package com.flx.quasar;

import co.paralleluniverse.fibers.FiberAsync;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.fibers.Suspendable;

public class FiberFooClient implements FooClient {
    private final AsyncFooClient asyncClient;

    public FiberFooClient(AsyncFooClient asyncClient) {
        this.asyncClient = asyncClient;
    }

    @Override
    @Suspendable
    public String op(final String arg) throws FooException, InterruptedException {
        try {
            return new FiberAsync<String, FooException>() {
                @Override
                protected void requestAsync() {
                    asyncClient.asyncOp(arg, new FooCompletion<String>() {
                        public void success(String result) {
                            asyncCompleted(result);
                        }

                        public void failure(FooException exception) {
                            asyncFailed(exception);
                        }
                    });
                }
            }.run();
        } catch (SuspendExecution e) {
            throw new AssertionError(e);
        }
    }
}
/*
https://www.javacodegeeks.com/2015/04/farewell-to-asynchronous-code.html
        https://github.com/puniverse/comsat
        http://docs.paralleluniverse.co/quasar/#specifying-the-java-agent-with-gradle
        http://docs.paralleluniverse.co/quasar/javadoc/co/paralleluniverse/fibers/FiberAsync.html*/
