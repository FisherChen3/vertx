package io.vertx.manual.core;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;

/**
 * Created by Fisher on 2/9/2017.
 */
public class Test2 {

    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();
        FileSystem fs = vertx.fileSystem();
        // Create a future that hasn't completed yet
        Future<Void> startFuture = Future.future();

        Future<Void> fut1 = Future.future();
        fs.createFile("foo",fut1.completer());

        fut1.compose(v->{
            // When the file is created (fut1), execute this:
            Future<Void> fut2 = Future.future();
            fs.writeFile("foo", Buffer.buffer(), fut2.completer());
            log(String.valueOf(fut1.isComplete()));
            return fut2;
        }).compose(v -> {
                    // When the file is written (fut2), execute this:
                    Future<Void> fut3 = Future.future();
                    fs.move("foo", "bar", fut3.completer());
                    log(String.valueOf(fut3.isComplete()));
                },
                // mark the start future as completed when all the chain has been completed,
                // or mark it as failed if any step fails.
                startFuture);
        log(String.valueOf(startFuture.isComplete()));

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log(String.valueOf(startFuture.isComplete()));
    }

    public static void log(String message){

        System.out.println("["+Thread.currentThread()+"] "+message);

    }

}
