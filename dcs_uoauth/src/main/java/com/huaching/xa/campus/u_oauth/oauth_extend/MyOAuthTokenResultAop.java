package com.huaching.xa.campus.u_oauth.oauth_extend;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.huaching.xa.campus.basic.c_enum.HttpResultEnum;
import com.huaching.xa.campus.basic.util.ConvertDataUtil;
import com.huaching.xa.campus.basic.util.ObjectCheckUtil;
import com.huaching.xa.campus.basic.util.StrTool;
import com.huaching.xa.campus.u_oauth.vo.TokenVo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author qiuhang
 * @desc：${DESCRIPTION}
 * @date 2019/10/5/005
 * @return ${return}
 */
@Component
@Aspect
public class MyOAuthTokenResultAop {

    @Pointcut("execution(* org.springframework.security.oauth2.provider.endpoint.TokenEndpoint.postAccessToken(..))")
    public void tokenAspect(){}


    @Before("tokenAspect()")
    public void doBefore(JoinPoint joinPoint){
        Map<String, String> params = (Map) joinPoint.getArgs()[1];
        String client_id = params.get("client_id");
        TokenVo tokenVo = new TokenVo();
        if (StrTool.isEmpty(client_id)){
            tokenVo.setErrorCode(HttpResultEnum.USER_ACCESS_TOKEN_FAILED.getCode());
            tokenVo.setErrorMsg(HttpResultEnum.USER_ACCESS_TOKEN_FAILED.getMessage());
            throw new InvalidRequestException("client_id is must parameter");
        }

        DynamicDataSourceContextHolder.push(client_id);

    }

    @Around("tokenAspect()")
    public ResponseEntity<TokenVo> hanldControllerMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object proceed = proceedingJoinPoint.proceed();
        Map<String,String> param = (Map<String, String>) proceedingJoinPoint.getArgs()[1];
        TokenVo tokenVo = new TokenVo();
        if (!ObjectCheckUtil.checkIsNullOrEmpty(proceed)){
            ResponseEntity<OAuth2AccessToken> tokenResponseEntity = (ResponseEntity<OAuth2AccessToken>) proceed;
            if (tokenResponseEntity.getStatusCode().is2xxSuccessful()){
                OAuth2AccessToken token = tokenResponseEntity.getBody();
                tokenVo.setAccessToken(token.getValue());
                tokenVo.setRefreshToken(token.getRefreshToken().getValue());
                tokenVo.setAccessTokenExpireTime(ConvertDataUtil.convertStr(token.getExpiresIn()));
                tokenVo.setRefSchool(ConvertDataUtil.convertStr(param.get("school_head")));
                return ResponseEntity.status(HttpResultEnum.SUCCESS.getCode()).body(tokenVo);
            }else {
                tokenVo.setErrorCode(HttpResultEnum.USER_ACCESS_TOKEN_FAILED.getCode());
                tokenVo.setErrorMsg(HttpResultEnum.USER_ACCESS_TOKEN_FAILED.getMessage());
            }
        }
        return ResponseEntity.status(HttpResultEnum.SUCCESS.getCode()).body(tokenVo);
    }


    @AfterReturning(pointcut = "tokenAspect()", returning="rvt")
    public void hanldControllerMethod(JoinPoint proceedingJoinPoint, Object rvt) throws Throwable {
        DynamicDataSourceContextHolder.clear();
    }
}
