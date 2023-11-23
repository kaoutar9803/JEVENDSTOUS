package com.JeVendsTOUS.JeVendsTOUS.Controller;
import com.JeVendsTOUS.JeVendsTOUS.entity.Article;
import com.JeVendsTOUS.JeVendsTOUS.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController //Annotation utilisé pour gerer des requettes HTTP et produire des reponses au format JSON pour les services WEB RESTFUL
@RequestMapping("/Article") // cela difinit un point de terminaison pour les requettes HTTP vers URL /ARTICLE
public class ArticleController {
    @Autowired
    private ArticleService ArticleService; // L'injection de l'instance l'Article services


    @GetMapping // Cela est utilisé pour creer un point de terminaison d'API REST qui renvoie une liste d'article au format JSON
    public List<Article> getAllArticles() {
        return ArticleService.getAllArticles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        // la methode getArticleById est utilisé pour récupérer l'article correspondant à l'identifiant fournit
        Article article = ArticleService.findById(id);
        return ResponseEntity.ok(article);
    }

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        // La methode create est utilisé pour enregistrer un nouvel article en utilisant les données fournies dans le corps de la requête.
        Article savedArticle = ArticleService.save(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article articleDetails) {
        // La methode update est utilisé pour modifier un article en utilisant les données fournies dans le corps de la requête.
        try {
            Article updatedArticle = ArticleService.updateArticle(id, articleDetails);
            return ResponseEntity.ok(updatedArticle);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id) {
        // La methode delete est utilisé pour supprimer un article en utilisant l'ID
        try {
            ArticleService.deleteArticle(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article N'existe pas");
        }
    }
}
