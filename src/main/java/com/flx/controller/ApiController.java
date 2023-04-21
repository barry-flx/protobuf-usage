package com.flx.controller;

import cn.hutool.json.JSONUtil;
import com.flx.dto.UserDto;
import com.flx.mdc.config.ScheduledThreadPoolExecutorMdcWrapper;
import com.flx.mdc.config.ThreadPoolExecutorMdcWrapper;
import com.flx.mdc.utils.HttpClientUtils;
import com.flx.mdc.utils.OkHttpUtils;
import com.flx.mdc.utils.RestTemplateHttpUtils;
import com.flx.signature.aop.SignatureValidation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

@Slf4j
@RestController
public class ApiController {

    private static ThreadPoolExecutor executorMdcWrapper = new ThreadPoolExecutorMdcWrapper(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>());
    private static ScheduledThreadPoolExecutor taskMdcWrapper = new ScheduledThreadPoolExecutorMdcWrapper(12, new BasicThreadFactory.Builder().namingPattern("task--%d").daemon(true).build());

    @GetMapping("/get")
    public String get() {
        log.info("success.");
        return "200";
    }

    @GetMapping("/mdc")
    public void mdc() {
        log.info("return ok.");
        executorMdcWrapper.execute(() -> log.info("executor thread log."));
        taskMdcWrapper.scheduleWithFixedDelay(() -> {
            log.info("schedule executor thread log.");
        }, 1_000, 5_000, TimeUnit.MILLISECONDS);

        OkHttpUtils.doGet("http://localhost:8081/get");
        HttpClientUtils.doGet("http://localhost:8081/get");
        RestTemplateHttpUtils.doGet("http://localhost:8081/get");
    }

    @SignatureValidation
    @PostMapping(value = "/post", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> post(@Validated @RequestBody UserDto req) {
        return new ResponseEntity<>(JSONUtil.toJsonStr(req), HttpStatus.OK);
    }
}
