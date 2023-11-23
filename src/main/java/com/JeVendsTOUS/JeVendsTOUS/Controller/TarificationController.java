package com.JeVendsTOUS.JeVendsTOUS.Controller;
import com.JeVendsTOUS.JeVendsTOUS.services.TarificationExterneClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/tarification")
public class TarificationController {

//    @Autowired
//    private TarificationExterneClient tarificationExterneClient;
//    @GetMapping("/calculer-tarif")
//    public ResponseEntity<BigDecimal> calculerTarif(@RequestParam("articleId") Long articleId) {
//        BigDecimal tarif = tarificationExterneClient.obtenirTarif(articleId);
//        return ResponseEntity.ok(tarif);
//    }
}