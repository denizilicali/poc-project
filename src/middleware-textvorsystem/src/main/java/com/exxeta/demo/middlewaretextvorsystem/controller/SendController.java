package com.exxeta.demo.middlewaretextvorsystem.controller;

import com.exxeta.demo.middlewaretextvorsystem.entity.MiddlewareData;
import com.exxeta.demo.middlewaretextvorsystem.repository.DataRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class SendController {

    private final DataRepository dataRepository;
    private final JmsTemplate jmsTemplate;

    private final RestTemplate restTemplate;


    @PostMapping("/send")
    public String send(@RequestBody String body) {
        // 1) Save to database
        MiddlewareData data = new MiddlewareData();
        data.setContent(body);
        dataRepository.save(data);

        // 2) Send to ActiveMQ (for TesysMessageListener)
        jmsTemplate.convertAndSend("test.queue", body);

        // 3) Send directly to TeSyS via RestTemplate
        try {
            String response = restTemplate.postForObject("http://tesys:8084/tesys/get", body, String.class);
            System.out.println("[Middleware] Tesys response: " + response);
        } catch (Exception e) {
            System.out.println("[Middleware] Failed to send to Tesys directly: " + e.getMessage());
        }

        return "Content added to database, sent to ActiveMQ queue, and forwarded to Tesys!";
    }

    @GetMapping("/receive")
    public List<MiddlewareData> getAllData() {
        return dataRepository.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteContentById(@PathVariable Long id){
        dataRepository.deleteById(id);
        return true;
    }

    @GetMapping("/messages/count")
    public long count() {
        return dataRepository.count();
    }

    @GetMapping("/healthcheck")
    public String health() {
        return "Middleware service is running!";
    }
}
