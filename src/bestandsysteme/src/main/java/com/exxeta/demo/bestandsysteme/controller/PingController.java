package com.exxeta.demo.bestandsysteme.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
//import com.exxeta.demo.middlewaretextvorsystem.entity.MiddlewareData;

import java.util.List;

@RestController
@RequestMapping("/trigger")
public class PingController {

    private final RestTemplate restTemplate;

    @Value("${middleware.url:http://middleware-textvorsystem:8082}")
    private String middlewareUrl;

    public PingController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping()
    public String trigger(@RequestBody String body){
        return restTemplate.postForObject(middlewareUrl + "/api/send", body, String.class);
    }

    @GetMapping("/get")
    public String getContent(){
        return restTemplate.getForObject(middlewareUrl + "/api/receive", List.class).toString();
    }
//    @GetMapping("/trigger/get")
//    public List<MiddlewareData> getContent(){
//        return restTemplate.getForObject(middlewareUrl + "/api/receive", List.class);
//    }
    @DeleteMapping("/delete/{id}")
    public String deleteContentById(@PathVariable Long id){
        restTemplate.delete(middlewareUrl + "/api/delete/" + id);
       return "Content with id number " + id + " was deleted";
    }

    @GetMapping("/ping")
    public String ping() {
        return "Bestandsysteme are working!";
    }
}
