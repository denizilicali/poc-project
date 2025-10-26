package com.exxeta.demo.bestandsysteme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BestandsystemeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BestandsystemeApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
//    @Bean
//    public JmsTemplate jmsTemplate() {
//        return new JmsTemplate();
//    }
}
