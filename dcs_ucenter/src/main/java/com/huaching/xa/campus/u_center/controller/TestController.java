package com.huaching.xa.campus.u_center.controller;

import com.huaching.xa.campus.u_center.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestController extends BaseController {

    @Autowired
    private TestService testService;


    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    @PreAuthorize("hasAnyRole('测试123')")
    public void test(HttpServletRequest request) {
        dbEnvironmentChange(request);

        List<Map<String, Object>> test = testService.test();
        System.out.println(test);
        System.out.println(test.size());

        dbEnvironmentClear();
    }

}
