package com.JeVendsTOUS.JeVendsTOUS.services;

import com.JeVendsTOUS.JeVendsTOUS.entity.Article;
import com.JeVendsTOUS.JeVendsTOUS.entity.FamilleArticle;
import com.JeVendsTOUS.JeVendsTOUS.repository.FamilleArticleRepository;
import com.JeVendsTOUS.JeVendsTOUS.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FamilleArticleService {
    @Autowired
    private FamilleArticleRepository FamilleArticleRepository; // L'injection du l'instance FamilleArticle repository (qui puisse gerer l'entite FamilleArticle)
    @Autowired
    private ArticleRepository articleRepository;

    public List<FamilleArticle> getAllFamilleArticle() {
        return FamilleArticleRepository.findAll();
    }


    public FamilleArticle findById(Long id) {
        return FamilleArticleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FamilleArticle n'existe pas"));
    }

    public FamilleArticle save(FamilleArticle familleArticle) {
        // Gestion de la relation avec Article
        if (familleArticle.getArticle() != null) {
            for (Article article : familleArticle.getArticle()) {
                article.setFamilleArticle(familleArticle);
                articleRepository.save(article);
            }
        }
        return FamilleArticleRepository.save(familleArticle);
    }

    public FamilleArticle updateFamilleArticle(Long id, FamilleArticle familleArticleDetails) {
        FamilleArticle existingFamilleArticle = findById(id);
        existingFamilleArticle.setNom(familleArticleDetails.getNom());
        existingFamilleArticle.setDescription(familleArticleDetails.getDescription());

        // Mise à jour de la relation avec Article
        if (familleArticleDetails.getArticle() != null) {
            existingFamilleArticle.getArticle().clear();
            for (Article article : familleArticleDetails.getArticle()) {
                Article existingArticle = articleRepository.findById(article.getId())
                        .orElseThrow(() -> new RuntimeException("Article n'existe pas"));
                existingArticle.setFamilleArticle(existingFamilleArticle);
                existingFamilleArticle.getArticle().add(existingArticle);
            }
        }

        return FamilleArticleRepository.save(existingFamilleArticle);
    }

    public void deleteFamilleArticle(Long id) {
        FamilleArticle familleArticle = findById(id);
        FamilleArticleRepository.delete(familleArticle);
    }
}


