package net.chandol.study;

import net.chandol.study._config.dummy.DummyDataGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

// integraionTest에서는 비활성화.
@Configuration
@Profile("!integration-test")
public class TestConfiguration {
    @Bean
    DummyDataGenerator dummyDataService() {
        return new DummyDataGenerator();
    }
}
