package com.dhouib.foobarquix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class FoobarquixApplication {
    public static void main(String[] args) {
        SpringApplication.run(FoobarquixApplication.class, args);
    }
}
