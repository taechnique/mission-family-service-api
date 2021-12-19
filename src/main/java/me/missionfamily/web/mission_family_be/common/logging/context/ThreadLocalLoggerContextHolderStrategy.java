package me.missionfamily.web.mission_family_be.common.logging.context;

import me.missionfamily.web.mission_family_be.common.util.Utils;
import org.springframework.util.Assert;

public class ThreadLocalLoggerContextHolderStrategy implements LoggerContextHolderStrategy{

    private static final ThreadLocal<LoggerContext> contextHolder = new ThreadLocal<>();

    @Override
    public void clearContext() {
        contextHolder.remove();
    }

    @Override
    public LoggerContext getContext() {
        LoggerContext context = contextHolder.get();
        if(Utils.isNull(context)) {
            context = createEmptyContext();
            contextHolder.set(context);
        }
        return context;
    }

    @Override
    public void setContext(LoggerContext context) {
        Assert.notNull(context, "Only non-null LoggerContext instances are permitted");
        contextHolder.set(context);
    }

    @Override
    public LoggerContext createEmptyContext() {
        return new LoggerContextImpl();
    }
}
