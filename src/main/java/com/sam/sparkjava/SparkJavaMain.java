package com.sam.sparkjava;

import static spark.Spark.*;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Unified Assurance (tm)
 * Copyright (C) 2015,  Tektronix Communications, Inc.
 * All rights reserved.
 */
public class SparkJavaMain {

    public static void main(String[] args) {

        //  setPort(5678); <- Uncomment this if you wan't spark to listen on a port different than 4567.

        get("/hello", new Route() {
            @Override
            public Object handle(Request request, Response response) {
                return "Hello World!";
            }
        });

        post("/hello", new Route() {
            @Override
            public Object handle(Request request, Response response) {
                return "Hello World: " + request.body();
            }
        });

        get("/private", new Route() {
            @Override
            public Object handle(Request request, Response response) {
                response.status(401);
                return "Go Away!!!";
            }
        });

        get("/users/:name", new Route() {
            @Override
            public Object handle(Request request, Response response) {
                return "Selected user: " + request.params(":name");
            }
        });

        get("/news/:section", new Route() {
            @Override
            public Object handle(Request request, Response response) {
                response.type("text/xml");
                return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><news>" + request.params("section") + "</news>";
            }
        });

        get("/protected", new Route() {
            @Override
            public Object handle(Request request, Response response) {
                halt(403, "I don't think so!!!");
                return null;
            }
        });

        get("/redirect", new Route() {
            @Override
            public Object handle(Request request, Response response) {
                response.redirect("/news/world");
                return null;
            }
        });

        get("/", new Route() {
            @Override
            public Object handle(Request request, Response response) {
                return "root";
            }
        });


    }
}
