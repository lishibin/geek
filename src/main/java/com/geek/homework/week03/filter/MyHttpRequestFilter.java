package com.geek.homework.week03.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


public class MyHttpRequestFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {

        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", "1");
        map.put("username", "jack");
        String token = Base64.getEncoder().encodeToString(map.toString().getBytes());

        fullRequest.headers().set("Authorization", token);
    }
}
