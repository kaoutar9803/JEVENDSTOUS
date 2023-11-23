package com.JeVendsTOUS.JeVendsTOUS.Controller;
import com.JeVendsTOUS.JeVendsTOUS.entity.Commercial;
import com.JeVendsTOUS.JeVendsTOUS.services.CommercialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController //Annotation utilisé pour gerer des requettes HTTP et produire des reponses au format JSON pour les services WEB RESTFUL
@RequestMapping(value="/commercial" ,method = RequestMethod.GET) // cela difinit un point de terminaison pour les requettes HTTP vers URL /Commercial
public class CommercialController {
    @Autowired
    private CommercialService CommercialService; // L'injection de l'instance commercial services

    @GetMapping // Cela est utilisé pour creer un point de terminaison d'API REST qui renvoie une liste de commerciaux au format JSON
    @ResponseBody
    public List<Commercial> getAllCommercial() {
        return CommercialService.getAllCommercial();
    }
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Commercial> getCommercialById(@PathVariable Long id) {
        // la methode getArticleById est utilisé pour récupérer un commercial correspondant à l'identifiant fournit
        Commercial commercial = CommercialService.findById(id);
        return ResponseEntity.ok(commercial);
    }

    @PostMapping
    public ResponseEntity<Commercial> createCommercial(@RequestBody Commercial commercial) {
        // La methode create est utilisé pour enregistrer un nouvel commercial en utilisant les données fournies dans le corps de la requête.
        Commercial savedCommercial = CommercialService.saveCommercial(commercial);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCommercial);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Commercial> updateCommercial(@PathVariable Long id, @RequestBody Commercial commercialDetails) {
        // La methode update est utilisé pour modifier un commercial en utilisant les données fournies dans le corps de la requête.
        Commercial updatedCommercial = CommercialService.updateCommercial(id, commercialDetails);
        return ResponseEntity.ok(updatedCommercial);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommercial(@PathVariable Long id) {
        // La methode delete est utilisé pour supprimer un commercial en utilisant l'ID

        CommercialService.deleteCommercial(id);
        return ResponseEntity.ok().build();
    }
}
