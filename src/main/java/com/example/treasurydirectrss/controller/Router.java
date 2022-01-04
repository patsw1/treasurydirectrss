package com.example.treasurydirectrss.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class Router {
	Logger logger = LogManager.getLogger();

	@Bean
	RouterFunction<ServerResponse> helloworld(HelloworldHandler helloworldHandler) {
		logger.info("GET /helloworld");
		RouterFunction<ServerResponse> route = RouterFunctions.route(RequestPredicates.GET("/helloworld"),
				helloworldHandler::helloworldAction);
		return route;
	}
	@Bean
	RouterFunction<ServerResponse> fetch(FetchHandler fetchHandler) {
		logger.info("GET /fetch");
		RouterFunction<ServerResponse> route = RouterFunctions.route(RequestPredicates.GET("/fetch"),
				fetchHandler::fetchAction);
		return route;
	}
}
