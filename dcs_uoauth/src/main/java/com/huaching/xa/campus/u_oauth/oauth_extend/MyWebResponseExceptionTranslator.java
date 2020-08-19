//package com.huaching.xa.campus.u_oauth.oauth_extend;
//
//import com.huaching.xa.campus.common.c_enum.HttpResultEnum;
//import com.huaching.xa.campus.u_oauth.vo.TokenVo;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
//import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MyWebResponseExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {
//
//    @Override
//    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
//        OAuth2Exception auth2Exception = new OAuth2Exception("异常");
//        TokenVo tokenVo = new TokenVo();
//        tokenVo.setErrorCode(HttpResultEnum.USER_ACCESS_TOKEN_FAILED.getCode());
//        tokenVo.setErrorMsg(HttpResultEnum.USER_ACCESS_TOKEN_FAILED.getMessage());
//
//        auth2Exception.addAdditionalInformation("aaa", "bbb");
//
//        return ResponseEntity.status(auth2Exception.getHttpErrorCode()).body(auth2Exception);
//    }
//
//}
