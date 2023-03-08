package com.flx.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;

/**
 * @author flx
 */
public class ParametersBuilder {

    private static DecimalFormat df;

    public static String appendParameters(SortedMap<String, Object> parameters) {
        StringBuilder stringBuilder = new StringBuilder();
        return appendParameters(stringBuilder, parameters);
    }

    public static String appendParameters(StringBuilder stringBuilder, SortedMap<String, Object> parameters) {
        if (parameters != null && !parameters.isEmpty()) {
            boolean isFirst = true;
            while (true) {
                for (Map.Entry entry : parameters.entrySet()) {
                    String key = (String) entry.getKey();
                    if (entry.getValue() instanceof Double) {
                        parameters.replace(key, getFormatter().format(entry.getValue()));
                    } else if (entry.getValue() instanceof ArrayList) {
                        if (!((ArrayList) entry.getValue()).isEmpty()) {
                            appendListParameters(key, stringBuilder, (ArrayList) entry.getValue(), isFirst);
                            isFirst = false;
                        }
                        continue;
                    }

                    if (isFirst) {
                        isFirst = false;
                    } else {
                        stringBuilder.append('&');
                    }

                    stringBuilder.append(key).append('=').append(urlEncode(entry.getValue().toString()));
                }
                return stringBuilder.toString();
            }
        } else {
            return stringBuilder.toString();
        }
    }

    private static void appendListParameters(String key, StringBuilder stringBuilder, ArrayList<?> values, boolean isFirst) {
        Object value;
        for (Iterator iterator = values.iterator(); iterator.hasNext(); stringBuilder.append(key).append('=').append(urlEncode(value.toString()))) {
            value = iterator.next();
            if (isFirst) {
                isFirst = false;
            } else {
                stringBuilder.append('&');
            }
        }
    }

    public static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException var2) {
            throw new RuntimeException(StandardCharsets.UTF_8.name() + " is unsupported", var2);
        }
    }

    private static DecimalFormat getFormatter() {
        if (null == df) {
            df = new DecimalFormat();
            df.setMaximumFractionDigits(30);
            df.setGroupingUsed(false);
        }

        return df;
    }

}
