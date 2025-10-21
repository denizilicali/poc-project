package com.exxeta.demo.middlewaretextvorsystem.controller;

import com.exxeta.demo.middlewaretextvorsystem.entity.Data;
import com.exxeta.demo.middlewaretextvorsystem.repository.DataRepository;
import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class SendController {

    private final DataRepository dataRepository;
    private final JmsTemplate jmsTemplate;


    @PostMapping("/send")
    public String send(@RequestBody String body) {

        Data data = new Data();
        data.setContent(body);
        dataRepository.save(data);

        jmsTemplate.convertAndSend("test.queue", body);
        
        return "Content added to database and sent to ActiveMQ queue!";
    }

    @GetMapping("/receive")
    public List<Data> getAllData() {
        return dataRepository.findAll();
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
