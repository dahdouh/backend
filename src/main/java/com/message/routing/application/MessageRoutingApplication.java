package com.message.routing.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.message.routing"})
@EntityScan(basePackages = {"com.message.routing"})
@EnableJpaRepositories(basePackages = "com.message.routing")
public class MessageRoutingApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MessageRoutingApplication.class, args);
    }

}
