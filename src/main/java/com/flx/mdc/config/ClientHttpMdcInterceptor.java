package com.flx.mdc.config;

import com.flx.mdc.utils.MdcUtils;
import com.flx.utils.Constants;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class ClientHttpMdcInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] bytes, ClientHttpRequestExecution execution) throws IOException {
        String traceId = MdcUtils.getTraceId();
        if (traceId != null) {
            request.getHeaders().set(Constants.TRACE_ID, traceId);
        }
        return execution.execute(request, bytes);
    }
}
