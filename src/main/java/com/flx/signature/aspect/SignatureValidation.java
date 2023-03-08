package com.flx.signature.aspect;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.flx.utils.ParametersBuilder;
import com.flx.utils.SignatureUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author flx
 */
@Slf4j
@Aspect
@Component
public class SignatureValidation {

    private static final long MAX_LIMITS_TIMESTAMP = 2 * 60 * 1000;

    private static final String SECRET_KEY = "12345678";

    @Pointcut("execution(@com.flx.signature.aop.SignatureValidation * *(..))")
    private void verifySignature() {

    }

    @Before("verifySignature()")
    public void doVerify(JoinPoint point) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        String signature = "";
        Long timestamp = null;
        SortedMap<String, Object> sortedMap = new TreeMap<>();
        // request param
        Enumeration names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            String value = request.getParameter(name);
            if ("signature".equals(name)) {
                signature = value;
                continue;
            }
            if ("timestamp".equals(name)) {
                timestamp = Long.valueOf(value);
            }
            sortedMap.put(name, value);
        }

        if (StringUtils.isBlank(signature) || timestamp == null) {
            throw new RuntimeException("Signature or timestamp is not null");
        }

        long time = System.currentTimeMillis() - timestamp;
        if (time >= MAX_LIMITS_TIMESTAMP) {
            throw new RuntimeException("Signature has expired");
        }

        for (Object object : point.getArgs()) {
            JSONObject jsonObject = JSONUtil.parseObj(object);
            for (Map.Entry entry : jsonObject.entrySet()) {
                sortedMap.put(entry.getKey().toString(), entry.getValue().toString());
            }
        }
        String signatureStr = ParametersBuilder.appendParameters(sortedMap);
        boolean result = SignatureUtils.validateSignature(signatureStr, SECRET_KEY, signature);
        log.info("Signature string:{{}}, validate result:{}", signatureStr, result);
        if (!result) {
            throw new RuntimeException("Signature has expired");
        }
    }

    @AfterReturning(pointcut = "verifySignature()", returning = "responseObject")
    public void afterReturning(JoinPoint point, Object responseObject) {
        log.info("responseObject:{}", responseObject);
    }

}
