package com.bo.cloud.web.filter;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;

import reactor.core.publisher.Mono;

/**
 * @Description 自定义JWT校验拦截器
 * 类名一定要为filterName + GatewayFilterFactory
 * @Author Bo
 * @Version 2018年7月11日　下午5:04:40
 * @码云 https://gitee.com/LeisureBo
 */
public class JwtCheckGatewayFilterFactory implements GatewayFilterFactory<Object>{

	@Override
	public GatewayFilter apply(Object config) {
		 return (exchange, chain) -> {
	            String token = exchange.getRequest().getHeaders().getFirst("Authorization");
	            String openId =  exchange.getRequest().getQueryParams().getFirst("openId");
	            // check token
	            if (StringUtils.isNotBlank(token)) {
	                String tokenOpenID = null;//JwtUtil.verifyToken(token);
	                if(StringUtils.isNotBlank(tokenOpenID)) {
	                    if(openId != null) {
	                        if(openId.equals(tokenOpenID)) {
	                            return chain.filter(exchange);
	                        }
	                    } else {
	                        return chain.filter(exchange);
	                    }
	                }

	            }
	            // 不合法
	            ServerHttpResponse response = exchange.getResponse();
	            // 设置headers
	            HttpHeaders httpHeaders = response.getHeaders();
	            httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
	            httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
	            // 设置body
	            Map<String, String> retMap = new HashMap<>();
	            retMap.put("status", "101");
	            retMap.put("message", "未登录或登录超时");
	            DataBuffer bodyDataBuffer = response.bufferFactory().wrap(retMap.toString().getBytes());
	            return response.writeWith(Mono.just(bodyDataBuffer));
	        };
	}
	
}
