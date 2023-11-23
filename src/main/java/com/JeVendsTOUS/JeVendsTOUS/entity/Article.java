package com.JeVendsTOUS.JeVendsTOUS.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "article")  //Annotation utilisé pour mappe la classe avec la table Article
public class Article {
    @Id
    @Column(name = "article_ID") // Annotation utilisé pour mappé les column des table avec les attributs de l'entité
    private Long id;
    @Column(name = "nom")
    private String nom;
    @Column(name = "description")
    private String description;
    @Column(name = "PRIXHT")
    private Long prixHT;
    @ManyToOne(cascade = CascadeType.ALL) // Annotation est pour difinir la relation Plusieurs à Un
    @JoinColumn(name = "FAMILLE_ARTICLE_ID" )
//    @JsonBackReference // Annotation JsonBackReference Utilisé pour gerer la sérialisation des relations entre les objets pour eviter les boules infinie lors de la sérialisation JSON,il est placée du côté de l'entité qui n'est pas la propriétaire de la relation
    private FamilleArticle familleArticle;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORIE_ID")
//    @JsonBackReference // Annotation JsonBackReference Utilisé pour gerer la sérialisation des relations entre les objets pour eviter les boules infinie lors de la sérialisation JSON,il est placée du côté de l'entité qui n'est pas la propriétaire de la relation
    private Categorie categorie;

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
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    public void setPrixHT(Long prixHT) {
        this.prixHT = prixHT;
    }
    public Long getPrixHT() {
        return prixHT;
    }

    //Getters et setters pour  les relations FamilleArticle et Categorie
    public void setFamilleArticle(FamilleArticle familleArticle) {
        this.familleArticle = familleArticle;
    }
    public FamilleArticle getFamilleArticle() {
        return familleArticle;
    }
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    public Categorie getCategorie() {
        return categorie;
    }
}
