package me.missionfamily.web.mission_family_be.common.aop;

import lombok.RequiredArgsConstructor;
import me.missionfamily.web.mission_family_be.common.logging.StepLogger;
import me.missionfamily.web.mission_family_be.common.logging.context.TrackerContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggerAop {

    private final StepLogger stepLogger;

    @Around("execution(* me.missionfamily.web.mission_family_be..*Controller.*(..))")
    public Object initRequestLog(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }

    @Around("@annotation(ServiceDescriptions)")
    public Object addServiceTracking(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        ServiceDescriptions checker = signature.getMethod().getAnnotation(ServiceDescriptions.class);

        TrackerContextHolder.getContext()
                .getTracker()
                .addStep(checker.value());

        return joinPoint.proceed();
    }
}
