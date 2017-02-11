package io.vertx.manual.core;

import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;

/**
 * Created by Fisher on 2/9/2017.
 */
public class Test1 {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

//        vertx.setPeriodic(1000, id -> {
//            // This handler will get called every second
//            log("timer fired!");
//        });
//
//        log("completed");



        WorkerExecutor executor = vertx.createSharedWorkerExecutor("my-worker-pool",10);
        executor.executeBlocking(future -> {
            // Call some blocking API that takes a significant amount of time to return
            String result = blockingMethod("hello");
            log("request");
            future.complete(result);
        }, res -> {
            System.out.println("The result is: " + res.result());
            log("response");
        });
        log("completed");
    }

    public static void log(String message){

        System.out.println("["+Thread.currentThread()+"] "+message);

    }

    public static String blockingMethod(String message){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return message;
    }
}
