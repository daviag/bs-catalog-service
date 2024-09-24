package com.daviag.bookshop.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class BookCatalogServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookCatalogServiceApplication.class, args);
    }
}