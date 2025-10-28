package com.exxeta.demo.bestandsysteme.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.jms.core.JmsTemplate;
//import com.exxeta.demo.middlewaretextvorsystem.entity.MiddlewareData;

import java.util.List;

@RestController
@RequestMapping("/trigger")
public class PingController {

    @Value("${middleware.url:http://middleware-textvorsystem:8082}")
    private String middlewareUrl;

    private final RestTemplate restTemplate;
    private final JmsTemplate jmsTemplate;

    public PingController(RestTemplate restTemplate, JmsTemplate jmsTemplate) {
        this.restTemplate = restTemplate;
        this.jmsTemplate = jmsTemplate;
    }

    @PostMapping("/send")
    public String postContent(@RequestBody String body) {
         jmsTemplate.convertAndSend("content.queue", body);

         return "Content was sent to the Middleware and added to the ActiveMQ Queue. Content: " + body;
    }


    @GetMapping("/get")
    public String getContent(){
        return restTemplate.getForObject(middlewareUrl + "/api/messages", List.class).toString();
    }
/*
    @Value("${middleware.url:http://middleware-textvorsystem:8082}")
    private String middlewareUrl;



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

 */
}
