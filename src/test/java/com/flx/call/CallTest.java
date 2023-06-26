package com.flx.call;

import org.junit.Test;

import java.util.List;

public class CallTest {

    @Test
    public void call() {
        AppScenario appScenario = new AppScenario();

        appScenario.execute(list -> {
            List<Object> pList = list;
            pList.add(1);
            return true;
        });

        appScenario.execute(list -> {
            List<Object> pList = list;
            pList.remove(0);
            return true;
        });

        appScenario.list.forEach(object -> {
            System.out.println(object);
        });


    }

}
