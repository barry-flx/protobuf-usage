package com.flx.mdc.config;

import com.flx.mdc.utils.MdcUtils;
import com.flx.utils.Constants;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class OkHttpMdcInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        String traceId = MdcUtils.getTraceId();
        Request request = null;
        if (traceId != null) {
            request = chain.request().newBuilder().addHeader(Constants.TRACE_ID, traceId).build();
        }
        return chain.proceed(request);
    }
}
