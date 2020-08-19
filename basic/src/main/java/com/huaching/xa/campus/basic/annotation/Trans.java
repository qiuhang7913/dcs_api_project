package com.huaching.xa.campus.basic.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({METHOD, FIELD, TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Trans {

    /**
     * redis  存储key
     * @return
     */
    String transKey() default "";
    
    /**
     * @description：关联字典表名
     * @author qiuhang
     * @date 2019/9/23
     */
    String refDICTName() default "";
    
    /**
     * @description：关联字段表条件字段
     * @author qiuhang
     * @date 2019/9/23/023
     */
    String refDICTCondition() default "FLD_ID";
    
    /**
     * @description：关联字段表结果字段
     * @author qiuhang
     * @date 2019/9/23/023
     */
    String refDICTQuery() default "FLD_NAME";
}
