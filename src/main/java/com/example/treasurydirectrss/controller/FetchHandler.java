package com.example.treasurydirectrss.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.BodyInserter;
//import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.treasurydirectrss.external.Fetch;

import reactor.core.publisher.Mono;

@Component
public class FetchHandler {

	Logger logger = LogManager.getLogger();
	
	public Mono<ServerResponse> fetchAction(ServerRequest request) {
		//String hello = "\"Hello,World\"";
		//logger.info(hello);
		//BodyInserter<String, ReactiveHttpOutputMessage> bodyInserter = BodyInserters.fromValue(hello);
		//return ServerResponse.ok().body(bodyInserter);
		Fetch fetch = new Fetch();
		return fetch.webFetch(request);
	}
}
