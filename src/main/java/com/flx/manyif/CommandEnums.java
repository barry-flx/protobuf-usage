package com.flx.manyif;

import java.util.HashMap;
import java.util.Map;

public enum CommandEnums {

    ONE("one", "com.flx.manyif.OneCommand"),
    TWO("two", "com.flx.manyif.TwoCommand"),
    THREE("three", "com.flx.manyif.ThreeCommand");

    private static final Map<String, String> commandMap = new HashMap<>();

    static {
        for (CommandEnums command : CommandEnums.values()) {
            commandMap.put(command.getCode(), command.getClazz());
        }
    }

    public static Map<String, String> getAllClazz() {
        return commandMap;
    }

    private String code;

    private String clazz;

    CommandEnums(String code, String clazz) {
        this.code = code;
        this.clazz = clazz;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

}
