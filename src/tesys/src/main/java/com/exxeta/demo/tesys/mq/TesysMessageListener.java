package com.exxeta.demo.tesys.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TesysMessageListener {

    @JmsListener(destination = "queue.tesys")
    public void handle(String payload) {
        System.out.println("[TeSyS] received the data via ActiveMQ: " + payload);

    }
}