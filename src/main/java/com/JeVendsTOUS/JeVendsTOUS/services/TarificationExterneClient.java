package com.JeVendsTOUS.JeVendsTOUS.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;


@Component
public class TarificationExterneClient {
//    private final RestTemplate restTemplate;
//    private final String externalApiUrl;
//    public TarificationExterneClient(
//            RestTemplate restTemplate,
//            @Value("${external.api.url}") String externalApiUrl) {
//        this.restTemplate = restTemplate;
//        this.externalApiUrl = externalApiUrl;
//    }
//
//    public BigDecimal obtenirTarif(Long articleId) {
//       String apiUrl = externalApiUrl + "/calculate?articleId=" + articleId;
//        ResponseEntity<BigDecimal> response = restTemplate.getForEntity(apiUrl, BigDecimal.class);
//
//        if (response.getStatusCode() == HttpStatus.OK) {
//            return response.getBody();
//        } else {
//            throw new RuntimeException("API innaccessible ");
//        }
//    }
//

}