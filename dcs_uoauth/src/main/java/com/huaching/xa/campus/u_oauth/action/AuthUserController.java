package com.huaching.xa.campus.u_oauth.action;

import com.huaching.xa.campus.basic.result.HttpResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class AuthUserController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("/member")
    @HystrixCommand
    public Principal user(Principal member) {


        return member;
    }

    @DeleteMapping(value = "/exit")
    @HystrixCommand
    public HttpResult<String> revokeToken(String access_token) {
        if (consumerTokenServices.revokeToken(access_token)) {
            return HttpResult.okOtherDataResult("注销成功");
        } else {
            return HttpResult.errorOtherObjResult("注销失败");
        }
    }

}
