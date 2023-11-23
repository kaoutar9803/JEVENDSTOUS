package com.JeVendsTOUS.JeVendsTOUS.Controller;
import com.JeVendsTOUS.JeVendsTOUS.entity.RoleUtilisateur;
import com.JeVendsTOUS.JeVendsTOUS.services.RoleUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/RoleUtilisateur")
public class RoleUtilisateurController {
    @Autowired
    private RoleUtilisateurService RoleUtilisateurService;

    @GetMapping()
    public List<RoleUtilisateur> getAllRoleUtilisateur() {
        return RoleUtilisateurService.getAllRoleUtilisateur();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleUtilisateur> getRoleUtilisateurById(@PathVariable Long id) {
        RoleUtilisateur roleUtilisateur = RoleUtilisateurService.findById(id);
        return ResponseEntity.ok(roleUtilisateur);
    }

    @PostMapping
    public ResponseEntity<RoleUtilisateur> createRoleUtilisateur(@RequestBody RoleUtilisateur roleUtilisateur) {
        RoleUtilisateur savedRoleUtilisateur = RoleUtilisateurService.save(roleUtilisateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRoleUtilisateur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleUtilisateur> updateRoleUtilisateur(@PathVariable Long id, @RequestBody RoleUtilisateur roleUtilisateurDetails) {
        RoleUtilisateur updatedRoleUtilisateur = RoleUtilisateurService.updateRoleUtilisateur(id, roleUtilisateurDetails);
        return ResponseEntity.ok(updatedRoleUtilisateur);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoleUtilisateur(@PathVariable Long id) {
        RoleUtilisateurService.deleteRoleUtilisateur(id);
        return ResponseEntity.ok().build();
    }
}
