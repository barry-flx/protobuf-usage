package com.flx.signature;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.flx.dto.UserDto;
import com.flx.utils.UrlBuilder;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class TestSignature {

    private static final String SECRET_KEY = "12345678";

    @Test
    public void testGet() {
        String result = HttpRequest.get(UrlBuilder.buildUrl("http://localhost:8081/get", SECRET_KEY)).execute().body();
        System.out.println(result);
    }

    @Test
    public void testPost() {
        UserDto userDto = UserDto.builder().name("test").password("123").build();
        SortedMap<String, Object> parameters = new TreeMap<>();
        parameters.put("password", userDto.getPassword());
        parameters.put("name", userDto.getName());

        String result = HttpRequest.post(UrlBuilder.buildUrl("http://localhost:8081/post", SECRET_KEY, parameters))
                .body(JSONUtil.toJsonStr(userDto))
                .header("Content-Type", "application/json").execute().body();

        System.out.println(result);
    }


}
