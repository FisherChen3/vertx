package com.jenkov.tutorials;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;

/**
 * Created by Fisher on 1/18/2017.
 */
public class VertxApp {

    public static void main(String[] args) {

//        vertx.deployVerticle(new MyVerticle(), new Handler<AsyncResult<String>>() {
//            @Override
//            public void handle(AsyncResult<String> stringAsyncResult) {
//                System.out.println("BasicVerticle deployment complete");
//            }
//        });

        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new EventBusReceiverVerticle("R1"), new Handler<AsyncResult<String>>() {
            @Override
            public void handle(AsyncResult<String> stringAsyncResult) {
                System.out.println("BasicVerticle deployment complete");
            }
        });
        vertx.deployVerticle(new EventBusReceiverVerticle("R2"));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        vertx.deployVerticle(new EventBusSenderVerticle());
//        vertx.deployVerticle(new VertxHttpServerVerticle());
    }

}
