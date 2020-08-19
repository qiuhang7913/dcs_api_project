package com.huaching.xa.campus.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

public class PreFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        //执行顺序晚于自带filter
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        String originalRequestPath = context.get(FilterConstants.REQUEST_URI_KEY).toString();

        String modifiedRequestPath = originalRequestPath;
        //TODO 后续继续完善各子系统前缀
        if (originalRequestPath.contains("/api/portal")){
            modifiedRequestPath = "/m" + originalRequestPath;
        }else if(originalRequestPath.contains("/api/wallet") || originalRequestPath.contains("/api/ego")){
            modifiedRequestPath = "/h" + originalRequestPath;
        }
        context.put(FilterConstants.REQUEST_URI_KEY, modifiedRequestPath);

        return null;
    }
}
