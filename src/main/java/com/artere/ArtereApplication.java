package com.artere;

import com.artere.entities.CacheConfig;
import com.artere.entities.User;
import com.artere.repositories.CacheRepository;
import com.artere.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.artere.services"})
@EntityScan("com.artere.entities")
public class ArtereApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ArtereApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CacheRepository cacheRepository;

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User("nazih", "nazih@test.com"));
		userRepository.save(new User("oxana", "oxana@test.com"));
		userRepository.save(new User("karim", "karim@test.com"));

		cacheRepository.save(new CacheConfig("ttl", 20000));
		cacheRepository.save(new CacheConfig("timer", 50000));
		cacheRepository.save(new CacheConfig("maxItems", 10));

	}
}
