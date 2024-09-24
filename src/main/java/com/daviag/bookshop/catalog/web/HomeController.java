package com.daviag.bookshop.catalog.web;

import com.daviag.bookshop.catalog.config.BSProperties;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HomeController {

    private final BSProperties bsProperties;

    @GetMapping("/")
    public String getGreeting() {
        return bsProperties.getGreeting();
    }

}
