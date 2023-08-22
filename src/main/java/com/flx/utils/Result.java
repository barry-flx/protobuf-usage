package com.flx.utils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Result {

    public static int SUCCESS = 0;

    private int code;

    private String msg;

    public boolean success() {
        return code == SUCCESS;
    }
}
