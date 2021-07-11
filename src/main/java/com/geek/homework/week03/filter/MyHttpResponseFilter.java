package com.geek.homework.week03.filter;

import io.netty.handler.codec.http.FullHttpResponse;

import java.util.UUID;


public class MyHttpResponseFilter implements HttpResponseFilter {

    @Override
    public void filter(FullHttpResponse response) {

        response.headers().set("sign", UUID.randomUUID().toString());
    }
}
