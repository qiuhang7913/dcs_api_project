package com.huaching.xa.campus.u_center.rpc_service;

import com.huaching.xa.campus.basic.result.HttpResult;
import com.huaching.xa.campus.feign.service.TestFeignService;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestRPCProviderService implements TestFeignService {


    @Override
    public HttpResult<Map> getInfo() {
        return HttpResult.okResult();
    }
}
