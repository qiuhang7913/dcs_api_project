package com.huaching.xa.campus.u_oauth.config;

import com.alibaba.fastjson.JSON;
import com.huaching.xa.campus.basic.c_enum.HttpResultEnum;
import com.huaching.xa.campus.basic.result.HttpResult;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>资源服务器配置</p>
 *
 * @author qiuhang
 * @version v1.0 2019/10/5/005
 */
@Configuration
@EnableResourceServer
@Order(3)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

        super.configure(resources);
        resources.authenticationEntryPoint((request, response, authException) -> {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(
                    HttpResult.aOtherResult(HttpResultEnum.USER_ACCESS_TOKEN_FAILED,
                            "请检查身份令牌(access_token)的完整与正确性！")));
            response.getWriter().flush();
        });

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().write(JSON.toJSONString(
                            HttpResult.aOtherResult(HttpResultEnum.USER_ACCESS_TOKEN_FAILED,
                                    "请检查身份令牌(access_token)的完整与正确性！")));
                    response.getWriter().flush();
                })
                .and()
                .requestMatchers().antMatchers("/api/**")
                .and()
                .authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .and()
                .httpBasic();
    }
}
