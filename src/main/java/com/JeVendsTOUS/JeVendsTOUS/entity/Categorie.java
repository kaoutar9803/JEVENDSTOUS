package com.JeVendsTOUS.JeVendsTOUS.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Categorie") //Annotation utilisé pour mappe la classe avec la table Categorie
public class Categorie {
    @Id
    @Column(name = "categorie_ID") // Annotation utilisé pour mappé les column des table avec les attributs de l'entité
    private Long id;
    @Column(name = "nom")
    private String nom;
    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categorie") // Annotation Onetomany et ses attributs est utilisé en JPA pour difinir la relation Un à plusieurs
    @JsonManagedReference  // Annotation JsonManagedReference utilisé pour gerer la sérialisation et il est placé a coté de l'entité qui est proprietaire de la relation
    private List<Article> article;
    @ManyToOne(cascade = CascadeType.ALL) // Annotation est pour difinir la relation Plusieurs à Un
    @JoinColumn(name = "COMMERCIAL_ID")
    @JsonBackReference // Annotation JsonBackReference Utilisé pour gerer la sérialisation des relations entre les objets pour eviter les boules infinie lors de la sérialisation JSON,il est placée du côté de l'entité qui n'est pas la propriétaire de la relation
    private Commercial commercial;
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
    public void setDescription(String  description) {
        this.description = description;
    }
    public String  getDescription() {
        return description;
    }

    //Getters et setters pour  les relations Article et Commercial
    public List<Article> getArticle() {
        return article;
    }
    public void setArticle(List<Article> article) {
        this.article = article;
    }
    public void setCommercial(Commercial  commercial) {
        this.commercial = commercial;
    }
    public Commercial  getCommercial() {
        return commercial;
    }

}
