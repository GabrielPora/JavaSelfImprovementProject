package com.assessment.dbapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class DBApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DBApiApplication.class, args);
    }
}
