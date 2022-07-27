package com.flx.mode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Strategy mode test
 */
public class TestStrategyMode {

    private static Map<String, Function<String, String>> modeMap = new HashMap<>();

    @Test
    public void testMode() {
        init();
        String key = "key02";
        Function<String, String> result = modeMap.get(key);
        if (result != null) {
            String value = result.apply("hello " + key);
            System.out.println(value);
        } else {
            System.out.println("map is null");
        }
    }

    private void init() {
        modeMap.put("key01", TestStrategyMode::key01);
        modeMap.put("key02", TestStrategyMode::key02);
        modeMap.put("key03", TestStrategyMode::key03);
    }

    public static String key01(String key) {
        System.out.println("key01===========" + key);
        return key;
    }

    public static String key02(String key) {
        System.out.println("key02===========" + key);
        return key;
    }

    public static String key03(String key) {
        System.out.println("key03===========" + key);
        return key;
    }

}
