package com.rickyslash.nbs.common.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfiguration {
  @Bean
  public CacheManager cacheManager() {
    ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();

    cacheManager.setAllowNullValues(false);
    cacheManager.setCacheNames(List.of(
        "productCache"
    ));

    return cacheManager;
  }

  @CacheEvict(value = "productCache", allEntries = true)
  @Scheduled(fixedDelay = 60000, initialDelay = 0)
  public void evictProductCache() {
    System.out.println("evictProductCache");
  }
}
