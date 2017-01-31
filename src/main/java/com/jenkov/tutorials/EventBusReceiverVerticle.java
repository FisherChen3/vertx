package com.jenkov.tutorials;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * Created by Fisher on 1/19/2017.
 */
public class EventBusReceiverVerticle extends AbstractVerticle{

    private String name = null;

    public EventBusReceiverVerticle(String name) {
        this.name = name;
    }

    public void start(Future<Void> startFuture) {
        vertx.eventBus().consumer("anAddress", message -> {
            System.out.println(this.name +
                    " received message: " +
                    message.body());
        });
    }

}
