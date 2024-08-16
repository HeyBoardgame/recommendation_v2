package com.yeoboge.recommendation.infra.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class QueryDslTestConfig {
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    @ConditionalOnMissingBean
    public JPAQueryFactory queryFactory() {
        return new JPAQueryFactory(this.entityManager);
    }
}
