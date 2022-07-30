package ru.kondratyeva.springcourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class BookkeepingApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookkeepingApplication.class, args);
    }
}
