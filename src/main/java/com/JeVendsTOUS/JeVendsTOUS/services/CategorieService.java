package com.JeVendsTOUS.JeVendsTOUS.services;
import com.JeVendsTOUS.JeVendsTOUS.entity.Article;
import com.JeVendsTOUS.JeVendsTOUS.entity.Categorie;
import com.JeVendsTOUS.JeVendsTOUS.entity.Client;
import com.JeVendsTOUS.JeVendsTOUS.entity.Commercial;
import com.JeVendsTOUS.JeVendsTOUS.repository.CategorieRepository;
import com.JeVendsTOUS.JeVendsTOUS.repository.CommercialRepository;
import com.JeVendsTOUS.JeVendsTOUS.repository.ArticleRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategorieService {

    @Autowired // L'injection du l'instance Categorie repository (qui puisse gerer l'entite categorie)
    private CategorieRepository CategorieRepository;
    @Autowired
    private CommercialRepository commercialRepository;
    @Autowired
    private ArticleRepository ArticleRepository;

    // Méthode pour récuperer toutes les elements de la table Categorie
    public List<Categorie> getAllCategorie() {
        return CategorieRepository.findAll();
    }

    //Méthode de recherche d'un categorie dans la base de donnnees en utilisant l'identifiant ID
    public Categorie findById(Long id) {
        return CategorieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("categorie n'existe pas"));
    }

    // Méthode Save est utilisé pour sauvegarder un nouvel categorie  dans la BD en utilisant les relations entre les tables
    public Categorie save(Categorie categorie) {

        // Gestion de la relation avec Commercial
        if (categorie.getCommercial() != null && categorie.getCommercial().getId() != null) {
            Commercial commercial = commercialRepository.findById(categorie.getCommercial().getId())
                    .orElseThrow(() -> new RuntimeException("Commercial n'existe pas"));
            categorie.setCommercial(commercial);
        }
        return CategorieRepository.save(categorie);
    }

    public Categorie updateCategorie(Long id, Categorie categorieDetails) {
        Categorie existingCategorie = CategorieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categorie n'existe pas"));

        existingCategorie.setNom(categorieDetails.getNom());
        existingCategorie.setDescription(categorieDetails.getDescription());

        // Gestion de la relation avec Commercial
        if (categorieDetails.getCommercial() != null && categorieDetails.getCommercial().getId() != null) {
            Commercial commercial = commercialRepository.findById(categorieDetails.getCommercial().getId())
                    .orElseThrow(() -> new RuntimeException("Commercial n'existe pas"));
            existingCategorie.setCommercial(commercial);
        }

        // Gestion des articles associés à cette catégorie
        if (categorieDetails.getArticle() != null && !categorieDetails.getArticle().isEmpty()) {
            List<Article> articles = new ArrayList<>();
            for (Article article : categorieDetails.getArticle()) {
                Article existingArticle = ArticleRepository.findById(article.getId())
                        .orElseThrow(() -> new RuntimeException("Article n'existe pas"));
                articles.add(existingArticle);
            }
            existingCategorie.setArticle(articles);
        }

        return CategorieRepository.save(existingCategorie);
    }

    //Methode est pour supprimer l'objet a partir d'un ID
    public void deleteCategorie(Long id) {
        Categorie categorie = CategorieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categorie n'existe pas"));
        CategorieRepository.delete(categorie);
    }
}
