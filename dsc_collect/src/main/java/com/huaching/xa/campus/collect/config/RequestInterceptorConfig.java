//package com.huaching.xa.campus.collect.config;
//
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Enumeration;
//
//@Configuration
//public class RequestInterceptorConfig implements RequestInterceptor {
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//
//    private static final String AUTHORIZATION_HEADER = "Authorization";
//
//    private static final String BEARER_TOKEN_TYPE = "Bearer";
//
//
//    @Override
//    public void apply(RequestTemplate requestTemplate) {
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        if (requestAttributes == null) {
//            return;
//        }
//
//        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
//        Enumeration<String> headerNames = request.getHeaderNames();
//        if (headerNames != null) {
//            while (headerNames.hasMoreElements()) {
//                String name = headerNames.nextElement();
//                Enumeration<String> values = request.getHeaders(name);
//                while (values.hasMoreElements()) {
//                    String value = values.nextElement();
//                    requestTemplate.header(name, value);
//                }
//            }
//        }
//    }
//}
