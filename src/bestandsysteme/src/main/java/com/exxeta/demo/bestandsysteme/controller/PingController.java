package com.exxeta.demo.bestandsysteme.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class PingController {

    private final RestTemplate restTemplate;

    @Value("${middleware.url:http://middleware-textvorsystem:8082}")
    private String middlewareUrl;

    public PingController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/trigger")
    public String trigger(@RequestBody String body){
        return restTemplate.postForObject(middlewareUrl + "/api/send", body, String.class);
    }

    @GetMapping("/trigger/get")
    public String getContent(){
        return restTemplate.getForObject(middlewareUrl + "/api/receive", List.class).toString();
    }

    @GetMapping("/ping")
    public String ping() {
        return "Bestandsysteme are working!";
    }
}
