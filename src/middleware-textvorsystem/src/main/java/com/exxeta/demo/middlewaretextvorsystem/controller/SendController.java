package com.exxeta.demo.middlewaretextvorsystem.controller;

import com.exxeta.demo.middlewaretextvorsystem.entity.Data;
import com.exxeta.demo.middlewaretextvorsystem.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class SendController {

    private final JmsTemplate jms;
    private final MessageRepository messageRepository;


    // Bestandsysteme bu endpointi çağırır:
    @PostMapping("/send")
    public String send(@RequestBody String body) {
        // 1) DB'ye yaz
        messageRepository.save(new Data(){{
            setPayload(body);
        }});
        // 2) ActiveMQ'ya kuyrukla
        jms.convertAndSend("queue.tesys", body);
        return "Added to the TeSyS queue!";
    }

    @GetMapping("/messages/count")
    public long count() {
        return messageRepository.count(); }
}
