package com.geek.homework.week03.router;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class MyHttpEndpointRouter implements HttpEndpointRouter {

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public String route(List<String> endpoints) {

        return endpoints.get(atomicInteger.getAndIncrement() % endpoints.size());
    }
}
