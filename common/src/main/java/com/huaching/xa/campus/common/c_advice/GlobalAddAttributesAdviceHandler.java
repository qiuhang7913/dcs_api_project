package com.huaching.xa.campus.common.c_advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>全局model值绑定</p>
 *
 * @author qiuhang
 * @version v1.0 2020/07/24
 */
@RestControllerAdvice
@Component
public class GlobalAddAttributesAdviceHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * <p> 把值绑定到Model中，使全局@RequestMapping可以获取到该值/p>
     *
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        //todo:具体使用在做具体实现
    }
}
