package com.kevin.usc.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * 如果是继承了GatewayFilter就需要去配置,才能使得过滤器生效
 */
@Component
public class AccessTokenFilter implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        System.out.println("我是全局过滤器！！！");
        return chain.filter(exchange);
    }

    /**
     * 返回的数字越小优先级越高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }

}
