package com.JeVendsTOUS.JeVendsTOUS.Controller;
import com.JeVendsTOUS.JeVendsTOUS.entity.Article;
import com.JeVendsTOUS.JeVendsTOUS.entity.Client;
import com.JeVendsTOUS.JeVendsTOUS.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Annotation utilisé pour gerer des requettes HTTP et produire des reponses au format JSON pour les services WEB RESTFUL
@RequestMapping(value="/client",method = RequestMethod.GET)  // cela difinit un point de terminaison pour les requettes HTTP vers URL /CLient
public class ClientController {

    @Autowired
    private ClientService ClientService; // L'injection de l'instance client services

    @GetMapping // Cela est utilisé pour creer un point de terminaison d'API REST qui renvoie une liste de client au format JSON
    @ResponseBody
    public List<Client> getAllClient() {
        return ClientService.getAllClient();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Client> getClientById(@PathVariable Long id) { // la methode getClientById est utilisé pour récupérer l'article correspondant à l'identifiant fournit
        Client client = ClientService.findById(id);
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {// La methode create est utilisé pour enregistrer un nouvel client en utilisant les données fournies dans le corps de la requête.
        Client savedClient = ClientService.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {  // La methode update est utilisé pour modifier un client en utilisant les données fournies dans le corps de la requête.

        try {
            Client updatedClient = ClientService.updateClient(id, clientDetails);
            return ResponseEntity.ok(updatedClient);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) { // La methode delete est utilisé pour supprimer un client en utilisant l'ID
        try {
            ClientService.deleteClient(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
    }
}
