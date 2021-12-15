package me.missionfamily.web.mission_family_be.common.logging.context;

import me.missionfamily.web.mission_family_be.common.logging.StepLogger;
import me.missionfamily.web.mission_family_be.common.util.MissionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerContext {

    static Logger log = LoggerFactory.getLogger(LoggerContext.class);

    private LoggerContext(){}

    private static final ThreadLocal<LoggerAttribute> loggerAttribute = new ThreadLocal<LoggerAttribute>() {

        @Override
        protected LoggerAttribute initialValue() {
            return new LoggerAttribute();
        }
    };

    private static final InheritableThreadLocal<LoggerAttribute> inHeritableLoggerAttributes = new InheritableThreadLocal<LoggerAttribute>();

    public static void applyLogObject(StepLogger step) {

    }

    public static void applyLogObjct(StepLogger step, boolean inhertiable) {
        if(inhertiable) {
            loggerAttribute.remove();

            if(MissionUtil.isNull(inHeritableLoggerAttributes.get())) {

                LoggerAttribute attribute = new LoggerAttribute(step);
                inHeritableLoggerAttributes.set(attribute);
            } else {
                LoggerAttribute attribute = inHeritableLoggerAttributes.get();
                attribute.setLogObject(step);
            }
        } else {
            inHeritableLoggerAttributes.remove();
            if(MissionUtil.isNull(inHeritableLoggerAttributes.get())) {

                LoggerAttribute attribute = new LoggerAttribute(step);

            } else {
                LoggerAttribute attribute = loggerAttribute.get();
                attribute.setLogObject(step);
            }
        }
    }

    public static LoggerAttribute getAttribute() {
        LoggerAttribute attribute = loggerAttribute.get();
        if(MissionUtil.isNull(attribute)) {
            return inHeritableLoggerAttributes.get();
        }
        return attribute;
    }

    public static void setAttribute(LoggerAttribute attribute) {
        setAttribute(attribute, false);
    }

    private static void setAttribute(LoggerAttribute attribute, boolean inheritable) {
        if(MissionUtil.isNull(attribute)) {
            resetAttributes();
        } else {
            if(inheritable) {
                inHeritableLoggerAttributes.set(attribute);
                loggerAttribute.remove();
            } else {
                loggerAttribute.set(attribute);
                inHeritableLoggerAttributes.remove();
            }
        }
    }


    public static void resetAttributes() {
        loggerAttribute.remove();
        inHeritableLoggerAttributes.remove();
    }

    public static StepLogger getStepLogger() {
        if(MissionUtil.isNull(loggerAttribute.get().getStep())) {
            return inHeritableLoggerAttributes.get().getStep();
        }

        return loggerAttribute.get().getStep();
    }
}
