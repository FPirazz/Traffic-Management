package com.userContext.infrastructure_layer.springBoot;

import com.userContext.business_logic_layer.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository repo) {
        return args -> {
            log.info("Preloading " + repo.save(new User("Nome Test 1", "Cognome Test 1", "Driver")));
            log.info("Preloading " + repo.save(new User("Nome Test 2", "Cognome Test 2", "Operator")));
        };
    }
}
