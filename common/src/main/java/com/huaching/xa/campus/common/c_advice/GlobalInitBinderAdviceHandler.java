package com.huaching.xa.campus.common.c_advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


/**
 * <p>初始化数据绑定器</p>
 *
 * @author qiuhang
 * @version v1.0 2020/07/24
 */
@RestControllerAdvice
@Component
public class GlobalInitBinderAdviceHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * <p>应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器/p>
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //todo:具体使用在做具体实现
    }

}
