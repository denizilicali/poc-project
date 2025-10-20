package com.exxeta.demo.bestandsysteme.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController

public class PingController {

    private final RestTemplate restTemplate;

    public PingController(RestTemplate restTemplate) {
    this.restTemplate = restTemplate; }

    @Value("http://localhost:8082")
    String middlewareBase;

    // Manuel tetikleme: Bestandsysteme -> Middleware
    @PostMapping("/trigger")
    public String trigger(@RequestBody String body){
        return restTemplate.postForObject(middlewareBase + "/api/send", body, String.class);
    }

    @GetMapping("/ping")
    public String ping() {
        return "Bestandsysteme are working!"; }
}
