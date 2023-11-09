package com.ead.computers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
//@EntityScan(basePackages = "com.ead.computers.entities")
public class ComputersApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComputersApplication.class, args);
    }

}
