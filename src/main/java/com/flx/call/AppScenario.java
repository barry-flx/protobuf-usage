package com.flx.call;

import java.util.ArrayList;
import java.util.List;

public class AppScenario {

    public List<Object> list = new ArrayList<>();

    public <T> T execute(CallBackInterface callBackInterface) {
        return (T) callBackInterface.process(list);
    }

}
