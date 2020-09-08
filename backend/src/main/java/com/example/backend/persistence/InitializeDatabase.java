package com.example.backend.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class InitializeDatabase {

    private static final Logger log = LoggerFactory.getLogger(InitializeDatabase.class);

    @Bean
    CommandLineRunner initDatabase(MessageEntityRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new MessageEntity("Spring Boot", "Spring Boot Website", "Alireza", "https://spring.io/projects/spring-boot")));
            log.info("Preloading " + repository.save(new MessageEntity("JPA JSR", "JSR 338: Java Persistence 2.2", "Alireza", "https://jcp.org/en/jsr/detail?id=338")));
            log.info("Preloading " + repository.save(new MessageEntity("Home Page", "Personal Home Page", "Alireza", "https://sites.google.com/view/alireza-haghighatkhah/")));
            log.info("Preloading " + repository.save(new MessageEntity("LinkedIn", "LinkedIn Profile", "Alireza", "https://www.linkedin.com/in/haghighatkhah/")));
        };
    }
}