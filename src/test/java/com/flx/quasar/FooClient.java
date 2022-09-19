package com.flx.quasar;

public interface FooClient {
    String op(String arg) throws FooException, InterruptedException;
}
