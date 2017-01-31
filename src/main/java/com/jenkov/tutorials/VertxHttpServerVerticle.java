package com.jenkov.tutorials;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;

/**
 * Created by Fisher on 1/19/2017.
 */
public class VertxHttpServerVerticle extends AbstractVerticle{

    private HttpServer httpServer = null;

    @Override
    public void start() throws Exception {
        httpServer = vertx.createHttpServer();
        httpServer.requestHandler(httpServerRequest -> System.out.println("incoming request"));
        // start http server by listen() method
        httpServer.listen(9999);
    }

}
