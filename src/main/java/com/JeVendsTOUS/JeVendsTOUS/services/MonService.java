package com.JeVendsTOUS.JeVendsTOUS.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MonService {

    @Autowired
    private RestTemplate restTemplate;

    public String someMethod() {
        String url = "https://httpbin.org/get";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }
}