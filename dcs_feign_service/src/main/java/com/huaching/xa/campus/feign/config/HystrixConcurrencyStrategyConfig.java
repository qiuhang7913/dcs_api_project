package com.huaching.xa.campus.feign.config;

import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.concurrent.Callable;

@Configuration
public class HystrixConcurrencyStrategyConfig extends HystrixConcurrencyStrategy {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public HystrixConcurrencyStrategyConfig() {
        HystrixPlugins.reset();
        HystrixPlugins.getInstance().registerConcurrencyStrategy(this);
    }

    @Override
    public <T> Callable<T> wrapCallable(Callable<T> callable) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return () -> {
            try {
                RequestContextHolder.setRequestAttributes(requestAttributes);
                return callable.call();
            }finally {
                RequestContextHolder.resetRequestAttributes();
            }

        };
    }
}
