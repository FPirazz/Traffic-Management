package com.userContext.infrastructure_layer.springBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.*;
import org.springframework.transaction.annotation.*;

@SpringBootApplication
@EnableJpaRepositories(basePackages="com.userContext")
@EnableTransactionManagement
@EntityScan(basePackages="com.userContext")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
