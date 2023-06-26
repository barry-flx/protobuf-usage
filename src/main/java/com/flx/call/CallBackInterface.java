package com.flx.call;

import java.util.List;

public interface CallBackInterface<T> {

    T process(List<Object> list);
}
