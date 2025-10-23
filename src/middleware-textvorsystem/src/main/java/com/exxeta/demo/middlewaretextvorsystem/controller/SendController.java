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

        MiddlewareData data = new MiddlewareData();
        data.setContent(body);
        dataRepository.save(data);

        jmsTemplate.convertAndSend("test.queue", body);

        //restTemplate.postForObject("http://localhost:8084/tesys/get", body, String.class);

        return "Content added to database and sent to ActiveMQ queue!";
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
