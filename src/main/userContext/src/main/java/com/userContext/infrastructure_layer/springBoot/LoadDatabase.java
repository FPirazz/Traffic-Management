package com.userContext.infrastructure_layer.springBoot;

import com.userContext.business_logic_layer.User;
import com.userContext.business_logic_layer.UserRole;
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
        };
    }
}
