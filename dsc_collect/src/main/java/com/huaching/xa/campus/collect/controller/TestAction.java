package com.huaching.xa.campus.collect.controller;

import com.huaching.xa.campus.basic.result.HttpResult;
import com.huaching.xa.campus.feign.service.TestFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestAction {

    @Autowired
    TestFeignService testFeignService;


    @RequestMapping("test")
    public HttpResult<Map> test(){
        return testFeignService.getInfo();
    }

}
