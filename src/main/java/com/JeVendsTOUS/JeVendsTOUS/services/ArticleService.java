package com.JeVendsTOUS.JeVendsTOUS.services;
import com.JeVendsTOUS.JeVendsTOUS.entity.Article;
import com.JeVendsTOUS.JeVendsTOUS.entity.Categorie;
import com.JeVendsTOUS.JeVendsTOUS.entity.FamilleArticle;
import com.JeVendsTOUS.JeVendsTOUS.repository.ArticleRepository;
import com.JeVendsTOUS.JeVendsTOUS.repository.FamilleArticleRepository;
import com.JeVendsTOUS.JeVendsTOUS.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository ArticleRepository; // L'injection du l'instance Article repository (qui puisse gerer l'entite Article)
    @Autowired
    private FamilleArticleRepository familleArticleRepository;
    @Autowired
    private CategorieRepository categorieRepository;
   // Méthode pour récuperer toutes les elements de la table article
    public List<Article> getAllArticles() {
        return ArticleRepository.findAll();
    }
    //Méthode de recherche d'un article dans la base de donnnees en utilisant l'identifiant ID
    public Article findById(Long id) {
        return ArticleRepository.findById(id).orElseThrow(() -> new RuntimeException("Article n'existe pas"));
    }
    // Méthode Save est utilisé pour sauvegarder un nouvel objet Article dans la BD en utilisant les relations entre les tables
    public Article save(Article article) {
        // Gestion de la relation avec FamilleArticle
        if (article.getFamilleArticle() != null && article.getFamilleArticle().getId() != null) {
            FamilleArticle famille = familleArticleRepository.findById(article.getFamilleArticle().getId())
                    .orElseThrow(() -> new RuntimeException("FamilleArticle n'existe pas"));
            article.setFamilleArticle(famille);
        }
        // Gestion des categories associés à ces Articles
        if (article.getCategorie() != null && article.getCategorie().getId() != null) {
            Categorie categorie = categorieRepository.findById(article.getCategorie().getId())
                    .orElseThrow(() -> new RuntimeException("Categorie n'existe pas"));
            article.setCategorie(categorie);
        }
        return ArticleRepository.save(article);
    }
    // Methode Update est pour modifier les objets de la tale Article en verifiant les relations entre les tables
    public Article updateArticle(Long id, Article articleDetails) {
        Article existingArticle = ArticleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));
        existingArticle.setNom(articleDetails.getNom());
        existingArticle.setDescription(articleDetails.getDescription());
        existingArticle.setPrixHT(articleDetails.getPrixHT());

        if (articleDetails.getFamilleArticle() != null && articleDetails.getFamilleArticle().getId() != null) {
            FamilleArticle famille = familleArticleRepository.findById(articleDetails.getFamilleArticle().getId())
                    .orElseThrow(() -> new RuntimeException("FamilleArticle n'existe pas"));
            existingArticle.setFamilleArticle(famille);
        }
        if (articleDetails.getCategorie() != null && articleDetails.getCategorie().getId() != null) {
            Categorie categorie = categorieRepository.findById(articleDetails.getCategorie().getId())
                    .orElseThrow(() -> new RuntimeException("Categorie n'existe pas"));
            existingArticle.setCategorie(categorie);
        }
        return ArticleRepository.save(existingArticle);
    }
    //Methode est pour supprimer l'objet a partir d'un ID
    public void deleteArticle(Long id) {
        Article article = ArticleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article N'existe pas"));
        ArticleRepository.delete(article);
    }
}



