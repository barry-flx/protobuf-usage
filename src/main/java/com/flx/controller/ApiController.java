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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ServletContext servletContext;

    private static ThreadPoolExecutor executorMdcWrapper = new ThreadPoolExecutorMdcWrapper(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>());
    private static ScheduledThreadPoolExecutor taskMdcWrapper = new ScheduledThreadPoolExecutorMdcWrapper(12, new BasicThreadFactory.Builder().namingPattern("task--%d").daemon(true).build());

    @GetMapping("get")
    public String get(@RequestParam Integer limit) {
        log.info("success, limit:{}", limit);
        return "200";
    }

    @GetMapping("/file")
    public String file() {
        StringBuilder sb = new StringBuilder();
        try {
            File file = new File("src/main/resources/file/test.txt");
            sb.append("|file read#").append(readInputStream(new FileInputStream(file)));
        } catch (Exception ex) {
            log.error("File read error", ex);
        }

        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("file/test.txt");
            sb.append("|ClassLoader read#").append(readInputStream(inputStream));
        } catch (Exception ex) {
            log.error("ClassLoader read error", ex);
        }

        try {
            InputStream inputStream = getClass().getResourceAsStream("/file/test.txt");
            sb.append("|Class read#").append(readInputStream(inputStream));
        } catch (Exception ex) {
            log.error("Class read error", ex);
        }

        try {
            Resource resource = resourceLoader.getResource("classpath:file/test.txt");
            sb.append("|ResourceLoader read#").append(readInputStream(resource.getInputStream()));
        } catch (Exception ex) {
            log.error("ResourceLoader read error", ex);
        }

        try {
            File file = ResourceUtils.getFile("classpath:file/test.txt");
            sb.append("|ResourceUtils read#").append(readInputStream(Files.newInputStream(file.toPath())));
        } catch (Exception ex) {
            log.error("ResourceUtils read error", ex);
        }

        try {
            Resource resource = applicationContext.getResource("classpath:file/test.txt");
            sb.append("|ResourceLoader read#").append(readInputStream(resource.getInputStream()));
        } catch (Exception ex) {
            log.error("ApplicationContext read error", ex);
        }

        try {
            sb.append("|ServletContext read#").append(readInputStream(servletContext.getResourceAsStream("file/test.txt")));
        } catch (Exception ex) {
            log.error("ServletContext read error", ex);
        }

        try {
            Path path = Paths.get("src/main/resources/file/test.txt");
            sb.append("|Paths read#").append(readInputStream(Files.newInputStream(path)));
        } catch (Exception ex) {
            log.error("Paths read error", ex);
        }

        try {
            ClassPathResource resource = new ClassPathResource("file/test.txt");
            sb.append("|ClassPathResource read#").append(readInputStream(resource.getInputStream()));
        } catch (Exception ex) {
            log.error("ClassPathResource read error", ex);
        }

        return sb.toString();
    }

    private String readInputStream(InputStream inputStream) throws Exception {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString(StandardCharsets.UTF_8.name());
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
