package com.huaching.xa.campus.basic.c_exception;


import com.huaching.xa.campus.basic.c_enum.HttpResultEnum;

/**
 * <p>业务自定义封装runtime异常</p>
 *
 * @author qiuhang
 * @version v1.0 2020/07/24
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private HttpResultEnum httpResultEnum;

    private Object otherObjResult;

    public BusinessException(HttpResultEnum httpResultEnum) {
        super(httpResultEnum.getMessage());
        this.httpResultEnum = httpResultEnum;
    }

    public BusinessException(HttpResultEnum httpResultEnum, Object otherObjResult) {
        super(httpResultEnum.getMessage());
        this.httpResultEnum = httpResultEnum;
        this.otherObjResult = otherObjResult;
    }

    public HttpResultEnum getHttpResultEnum() {
        return httpResultEnum;
    }

    public void setHttpResultEnum(HttpResultEnum httpResultEnum) {
        this.httpResultEnum = httpResultEnum;
    }

    public Object getOtherObjResult() {
        return otherObjResult;
    }

    public void setOtherObjResult(Object otherObjResult) {
        this.otherObjResult = otherObjResult;
    }
}
