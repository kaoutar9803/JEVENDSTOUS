package com.JeVendsTOUS.JeVendsTOUS.services;
import com.JeVendsTOUS.JeVendsTOUS.entity.Categorie;
import com.JeVendsTOUS.JeVendsTOUS.entity.Commercial;
import com.JeVendsTOUS.JeVendsTOUS.entity.Devis;
import com.JeVendsTOUS.JeVendsTOUS.repository.CategorieRepository;
import com.JeVendsTOUS.JeVendsTOUS.repository.CommercialRepository;
import com.JeVendsTOUS.JeVendsTOUS.repository.RoleUtilisateurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.JeVendsTOUS.JeVendsTOUS.entity.RoleUtilisateur;


import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class CommercialService {
    @Autowired
    private  CommercialRepository CommercialRepository; // L'injection du l'instance commercial repository (qui puisse gerer l'entite commercial)
    @Autowired
    private RoleUtilisateurRepository roleUtilisateurRepository;
    @Autowired
    private CategorieRepository categorieRepository;
    public List<Commercial> getAllCommercial() {
        return CommercialRepository.findAll();
    }

    public Commercial findById(Long id) {
        return CommercialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commercial n'existe pas"));
    }


    public Commercial saveCommercial(Commercial commercial) {
        // gestion de la relation avec RoleUtilisateur
        if (commercial.getRoleUtilisateur() != null && commercial.getRoleUtilisateur().getId() != null) {
            RoleUtilisateur role = roleUtilisateurRepository.findById(commercial.getRoleUtilisateur().getId())
                    .orElseThrow(() -> new RuntimeException("RoleUtilisateur n'existe pas"));
            commercial.setRoleUtilisateur(role);
        }
        return CommercialRepository.save(commercial);
    }


    public Commercial updateCommercial(Long id, Commercial commercialDetails) {
        Commercial existingCommercial = CommercialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commercial  n'existe pas"));

        existingCommercial.setNomc(commercialDetails.getNomc());
        existingCommercial.setPrenomc(commercialDetails.getPrenomc());

        // gestion de la relation avec RoleUtilisateur
        if (commercialDetails.getRoleUtilisateur() != null && commercialDetails.getRoleUtilisateur().getId() != null) {
            RoleUtilisateur role = roleUtilisateurRepository.findById(commercialDetails.getRoleUtilisateur().getId())
                    .orElseThrow(() -> new RuntimeException("RoleUtilisateur  n'existe pas"));
            existingCommercial.setRoleUtilisateur(role);
        }

        // gestion de la mise à jour des catégories associées
        if (commercialDetails.getCategorie() != null && !commercialDetails.getCategorie().isEmpty()) {
            List<Categorie> categories = new ArrayList<>();
            for (Categorie categorie : commercialDetails.getCategorie()) {
                Categorie existingCategorie = categorieRepository.findById(categorie.getId())
                        .orElseThrow(() -> new RuntimeException("Categorie n'existe pas"));
                categories.add(existingCategorie);
            }
            existingCommercial.setCategorie(categories);
        }

        return CommercialRepository.save(existingCommercial);
    }
    public void deleteCommercial(Long id) {
        Commercial commercial = CommercialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commercial not found"));
        CommercialRepository.delete(commercial);
    }
}
