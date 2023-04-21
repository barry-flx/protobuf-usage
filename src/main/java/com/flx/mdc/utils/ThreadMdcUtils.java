package com.flx.mdc.utils;

import java.util.Map;
import java.util.concurrent.Callable;

public class ThreadMdcUtils {

    public static void setTraceIdIfAbsent() {
        if (MdcUtils.getTraceId() != null) {
            return;
        }
        MdcUtils.setTraceId(MdcUtils.generateTraceId());
    }

    public static <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MdcUtils.clear();
            } else {
                MdcUtils.setContextMap(context);
            }
            setTraceIdIfAbsent();
            try {
                return callable.call();
            } finally {
                MdcUtils.clear();
            }
        };
    }

    public static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MdcUtils.clear();
            } else {
                MdcUtils.setContextMap(context);
            }
            setTraceIdIfAbsent();
            try {
                runnable.run();
            } finally {
                MdcUtils.clear();
            }
        };
    }

}
