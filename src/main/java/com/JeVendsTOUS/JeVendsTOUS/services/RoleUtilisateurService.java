package com.JeVendsTOUS.JeVendsTOUS.services;

import com.JeVendsTOUS.JeVendsTOUS.entity.RoleUtilisateur;
import com.JeVendsTOUS.JeVendsTOUS.repository.RoleUtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleUtilisateurService {
    @Autowired
    private RoleUtilisateurRepository RoleUtilisateurRepository;
   public List<RoleUtilisateur> getAllRoleUtilisateur() {
        return RoleUtilisateurRepository.findAll();
    }

    public RoleUtilisateur findById(Long id) {
        return RoleUtilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RoleUtilisateur not found"));
    }

    public RoleUtilisateur save(RoleUtilisateur roleUtilisateur) {
        return RoleUtilisateurRepository.save(roleUtilisateur);
    }

    public RoleUtilisateur updateRoleUtilisateur(Long id, RoleUtilisateur roleUtilisateurDetails) {
        RoleUtilisateur existingRoleUtilisateur = findById(id);
        existingRoleUtilisateur.setRole(roleUtilisateurDetails.getRole());
        return RoleUtilisateurRepository.save(existingRoleUtilisateur);
    }

    public void deleteRoleUtilisateur(Long id) {
        RoleUtilisateur roleUtilisateur = findById(id);
        RoleUtilisateurRepository.delete(roleUtilisateur);
    }


}
