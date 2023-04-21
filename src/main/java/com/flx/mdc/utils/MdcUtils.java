package com.flx.mdc.utils;

import com.flx.utils.Constants;
import org.slf4j.MDC;

import java.util.Map;
import java.util.UUID;

public class MdcUtils {

    public static String generateTraceId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getTraceId() {
        return MDC.get(Constants.TRACE_ID);
    }

    public static void setTraceId(String traceId) {
        MDC.put(Constants.TRACE_ID, traceId);
    }

    public static void setContextMap(Map<String, String> context) {
        MDC.setContextMap(context);
    }

    public static void removeTraceId() {
        MDC.remove(Constants.TRACE_ID);
    }

    public static void clear() {
        MDC.clear();
    }
}
