package com.artere.repositories;

import com.artere.entities.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CacheRepository extends JpaRepository<CacheConfig, String> {
}
