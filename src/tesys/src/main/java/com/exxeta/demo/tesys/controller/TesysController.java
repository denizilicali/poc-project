package com.exxeta.demo.tesys.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/tesys")
public class TesysController {

    @PostMapping("/get")
    public String getContent(@RequestBody String body) {

        return body.toString();
    }
}
