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

/**
 * @author qiuhang
 * @desc：${DESCRIPTION}
 * @date 2019/10/5/005
 * @return ${return}
 */
@Component
@Aspect
public class MyOAuthTokenOtherErrorAop {

    @Around("execution(* org.springframework.security.oauth2.provider.endpoint.TokenEndpoint.handleException(..))")
<<<<<<< HEAD
    public ResponseEntity<TokenVo> doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
=======
    public ResponseEntity<TokenVo> hanldControllerMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
>>>>>>> a59694efc18f7c3cd80d479c9116b8584be62b21
        ResponseEntity<OAuth2Exception> excetion = (ResponseEntity<OAuth2Exception>) proceedingJoinPoint.proceed();
        TokenVo tokenVo = new TokenVo();
        tokenVo.setErrorCode(excetion.getBody().getHttpErrorCode());
        tokenVo.setErrorMsg(excetion.getBody().getMessage());
        return ResponseEntity.status(HttpResultEnum.FAILURE.getCode()).body(tokenVo);
    }

}
