package com.JeVendsTOUS.JeVendsTOUS.services;

import com.JeVendsTOUS.JeVendsTOUS.entity.LigneDevis;
import com.JeVendsTOUS.JeVendsTOUS.entity.Article;
import com.JeVendsTOUS.JeVendsTOUS.entity.Devis;
import com.JeVendsTOUS.JeVendsTOUS.repository.ArticleRepository;
import com.JeVendsTOUS.JeVendsTOUS.repository.DevisRepository;
import com.JeVendsTOUS.JeVendsTOUS.repository.LigneDevisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LigneDevisService {
    @Autowired
    private LigneDevisRepository LigneDevisRepository;
    @Autowired
    private DevisRepository devisRepository;
    @Autowired
    private ArticleRepository articleRepository;
    public List<LigneDevis> getAllLigneDevis() {
        return LigneDevisRepository.findAll();
    }

    public LigneDevis findById(Long id) {
        return LigneDevisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("LigneDevis n'existe pas"));
    }
    public LigneDevis save(LigneDevis ligneDevis) {
        handleRelations(ligneDevis);
        return LigneDevisRepository.save(ligneDevis);
    }

    public LigneDevis updateLigneDevis(Long id, LigneDevis ligneDevisDetails) {
        LigneDevis existingLigneDevis = findById(id);
        existingLigneDevis.setQuantite(ligneDevisDetails.getQuantite());
        existingLigneDevis.setPrixUnitaireHt(ligneDevisDetails.getPrixUnitaireHt());

        // Gérer la relation avec Devis et Article
        handleRelations(ligneDevisDetails);

        return LigneDevisRepository.save(existingLigneDevis);
    }

    public void deleteLigneDevis(Long id) {
        LigneDevis ligneDevis = findById(id);
        LigneDevisRepository.delete(ligneDevis);
    }

    private void handleRelations(LigneDevis ligneDevis) {
        if (ligneDevis.getDevis() != null && ligneDevis.getDevis().getId() != null) {
            Devis existingDevis = devisRepository.findById(ligneDevis.getDevis().getId())
                    .orElseThrow(() -> new RuntimeException("Devis n'existe pas"));
            ligneDevis.setDevis(existingDevis);
        }

        if (ligneDevis.getArticle() != null && ligneDevis.getArticle().getId() != null) {
            Article existingArticle = articleRepository.findById(ligneDevis.getArticle().getId())
                    .orElseThrow(() -> new RuntimeException("Article n'existe pas"));
            ligneDevis.setArticle(existingArticle);
        }
    }
}
