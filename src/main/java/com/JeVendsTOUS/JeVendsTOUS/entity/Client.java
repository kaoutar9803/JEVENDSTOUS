package com.JeVendsTOUS.JeVendsTOUS.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "Client")  //Annotation utilisé pour mappe la classe avec la table Client
public class Client {
    @Id
    @Column(name = "client_ID")  // Annotation utilisé pour mappé les column des table avec les attributs de l'entité
    private Long id;
    @Column(name = "nom")
    private String nom;
    @Column(name = "adresse")
    private String adresse;

    @OneToMany(cascade = CascadeType.ALL) // Annotation JsonManagedReference utilisé pour gerer la sérialisation et il est placé a coté de l'entité qui est proprietaire de la relation
    @JsonManagedReference
    private List<Devis> devis;

    // Getters et Setters des attributs
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }
    public void setAdresse(String  adresse) {
        this.adresse = adresse;
    }
    public String  getAdresse() {
        return adresse;
    }

    //Getters et setters pour  la relation Devis avec Client
    public List<Devis> getDevis() {
        return devis;
    }
    public void setDevis(List<Devis> devis) {
        this.devis = devis;
    }

}
