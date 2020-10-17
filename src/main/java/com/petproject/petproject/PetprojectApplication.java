package com.petproject.petproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication

public class PetprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetprojectApplication.class, args);
    }

}
