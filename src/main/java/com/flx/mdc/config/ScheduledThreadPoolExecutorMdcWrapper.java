package com.flx.mdc.config;

import com.flx.mdc.utils.ThreadMdcUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.MDC;

import java.util.concurrent.*;

public class ScheduledThreadPoolExecutorMdcWrapper extends ScheduledThreadPoolExecutor {
    public ScheduledThreadPoolExecutorMdcWrapper(int corePoolSize) {
        super(corePoolSize);
    }

    public ScheduledThreadPoolExecutorMdcWrapper(int corePoolSize, @NotNull ThreadFactory threadFactory) {
        super(corePoolSize, threadFactory);
    }

    public ScheduledThreadPoolExecutorMdcWrapper(int corePoolSize, @NotNull RejectedExecutionHandler handler) {
        super(corePoolSize, handler);
    }

    public ScheduledThreadPoolExecutorMdcWrapper(int corePoolSize, @NotNull ThreadFactory threadFactory, @NotNull RejectedExecutionHandler handler) {
        super(corePoolSize, threadFactory, handler);
    }

    @Override
    public void execute(Runnable task) {
        super.execute(ThreadMdcUtils.wrap(task, MDC.getCopyOfContextMap()));
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return super.submit(ThreadMdcUtils.wrap(task, MDC.getCopyOfContextMap()), result);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return super.submit(ThreadMdcUtils.wrap(task, MDC.getCopyOfContextMap()));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(ThreadMdcUtils.wrap(task, MDC.getCopyOfContextMap()));
    }
}
