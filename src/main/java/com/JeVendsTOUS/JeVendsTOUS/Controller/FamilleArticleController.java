package com.JeVendsTOUS.JeVendsTOUS.Controller;
import com.JeVendsTOUS.JeVendsTOUS.entity.FamilleArticle;
import com.JeVendsTOUS.JeVendsTOUS.services.FamilleArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.util.List;

@RestController
@RequestMapping(value="/FamilleArticle",method = RequestMethod.GET)
public class FamilleArticleController {
    @Autowired
    private FamilleArticleService FamilleArticleService;

    @GetMapping()
    @ResponseBody
    public List<FamilleArticle> getAllFamilleArticle() {
        return FamilleArticleService.getAllFamilleArticle();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<FamilleArticle> getFamilleArticleById(@PathVariable Long id) {
        FamilleArticle familleArticle = FamilleArticleService.findById(id);
        return ResponseEntity.ok(familleArticle);
    }

    @PostMapping
    public ResponseEntity<FamilleArticle> createFamilleArticle(@RequestBody FamilleArticle familleArticle) {
        FamilleArticle savedFamilleArticle = FamilleArticleService.save(familleArticle);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFamilleArticle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FamilleArticle> updateFamilleArticle(@PathVariable Long id, @RequestBody FamilleArticle familleArticleDetails) {
        FamilleArticle updatedFamilleArticle = FamilleArticleService.updateFamilleArticle(id, familleArticleDetails);
        return ResponseEntity.ok(updatedFamilleArticle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFamilleArticle(@PathVariable Long id) {
        FamilleArticleService.deleteFamilleArticle(id);
        return ResponseEntity.ok().build();
    }

}
