package com.JeVendsTOUS.JeVendsTOUS.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "LIGNEDEVIS") //Annotation utilisé pour mappe la classe avec la table LigneDevis
public class LigneDevis {
    @Id
    @Column(name = "LIGNEDEVIS_ID")
    private Long id;
    @Column(name = "QUANTITE")
    private Long quantite;
    @Column(name = "PRIXUNITAIREHT")
    private Long prixUnitaireHt;
    @ManyToOne(cascade = CascadeType.ALL) // Annotation ManyToOne et ses attributs est utilisé en JPA pour difinir la relation plusieurs à Un
    @JoinColumn(name = "DEVIS_ID")
    @JsonBackReference  // Annotation JsonBackReference Utilisé pour gerer la sérialisation des relations entre les objets pour eviter les boules infinie lors de la sérialisation JSON,il est placée du côté de l'entité qui n'est pas la propriétaire de la relation
    private Devis devis;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ARTICLE_ID")
    @JsonBackReference
    private Article article;

    // Getters et Setters des attributs
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setQuantite(Long quantite) {
        this.quantite = quantite;
    }

    public Long getQuantite() {
        return quantite;
    }
    public void setPrixUnitaireHt(Long prixUnitaireHt) {
        this.prixUnitaireHt = prixUnitaireHt;
    }

    public Long getPrixUnitaireHt() {
        return prixUnitaireHt;
    }

    //Getters et setters pour  la relation entre Article, Devis et LigneDevis
    public void setDevis(Devis devis) {
        this.devis = devis;
    }
    public Devis getDevis() {
        return devis;
    }
    public void setArticle(Article article) {
        this.article = article;
    }
    public Article getArticle() {
        return article;
    }
}
