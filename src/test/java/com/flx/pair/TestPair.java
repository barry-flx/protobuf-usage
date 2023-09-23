package com.flx.pair;

import cn.hutool.json.JSONUtil;
import co.paralleluniverse.common.util.Pair;
import org.junit.Assert;
import org.junit.Test;

public class TestPair {

    @Test
    public void testPair() {
        Pair<String, Integer> pair = new Pair<>("hello", 100);
        Assert.assertNotNull(pair);
        System.out.println(JSONUtil.toJsonStr(pair));
    }

}
