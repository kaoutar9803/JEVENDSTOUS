package com.JeVendsTOUS.JeVendsTOUS.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ROLE_UTILISATEUR")
public class RoleUtilisateur {
    @Id
    @Column(name = "ROLE_UTILISATEUR_ID")
    private Long id;
    @Column(name = "ROLE")
    private String role;

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setRole(String  role) {
        this.role = role;
    }
    public String  getRole() {
        return role;
    }

}
