package com.exxeta.demo.middlewaretextvorsystem.controller;

import com.exxeta.demo.middlewaretextvorsystem.entity.Data;
import com.exxeta.demo.middlewaretextvorsystem.repository.DataRepository;
import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class SendController {

    private final DataRepository dataRepository;
    private final JmsTemplate jmsTemplate;

    // Bestandsysteme bu endpointi çağırır:
    @PostMapping("/send")
    public String send(@RequestBody String body) {
        // 1) DB'ye yaz
        Data data = new Data();
        data.setPayload(body);
        dataRepository.save(data);
        
        // 2) ActiveMQ'ya kuyrukla
        jmsTemplate.convertAndSend("test.queue", body);
        
        return "Added to database and sent to ActiveMQ queue!";
    }

    @GetMapping("/messages/count")
    public long count() {
        return dataRepository.count();
    }

    @GetMapping("/health")
    public String health() {
        return "Middleware service is running!";
    }
}
