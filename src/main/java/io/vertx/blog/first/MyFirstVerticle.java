package io.vertx.blog.first;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * Created by Fisher on 1/21/2017.
 * tutorial: http://vertx.io/blog/my-first-vert-x-3-application/
 */
// very verticle is single thread
public class MyFirstVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> fut) {
        System.out.println("["+Thread.currentThread().getName()+"]"+" begin of start");
        vertx.createHttpServer()
                .requestHandler(r -> {
                    r.response().end("<h1>Hello from my first " +
                            "Vert.x 3 application</h1>");
                    System.out.println("["+Thread.currentThread().getName()+"]"+" response");
                })
                .listen(
                        // Retrieve the port from the configuration,
                        // default to 8080.
                        config().getInteger("http.port",8080),
                        result -> {
                                        if (result.succeeded()) {
                                            fut.complete();
                                            System.out.println("["+Thread.currentThread().getName()+"]"+" completed");
                                        } else {
                                            fut.fail(result.cause());
                                            System.out.println("["+Thread.currentThread().getName()+"]"+" error");
                                   }
                });
        System.out.println("["+Thread.currentThread().getName()+"]"+" end of start");
    }

//    // Store our product
//    private Map<Integer, Whisky> products = new LinkedHashMap<>();
//    // Create some product
//    private void createSomeData() {
//        Whisky bowmore = new Whisky("Bowmore 15 Years Laimrig", "Scotland, Islay");
//        products.put(bowmore.getId(), bowmore);
//        Whisky talisker = new Whisky("Talisker 57° North", "Scotland, Island");
//        products.put(talisker.getId(), talisker);
//    }
//
//    @Override
//    public void start(Future<Void> fut) {
//
//        createSomeData();
//
//        // Create a router object.
//        // router is the cornerstone of Vert.x Web
//        Router router = Router.router(vertx);
//
//        // Bind "/" to our hello message - so we are still compatible.
//        router.route("/").handler(routingContext -> {
//            HttpServerResponse response = routingContext.response();
//            response
//                    .putHeader("content-type", "text/html")
//                    .end("<h1>Hello from my first Vert.x 3 application</h1>");
//        });
//
//        // Serve static resources from the /assets directory
//        router.route("/assets/*").handler(StaticHandler.create("assets"));
//
//        router.get("/api/whiskies").handler(this::getAll);
//
//        // enables the reading of the request body for all routes under “/api/whiskies”
//        router.route("/api/whiskies*").handler(BodyHandler.create());
//        router.post("/api/whiskies").handler(this::addOne);
//        router.get("/api/whiskies/:id").handler(this::getOne);
//        router.put("/api/whiskies/:id").handler(this::updateOne);
//        router.delete("/api/whiskies/:id").handler(this::deleteOne);
//
//        // Create the HTTP server and pass the "accept" method to the request handler.
//        vertx
//                .createHttpServer()
//                .requestHandler(router::accept)
//                .listen(
//                        // Retrieve the port from the configuration,
//                        // default to 8080.
//                        config().getInteger("http.port", 8080),
//                        result -> {
//                            if (result.succeeded()) {
//                                fut.complete();
//                            } else {
//                                fut.fail(result.cause());
//                            }
//                        }
//                );
//    }
//
//    private void getAll(RoutingContext routingContext) {
//        routingContext.response()
//                .putHeader("content-type", "application/json; charset=utf-8")
//                .end(Json.encodePrettily(products.values()));
//    }
//
//    private void addOne(RoutingContext routingContext) {
//        final Whisky whisky = Json.decodeValue(routingContext.getBodyAsString(),
//                Whisky.class);
//        products.put(whisky.getId(), whisky);
//        routingContext.response()
//                .setStatusCode(201)
//                .putHeader("content-type", "application/json; charset=utf-8")
//                .end(Json.encodePrettily(whisky));
//    }
//
//    private void getOne(RoutingContext routingContext) {
//        final String id = routingContext.request().getParam("id");
//        if (id == null) {
//            routingContext.response().setStatusCode(400).end();
//        } else {
//            final Integer idAsInteger = Integer.valueOf(id);
//            Whisky whisky = products.get(idAsInteger);
//            if (whisky == null) {
//                routingContext.response().setStatusCode(404).end();
//            } else {
//                routingContext.response()
//                        .putHeader("content-type", "application/json; charset=utf-8")
//                        .end(Json.encodePrettily(whisky));
//            }
//        }
//    }
//
//    private void updateOne(RoutingContext routingContext) {
//        final String id = routingContext.request().getParam("id");
//        JsonObject json = routingContext.getBodyAsJson();
//        if (id == null || json == null) {
//            routingContext.response().setStatusCode(400).end();
//        } else {
//            final Integer idAsInteger = Integer.valueOf(id);
//            Whisky whisky = products.get(idAsInteger);
//            if (whisky == null) {
//                routingContext.response().setStatusCode(404).end();
//            } else {
//                whisky.setName(json.getString("name"));
//                whisky.setOrigin(json.getString("origin"));
//                routingContext.response()
//                        .putHeader("content-type", "application/json; charset=utf-8")
//                        .end(Json.encodePrettily(whisky));
//            }
//        }
//    }
//
//    private void deleteOne(RoutingContext routingContext) {
//        String id = routingContext.request().getParam("id");
//        if (id == null) {
//            routingContext.response().setStatusCode(400).end();
//        } else {
//            Integer idAsInteger = Integer.valueOf(id);
//            products.remove(idAsInteger);
//        }
//        routingContext.response().setStatusCode(204).end();
//    }
}