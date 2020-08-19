package com.huaching.xa.campus.u_oauth.oauth_extend;

import com.huaching.xa.campus.basic.c_enum.HttpResultEnum;
import com.huaching.xa.campus.basic.util.ObjectCheckUtil;
import com.huaching.xa.campus.u_oauth.vo.TokenVo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyOAuthTokenRequestMethodErrorAop {

    @Around("execution(* org.springframework.security.oauth2.provider.endpoint.TokenEndpoint.handleHttpRequestMethodNotSupportedException(..))")
    public ResponseEntity<TokenVo> doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ResponseEntity<OAuth2Exception> excetion = (ResponseEntity<OAuth2Exception>) proceedingJoinPoint.proceed();
        TokenVo tokenVo = new TokenVo();
        tokenVo.setErrorCode(excetion.getBody().getHttpErrorCode());
        tokenVo.setErrorMsg(excetion.getBody().getMessage());
        return ResponseEntity.status(HttpResultEnum.FAILURE.getCode()).body(tokenVo);
    }


}
