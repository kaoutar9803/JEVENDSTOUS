package com.JeVendsTOUS.JeVendsTOUS.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
@Entity
@Table(name = "Commercial") //Annotation utilisé pour mappe la classe avec la table Comercial
public class Commercial {
    @Id
    @Column(name = "Commercial_ID") // Annotation utilisé pour mappé les column des table avec les attributs de l'entité
    private Long id;
    @Column(name = "nomc")
    private String nomc;
    @Column(name = "prenomc")
    private String prenomc;
    @OneToOne
    @JoinColumn(name = "ROLE_UTILISATEUR_ID", referencedColumnName = "ROLE_UTILISATEUR_ID")
    @JsonBackReference // Annotation JsonBackReference Utilisé pour gerer la sérialisation des relations entre les objets pour eviter les boules infinie lors de la sérialisation JSON,il est placée du côté de l'entité qui n'est pas la propriétaire de la relation
    private RoleUtilisateur roleUtilisateur;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commercial")
    @JsonManagedReference // Annotation JsonManagedReference utilisé pour gerer la sérialisation et il est placé a coté de l'entité qui est proprietaire de la relation
    private List<Categorie> categories;

    // Getters et Setters des attributs
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setNomc(String nomc) {
        this.nomc = nomc;
    }
    public String getNomc() {
        return nomc;
    }
    public void setPrenomc(String prenomc) {
        this.prenomc = prenomc;
    }
    public String getPrenomc() {
        return prenomc;
    }

    //Getters et setters pour  les relations roleUtilisateur et categorie
    public void setRoleUtilisateur(RoleUtilisateur roleUtilisateur) {
        this.roleUtilisateur = roleUtilisateur;
    }
    public RoleUtilisateur getRoleUtilisateur() {
        return roleUtilisateur;
    }
    public List<Categorie> getCategorie() {
        return categories;
    }
    public void setCategorie(List<Categorie> categories) {
        this.categories = categories;
    }



}
