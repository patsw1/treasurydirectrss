package com.example.treasurydirectrss.external;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.function.server.ServerRequest;
//import org.springframework.web.reactive.function.server.ServerResponse.BodyBuilder;
import org.awaitility.Awaitility;

import reactor.core.publisher.Mono;

public class Fetch {

	final String validator = "http://validator.w3.org/checklink";
	final String treasury = "https://www.treasurydirect.gov";
	final String rss = "/TA_WS/securities/announced/rss";

	Logger logger = LogManager.getLogger();
	AtomicBoolean received = new AtomicBoolean();

	public void test() {
		logger.info("test()");
		Mono<ServerResponse> mono = fetch(treasury);
		logger.info("test() fetch returned mono="+mono);
		
		mono.subscribe(s -> {
			logger.info("test() mono subscription returned " + s.getClass().getName()+":"+s);
			if (s instanceof Mono) {
			logger.info("is Mono");	
			}
			if (s instanceof ServerResponse) {
			logger.info("is ServerResponse");	
			}
			//ServerResponse response = s;
			received.set(true);
		});
		
		
		//mono.flatMap(s->{
		//	logger.info("flatmap:"+s+" momo:"+mono);
		//	received.set(true);
		//	return mono;
		//	});
		
		
		logger.info("test() waiting.");
		Awaitility.await().untilTrue(received);
		logger.info("test() completed.");
	}

	public Mono<ServerResponse> webFetch(ServerRequest request) {
		//return fetch(validator);
		return fetch(treasury+rss);
	}
	
	private Mono<ServerResponse> fetch(String url) {
		logger.info("fetch() url="+url);
		
		final WebClient webClient = WebClient.create(url);
		RequestHeadersSpec<?> request = webClient.get();
		request = request.accept(MediaType.APPLICATION_RSS_XML);
		Mono<String> mono = request.exchangeToMono(response -> response.bodyToMono(String.class).log());
		mono.subscribe();
		logger.info("fetch() mono="+mono);
		//Mono<String> fixedMono = Mono.just("fetch test").log();
		//logger.info("fetch() fixedMono="+fixedMono);		
		//Mono<ServerResponse> y = (s)->{ServerResponse.ok();};
		//BodyBuilder bodyBuilder = ServerResponse.ok();
		//Mono<ServerResponse> monoResponseBody = bodyBuilder.body(Mono.just("monoResponseBody"), String.class);
		//body(Mono.just("test"), String.class);
		//Function<String,Mono<ServerResponse>> mapper = s -> ServerResponse.ok().body(;
		//logger.info("fetch() mapper="+mapper);		
		
		//Mono<ServerResponse> response = ServerResponse.ok().body(fixedMono,String.class).log();
		//Mono<ServerResponse> response = fixedMono.flatMap(mapper);
		//logger.info("fetch() monoResponseBody="+monoResponseBody);		
		//return monoResponseBody;

		BodyInserter<String, ReactiveHttpOutputMessage> bodyInserter = BodyInserters.fromValue(url);
		Mono<ServerResponse> response = ServerResponse.ok().body(bodyInserter); 
		return response;
	}
	
	class Transform implements Function<String,Mono<ServerResponse>> {

		@Override
		public Mono<ServerResponse> apply(String s) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

	
	
}
