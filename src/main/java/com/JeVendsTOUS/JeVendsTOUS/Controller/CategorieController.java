package com.JeVendsTOUS.JeVendsTOUS.Controller;
import com.JeVendsTOUS.JeVendsTOUS.entity.Categorie;
import com.JeVendsTOUS.JeVendsTOUS.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Annotation utilisé pour gerer des requettes HTTP et produire des reponses au format JSON pour les services WEB RESTFUL
@RequestMapping(value="/Categorie",method = RequestMethod.GET) // cela difinit un point de terminaison pour les requettes HTTP vers URL /Categorie
public class CategorieController {
    @Autowired
    private CategorieService CategorieService;  // L'injection de l'instance categorie services

    @GetMapping// Cela est utilisé pour creer un point de terminaison d'API REST qui renvoie une liste de categorie au format JSON
    @ResponseBody
    public List<Categorie> getAllCategorie() {
        return CategorieService.getAllCategorie();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Categorie> getCategorieById(@PathVariable Long id) { // la methode getCategorieById est utilisé pour récupérer une categorie correspondant à l'identifiant fournit
        Categorie categorie = CategorieService.findById(id);
        return ResponseEntity.ok(categorie);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Categorie> createCategorie(@RequestBody Categorie categorie) { // La methode create est utilisé pour enregistrer un nouvel categorie en utilisant les données fournies dans le corps de la requête.
        return ResponseEntity.ok(CategorieService.save(categorie));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable Long id, @RequestBody Categorie categorieDetails) { // La methode update est utilisé pour modifier une categorie en utilisant les données fournies dans le corps de la requête.
        try {
            Categorie updatedCategorie = CategorieService.updateCategorie(id, categorieDetails);
            return ResponseEntity.ok(updatedCategorie);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategorie(@PathVariable Long id) {  // La methode delete est utilisé pour supprimer une categorie en utilisant l'ID
        try {
            CategorieService.deleteCategorie(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categorie n'existe pas");
        }
    }
}
