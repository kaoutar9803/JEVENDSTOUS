package com.JeVendsTOUS.JeVendsTOUS.services;
import com.JeVendsTOUS.JeVendsTOUS.entity.Client;
import com.JeVendsTOUS.JeVendsTOUS.entity.Devis;
import com.JeVendsTOUS.JeVendsTOUS.entity.LigneDevis;
import com.JeVendsTOUS.JeVendsTOUS.repository.DevisRepository;
import com.JeVendsTOUS.JeVendsTOUS.repository.ClientRepository;
import com.JeVendsTOUS.JeVendsTOUS.repository.LigneDevisRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class DevisService {
    @Autowired
    private DevisRepository DevisRepository; // L'injection du l'instance devis repository (qui puisse gerer l'entite devis)
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private LigneDevisRepository ligneDevisRepository;


    public List<Devis> getAllDevis() {
        return DevisRepository.findAll();
    }

    public Devis findById(Long id) {
        return DevisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Devis n'existe pas"));
    }
    public Devis save(Devis devis) {
        // Gestion de la relation avec Client
        if (devis.getClient() != null && devis.getClient().getId() != null) {
            Client client = clientRepository.findById(devis.getClient().getId())
                    .orElseThrow(() -> new RuntimeException("Client n'existe pas"));
            devis.setClient(client);
        }
        return DevisRepository.save(devis);
    }

    public Devis updateDevis(Long id, Devis devisDetails) {
        Devis existingDevis = DevisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Devis n'existe pas"));

        existingDevis.setDatedevis(devisDetails.getDatedevis());
        existingDevis.setMontantHT(devisDetails.getMontantHT());
        existingDevis.setEtat(devisDetails.getEtat());

        // Gestion de la relation avec Client
        if (devisDetails.getClient() != null && devisDetails.getClient().getId() != null) {
            Client client = clientRepository.findById(devisDetails.getClient().getId())
                    .orElseThrow(() -> new RuntimeException("Client n'existe pas"));
            existingDevis.setClient(client);
        }

        // Gestion de la mise à jour des lignes de devis associées
        if (devisDetails.getLigneDevis() != null) {
            List<LigneDevis> lignes = new ArrayList<>();
            for (LigneDevis ligne : devisDetails.getLigneDevis()) {
                LigneDevis existingLigne = ligneDevisRepository.findById(ligne.getId())
                        .orElseThrow(() -> new RuntimeException("LigneDevis n'existe pas"));
                existingLigne.setDevis(existingDevis);
                lignes.add(existingLigne);
            }
            existingDevis.setLigneDevis(lignes);
        }

        return DevisRepository.save(existingDevis);
    }

    public void deleteDevis(Long id) {
        Devis devis = DevisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Devis n'existe pas"));
        DevisRepository.delete(devis);
    }
}
