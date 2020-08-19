//package com.huaching.xa.campus.u_oauth.filter;
//
//import com.alibaba.fastjson.JSON;
//import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
//import com.huaching.xa.campus.basic.c_enum.HttpResultEnum;
//import com.huaching.xa.campus.basic.result.HttpResult;
//import com.huaching.xa.campus.basic.util.ConvertDataUtil;
//import com.huaching.xa.campus.basic.util.StrTool;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//public class DBEnvironmentChangeFilter extends OncePerRequestFilter {
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//        String schoolHead = ConvertDataUtil.convertStr(request.getHeader("schoolHead"));
//        if (StrTool.isEmpty(schoolHead)){
//            schoolHead = ConvertDataUtil.convertStr(request.getParameter("schoolHead"));
//        }
//
//        if (StrTool.isEmpty(schoolHead)){
//            response.setContentType("application/json;charset=utf-8");
//            PrintWriter out = response.getWriter();
//            out.write(JSON.toJSONString(HttpResult.errorOtherResult(HttpResultEnum.REQUEST_PARAM_RRROR)));
//            out.flush();
//            out.close();
//        }
//
//        DynamicDataSourceContextHolder.push(schoolHead);
//
//        filterChain.doFilter(request, response);
//
//        DynamicDataSourceContextHolder.clear();
//
//    }
//}
