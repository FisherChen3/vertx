package io.vertx.blog.first;

import io.vertx.core.Vertx;

/**
 * Created by Fisher on 2/5/2017.
 */
public class VertxApp {
    public static void main(String[] args) {

        System.out.println("["+Thread.currentThread().getName()+"]"+" main started");

        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new MyFirstVerticle());

        System.out.println("["+Thread.currentThread().getName()+"]"+" main ended");

    }
}
