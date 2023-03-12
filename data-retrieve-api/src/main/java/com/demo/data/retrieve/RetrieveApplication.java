package com.demo.data.retrieve;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableWebMvc
@OpenAPIDefinition
public class RetrieveApplication {

    public static void main(String[] args) {
        SpringApplication.run(RetrieveApplication.class, args);
    }

}