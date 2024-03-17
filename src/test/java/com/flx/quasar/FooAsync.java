package com.flx.quasar;

import co.paralleluniverse.fibers.FiberAsync;

abstract class FooAsync<T> extends FiberAsync<T, FooException> implements FooCompletion<T> {

    @Override
    public void success(T result) {
        asyncCompleted(result);
    }

    @Override
    public void failure(FooException exception) {
        asyncFailed(exception);
    }
}
