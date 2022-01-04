package com.example.treasurydirectrss;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.treasurydirectrss.external.Fetch;

@SpringBootTest
class TreasurydirectrssApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void fetch() {
		Fetch fetch = new Fetch();
		fetch.test();
		
	}
}
