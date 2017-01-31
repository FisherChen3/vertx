package com.jenkov.tutorials;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * Created by Fisher on 1/19/2017.
 */
public class MyVerticle extends AbstractVerticle{

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        vertx.eventBus().consumer("anAddress",message -> {
            System.out.println("1 received message.body() = "
                    + message.body());
        });
    }

    @Override
    public void stop() throws Exception {
        System.out.println("MyVerticle stoped!");
    }
}
