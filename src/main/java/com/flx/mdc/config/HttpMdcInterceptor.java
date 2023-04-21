package com.flx.mdc.config;

import com.flx.mdc.utils.MdcUtils;
import com.flx.utils.Constants;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class HttpMdcInterceptor implements HttpRequestInterceptor {
    @Override
    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        String traceId = MdcUtils.getTraceId();
        if (traceId != null) {
            request.addHeader(Constants.TRACE_ID, traceId);
        }
    }
}
