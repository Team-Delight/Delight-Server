package com.team.delightserver.config;

import com.team.delightserver.util.enumclass.CacheKey;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Created by Bloo
 * @Date: 2021/08/17
 */

@RequiredArgsConstructor
@Configuration
public class RedisCacheConfig {

    private final RedisConnectionFactory connectionFactory;

    @Bean
    public CacheManager redisCacheManager () {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration
            .defaultCacheConfig()
            .disableCachingNullValues()
            .entryTtl(Duration.ofSeconds(CacheKey.DEFAULT_EXPIRE_SEC))
            .computePrefixWith(CacheKeyPrefix.simple())

            .serializeKeysWith(
                RedisSerializationContext.SerializationPair
                    .fromSerializer(new StringRedisSerializer())
            )

            .serializeValuesWith(
                RedisSerializationContext.SerializationPair
                    .fromSerializer(new GenericJackson2JsonRedisSerializer())
            );

        /**
         * 데일리 랭킹을 위한 Cache Config 설정
         */
        Map<String, RedisCacheConfiguration> configurations = new HashMap<>();
        configurations.put(
            CacheKey.RECOMMENDATION_RANKING_KEY,
            RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(CacheKey.RANKING_EXPIRE_SEC))
        );

        return RedisCacheManager.RedisCacheManagerBuilder
            .fromConnectionFactory(connectionFactory)
            .withInitialCacheConfigurations(configurations)
            .cacheDefaults(redisCacheConfiguration)
            .build();
    }
}
