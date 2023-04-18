package com.amigoscode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController // We must annotate our class with this @RestController in order for the endpoints to work.
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }

    @GetMapping("/") // In order to expose our method to receive GET requests, we must use the @GetMapping annotation.
    public String greet() {
        return "Hello! Peaches Peaches Peaches Peaches Peaches!";
    }

    @GetMapping("/greet") // In order to expose our method to receive GET requests, we must use the @GetMapping annotation.
    public String greet2() {
        return "Hello! Peache understaaaaand!";
    }
}
