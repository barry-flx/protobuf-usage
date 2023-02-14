package com.flx.controller;

import cn.hutool.json.JSONUtil;
import com.flx.dto.UserDto;
import com.flx.signature.aop.SignatureValidation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/get")
    public String health() {
        return "200";
    }

    @SignatureValidation
    @PostMapping(value = "/post", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> post(@Validated @RequestBody UserDto req) {
        return new ResponseEntity<>(JSONUtil.toJsonStr(req), HttpStatus.OK);
    }
}
