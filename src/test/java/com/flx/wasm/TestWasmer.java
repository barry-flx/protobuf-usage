package com.flx.wasm;

import org.wasmer.Instance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestWasmer {

    public static void main(String[] args) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get("E:\\item\\java\\usage\\protobuf-usage\\src\\test\\resources\\a.out.wasm"));
        Instance instance = new Instance(bytes);
        Integer result = (Integer) instance.exports.getFunction("add").apply(5, 37)[0];
        assert result == 42;

        String s = (String) instance.exports.getFunction("concat_str").apply("h", "w")[0];
        System.out.println(s);

        instance.close();
    }
}
