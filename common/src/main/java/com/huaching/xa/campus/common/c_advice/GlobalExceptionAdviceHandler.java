package com.huaching.xa.campus.common.c_advice;

import com.alibaba.fastjson.JSONException;
import com.huaching.xa.campus.basic.c_enum.HttpResultEnum;
import com.huaching.xa.campus.basic.c_exception.BusinessException;
import com.huaching.xa.campus.basic.result.HttpResult;
import com.huaching.xa.campus.basic.util.StrTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>全局错误封装</p>
 * <p>全局错误封装定义在这里</p>
 *
 * @author qiuhang
 * @version v1.0 2020/07/24
 */
@RestControllerAdvice
@Component
public class GlobalExceptionAdviceHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     *<p>异常返回处理/p>
     *
     * @param exception:异常
     * @return HttpResult
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpResult<Object> handleRequestError(Exception exception) {
        return dealWithError(exception);
    }


    /**
     *<p>异常返回处理-业务执行/p>
     *
     * @param ex:异常
     * @return HttpResult
     */
    private HttpResult<Object> dealWithError(Exception ex){
        logger.debug("错误信息!",ex);

        if (ex instanceof BusinessException){
            BusinessException businessException = (BusinessException) ex;
            return HttpResult.aOtherResult(businessException.getHttpResultEnum(), businessException.getOtherObjResult());
        }

        if (ex instanceof HttpRequestMethodNotSupportedException){
            return  HttpResult.aOtherResult(HttpResultEnum.REQUEST_METHOD_TYPE_RRROR, ex.getMessage());
        }

        if (ex instanceof JSONException || ex instanceof HttpMessageConversionException){
            return HttpResult.aOtherResult(HttpResultEnum.REQUEST_PARAM_CONVERT_RRROR, ex.getMessage());
        }

        if (ex instanceof MethodArgumentNotValidException){
            return HttpResult.aOtherResult(HttpResultEnum.REQUEST_PARAM_RRROR, methodArgNotValid(((MethodArgumentNotValidException)ex).getBindingResult()));
        }

        if (ex instanceof AccessDeniedException){
            return HttpResult.aOtherResult(HttpResultEnum.USER_NO_ACCESS, ex.getMessage());
        }

        return HttpResult.aOtherResult(HttpResultEnum.SYSTEM_RRROR, ex.getMessage());
    }


    /**
     *<p>参数校验错误/p>
     *
     * @param result:BindingResult
     * @return String:拼接的错误内容
     */
    private String methodArgNotValid(BindingResult result){
        String err = StrTool.EMPTY_STR;
        if (result.hasErrors()){
            List<ObjectError> allErrors = result.getAllErrors();
            List<String> allErrorDesc = new ArrayList<>();
            allErrors.forEach(p -> {
                FieldError fieldError = (FieldError) p;
                allErrorDesc.add("{错误字段["+fieldError.getField()+"],错误内容["+fieldError.getDefaultMessage()+"]}");
            });
            err = StrTool.join(allErrorDesc,StrTool.EMPTY_STR);
        }
        return err;
    }

}
