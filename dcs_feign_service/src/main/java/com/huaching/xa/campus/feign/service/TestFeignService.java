package com.huaching.xa.campus.feign.service;


import com.huaching.xa.campus.basic.c_constant.DCSProviderAppNameConstant;
import com.huaching.xa.campus.basic.result.HttpResult;
import com.huaching.xa.campus.feign.config.FallbackClientFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(name = DCSProviderAppNameConstant.UCENTER_PROVIDER_APP_NAME, fallbackFactory = FallbackClientFactory.class)
public interface TestFeignService {

    @GetMapping(value = "/api/feign/test")
    HttpResult<Map> getInfo();

}
