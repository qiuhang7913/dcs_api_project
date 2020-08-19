package com.huaching.xa.campus.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.huaching.xa.campus.basic.c_enum.HttpResultEnum;
import com.huaching.xa.campus.basic.result.HttpResult;
import com.huaching.xa.campus.basic.util.ConvertDataUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorFilter extends ZuulFilter {

    @Override
    public String filterType() {
//        PRE：这种过滤器在请求被路由调用之前调用。我们可利用这种过滤器实现身份验证、再集群中选择请求的微服务、记录调试信息等。
//        ROUTING：这种过滤器将请求路由到微服务。用于构建发送给微服务的请求，并使用Apache HttpClient或Netflix Ribbon构建和发送原始HTTP请求的位置。
//        POST：请求在路由到微服务之后执行。示例包括向响应添加标准HTTP标头、收集统计信息和指标、以及将响应从源传输到客户端。
//        ERROR：过滤器在其中一个阶段发生错误时执行。
        return "error";
    }

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return true;
    }

    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        HttpServletResponse response = currentContext.getResponse();

        //response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setContentType("text/html;charset=utf-8");
        /* 允许跨域的主机地址 */
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Host"));
        /* 允许跨域的请求方法GET, POST, HEAD 等 */
        response.setHeader("Access-Control-Allow-Methods", "*");
        /* 重新预检验跨域的缓存时间 (s) */
        response.setHeader("Access-Control-Max-Age", "3600");
        /* 允许跨域的请求头 */
        response.setHeader("Access-Control-Allow-Headers", "*");
        /* 是否携带cookie */
        response.setHeader("Access-Control-Allow-Credentials", "true");


        ZuulException zuulException = (ZuulException) currentContext.get("throwable");
        String serviceId = ConvertDataUtil.convertStr(currentContext.get("serviceId"));
        String curt_uri = ConvertDataUtil.convertStr(currentContext.get("requestURI"));


        try {
            response.getOutputStream().write(JSON.toJSONString(HttpResult.aOtherResult(HttpResultEnum.ERROR_CALL,
                    "网关调用异常[serviceId:" + serviceId + ", curt_uri:" + curt_uri + "]")).getBytes());
            response.getOutputStream().flush();
            response.getOutputStream().close();
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
