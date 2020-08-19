package com.huaching.xa.campus.feign.config;

import com.huaching.xa.campus.basic.c_enum.HttpResultEnum;
import com.huaching.xa.campus.basic.result.HttpResult;
import com.huaching.xa.campus.feign.service.TestFeignService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FallbackClientFactory implements FallbackFactory<TestFeignService> {


    @Override
    public TestFeignService create(Throwable throwable) {
        return new TestFeignService() {
            @Override
            public HttpResult<Map> getInfo() {
                return HttpResult.errorOtherResult(HttpResultEnum.SYSTEM_RRROR);
            }
        };
    }
}
