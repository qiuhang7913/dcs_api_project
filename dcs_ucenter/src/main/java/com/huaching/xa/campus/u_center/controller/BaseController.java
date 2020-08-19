package com.huaching.xa.campus.u_center.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.huaching.xa.campus.basic.util.StrTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    @Autowired
    private UserInfoTokenServices userInfoTokenServices;


    protected void dbEnvironmentChange(HttpServletRequest request){
        JSONObject oauth2Request = obtainOAuthUserDetails(request).getJSONObject("oauth2Request");
        String clientId = oauth2Request.getString("clientId");
        DynamicDataSourceContextHolder.push(clientId);
    }


    protected void dbEnvironmentClear(){
        DynamicDataSourceContextHolder.clear();
    }

    /**
     *
     * 获取认证用户信息
     * @param request
     * @return JSONObject
     */
    protected JSONObject obtainOAuthUserInfo(HttpServletRequest request){
        return obtainOAuthUserDetails(request).getJSONObject("principal");
    }

    /**
     *
     * 获取accessToken;
     * @param request
     * @return accessToken
     */
    protected String obtanAccessToken(HttpServletRequest request){
        String accessToken = request.getHeader("Bearer ");
        if (! StrTool.isEmpty(accessToken)){
            accessToken = request.getParameter("access_token");
        }

        return accessToken;
    }

    /**
     *
     * 获取oauth认证信息详情
     * @param request
     * @return JSONObject
     */
    private JSONObject obtainOAuthUserDetails(HttpServletRequest request){
        OAuth2Authentication oAuth2Authentication = userInfoTokenServices.loadAuthentication(obtanAccessToken(request));
        return JSON.parseObject(JSON.toJSONString(oAuth2Authentication.getUserAuthentication().getDetails()));
    }
}
