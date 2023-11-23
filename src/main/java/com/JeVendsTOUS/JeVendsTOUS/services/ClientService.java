package com.JeVendsTOUS.JeVendsTOUS.services;

import com.JeVendsTOUS.JeVendsTOUS.entity.Article;
import com.JeVendsTOUS.JeVendsTOUS.entity.Client;
import com.JeVendsTOUS.JeVendsTOUS.entity.Devis;

import com.JeVendsTOUS.JeVendsTOUS.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientService {
    @Autowired // L'injection du l'instance Client repository (qui puisse gerer l'entite Client)
    private ClientRepository ClientRepository;

    public List<Client> getAllClient() {  // Méthode pour récuperer toutes les elements de la table Client
        return ClientRepository.findAll();
    }

    public Client findById(Long id) { //Méthode de recherche d'un client dans la base de donnnees en utilisant l'identifiant ID
        return ClientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client n'existe pas"));
    }

    public Client save(Client client) {  // Méthode Save est utilisé pour sauvegarder un nouvel client  dans la BD en utilisant les relations entre les tables
        // gestion des devis associés au client lors de l'enregistrement
        if (client.getDevis() != null && !client.getDevis().isEmpty()) {
            for (Devis devis : client.getDevis()) {
                devis.setClient(client);
            }
        }
        return ClientRepository.save(client);
    }

    public Client updateClient(Long id, Client clientDetails) { // Méthode update est utilisé pour modifier un client  dans la BD en utilisant les relations entre les tables
        Client existingClient = ClientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client n'existe pas"));

        existingClient.setNom(clientDetails.getNom());
        existingClient.setAdresse(clientDetails.getAdresse());

        // Gestion de la relation client avec devisv
        if (clientDetails.getDevis() != null && !clientDetails.getDevis().isEmpty()) {
            for (Devis devis : clientDetails.getDevis()) {
                devis.setClient(existingClient);
            }
            existingClient.setDevis(clientDetails.getDevis());
        }

        return ClientRepository.save(existingClient);
    }

    public void deleteClient(Long id) {    //Methode est pour supprimer l'objet a partir d'un ID
        Client client = ClientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client n'existe pas"));
        ClientRepository.delete(client);
    }
}
