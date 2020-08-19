package com.huaching.xa.campus.gateway.provider;

import com.alibaba.fastjson.JSON;
import com.huaching.xa.campus.basic.c_enum.HttpResultEnum;
import com.huaching.xa.campus.basic.result.HttpResult;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@Component
public class MyFallbackProvider implements FallbackProvider {
    @Override
    public String getRoute() {
        // 表明是为哪个微服务提供回退，*表示为所有微服务提供回退
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return this.response(HttpStatus.INTERNAL_SERVER_ERROR, route);
    }

    private ClientHttpResponse response(final HttpStatus status, final String serviceId) {
        return new ClientHttpResponse() {

            @Override
            public HttpStatus getStatusCode() throws IOException {
                return status;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return status.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return status.getReasonPhrase();
            }

            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() throws IOException {
                String s = JSON.toJSONString(HttpResult.aOtherResult(HttpResultEnum.ERROR_CALL, "服务[serviceId:" + serviceId + "]不可用，请稍后再试!"));
                return new ByteArrayInputStream(s.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                // headers设定
                HttpHeaders headers = new HttpHeaders();
                MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
                headers.setContentType(mt);
                return headers;
            }

        };
    }

}
