package net.chandol.study._config.dummy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.annotation.PostConstruct;

// 테스트 데이터는 프로파일 "withDummyData"이 있으며 embedded database환경에서 동작한다.
@Configuration
@Profile("withDummyData")
@ConditionalOnClass(EmbeddedDatabaseType.class)
public class DummyDataCreator {

    @Bean
    DummyDataGenerator dummyDataService() {
        return new DummyDataGenerator();
    }

    @Autowired
    DummyDataGenerator service;

    @PostConstruct
    public void initData() {
        service.persistMovie();
        service.persistTheater();
    }
}
