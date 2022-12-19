package com.artere;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import services.InMemoryService;

@SpringBootTest
class ArtereApplicationTests {

	@Test
	public void contextLoads() {
		InMemoryService<String, String> cache = new InMemoryService<>(200, 500, 6);
		cache.put("nazih", "nazih@test.com");
		cache.put("oxana", "oxana@test.com");
		cache.put("karim", "karim@test.com");

		Assert.assertEquals(3, cache.size());

		cache.remove("karim");
		Assert.assertEquals(2, cache.size());
	}

}
