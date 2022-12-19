package services;

import com.artere.entities.CacheConfig;
import com.artere.repositories.CacheRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Data
public class CacheConfigService implements CommandLineRunner {
    @Autowired
    private CacheRepository cacheRepository;

    private InMemoryService inMemoryService;

    public Optional<CacheConfig> getConfig (String key) {
        return  cacheRepository.findById(key);
    }

    public Integer getTtl() {
       return this.getConfig("ttl").isPresent() ? this.getConfig("ttl").get().getValue() : 10000;
    }

    public Integer getTime() {
        return this.getConfig("timer").isPresent() ? this.getConfig("timer").get().getValue() : 50000;
    }

    public Integer getMaxItems() {
        return this.getConfig("maxItems").isPresent() ? this.getConfig("maxItems").get().getValue() : 10;
    }

    @Override
    public void run(String... args) throws Exception {
        inMemoryService = new InMemoryService<>(getTtl(), getTime(), getMaxItems());
    }
}
