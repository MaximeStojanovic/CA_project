package org.sfeir.maxime.mqapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@SpringBootApplication
@RestController
public class MqAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqAppApplication.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to Spring Boot Application!";
    }
}