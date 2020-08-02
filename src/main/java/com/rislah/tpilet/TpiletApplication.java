package com.rislah.tpilet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Import(ApplicationConfig.class)
@EnableTransactionManagement
public class TpiletApplication {
    public static void main(String[] args) {
        SpringApplication.run(TpiletApplication.class, args);
    }
}
