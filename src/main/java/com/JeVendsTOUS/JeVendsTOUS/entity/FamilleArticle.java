package com.JeVendsTOUS.JeVendsTOUS.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "FAMILLE_ARTICLE") //Annotation utilisé pour mappe la classe avec la table FamilleArticle
public class FamilleArticle {
    @Id
    @Column(name = "FAMILLE_ARTICLE_ID") // Annotation utilisé pour mappé les column des table avec les attributs de l'entité
    private Long id;
    @Column(name = "nom")
    private String nom;
    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "familleArticle") // Annotation Onetomany et ses attributs est utilisé en JPA pour difinir la relation Un à plusieurs
    @JsonManagedReference  // Annotation JsonManagedReference utilisé pour gerer la sérialisation et il est placé a coté de l'entité qui est proprietaire de la relation
    private List<Article> article;

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

    //Getters et setters pour  la relation entre Famille Article et Article
    public List<Article> getArticle() {
        return article;
    }
    public void setArticle(List<Article> article) {
        this.article = article;
    }

}


