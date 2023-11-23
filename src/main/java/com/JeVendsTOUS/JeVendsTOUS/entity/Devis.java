package com.JeVendsTOUS.JeVendsTOUS.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "DEVIS") //Annotation utilisé pour mappe la classe avec la table Devis
public class Devis {
    @Id
    @Column(name = "DEVIS_ID")  // Annotation utilisé pour mappé les column des table avec les attributs de l'entité
    private Long id;
    @Column(name = "DATEDEVIS")
    private Date datedevis;
    @Column(name = "MONTANTHT")
    private Long montantHT;
    @Column(name = "ETAT")
    private String etat;

    @ManyToOne(fetch = FetchType.EAGER) // Annotation ManyToOne et ses attributs est utilisé en JPA pour difinir la relation plusieurs à Un
    @JoinColumn(name = "CLIENT_ID")
    @JsonBackReference // Annotation JsonBackReference Utilisé pour gerer la sérialisation des relations entre les objets pour eviter les boules infinie lors de la sérialisation JSON,il est placée du côté de l'entité qui n'est pas la propriétaire de la relation
    private Client client;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "devis") // Annotation Onetomany et ses attributs est utilisé en JPA pour difinir la relation Un à plusieurs
    @JsonManagedReference  // Annotation JsonManagedReference utilisé pour gerer la sérialisation et il est placé a coté de l'entité qui est proprietaire de la relation
    private List<LigneDevis> ligneDevis;

    // Getters et Setters des attributs
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setDatedevis(Date datedevis) {
        this.datedevis = datedevis;
    }
    public Date getDatedevis() {
        return datedevis;
    }
    public void setMontantHT(Long montantHT) {
        this.montantHT = montantHT;
    }
    public Long getMontantHT() {
        return montantHT;
    }
    public void setEtat(String etat) {
        this.etat = etat;
    }
    public String getEtat() {
        return etat;
    }

    //Getters et setters pour  les relations Client et LigneDevis
    public void setClient(Client client) {
        this.client = client;
    }
    public Client getClient() {
        return client;
    }
    public List<LigneDevis> getLigneDevis() {
        return ligneDevis;
    }
    public void setLigneDevis(List<LigneDevis> ligneDevis) {
        this.ligneDevis = ligneDevis;
    }

}
