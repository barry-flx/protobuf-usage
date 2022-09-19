package com.flx.quasar;

import java.util.concurrent.Future;

public interface AsyncFooClient {
    Future<String> asyncOp(String arg, FooCompletion<String> callback);
}
