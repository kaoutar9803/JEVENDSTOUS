package com.JeVendsTOUS.JeVendsTOUS.Controller;
import com.JeVendsTOUS.JeVendsTOUS.services.MonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HttpbinController {

    @Autowired
    private MonService monService;

    @GetMapping("/")
    public String home() {
        String result = monService.someMethod();
        return "Bienvenue sur JeVendsTOUS! Voici le résultat de someMethod: " + result;
    }

}
