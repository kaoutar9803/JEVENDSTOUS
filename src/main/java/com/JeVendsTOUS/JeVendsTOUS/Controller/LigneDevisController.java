package com.JeVendsTOUS.JeVendsTOUS.Controller;

import com.JeVendsTOUS.JeVendsTOUS.entity.LigneDevis;
import com.JeVendsTOUS.JeVendsTOUS.services.LigneDevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/LigneDevis",method = RequestMethod.GET)

public class LigneDevisController {
    @Autowired
    private LigneDevisService LigneDevisService;

    @GetMapping()
    @ResponseBody
    public List<LigneDevis> getAllLigneDevis() {
        return LigneDevisService.getAllLigneDevis();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<LigneDevis> getLigneDevisById(@PathVariable Long id) {
        LigneDevis ligneDevis = LigneDevisService.findById(id);
        return ResponseEntity.ok(ligneDevis);
    }

    @PostMapping
    public ResponseEntity<LigneDevis> createLigneDevis(@RequestBody LigneDevis ligneDevis) {
        LigneDevis savedLigneDevis = LigneDevisService.save(ligneDevis);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLigneDevis);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LigneDevis> updateLigneDevis(@PathVariable Long id, @RequestBody LigneDevis ligneDevisDetails) {
        LigneDevis updatedLigneDevis = LigneDevisService.updateLigneDevis(id, ligneDevisDetails);
        return ResponseEntity.ok(updatedLigneDevis);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLigneDevis(@PathVariable Long id) {
        LigneDevisService.deleteLigneDevis(id);
        return ResponseEntity.ok().build();
    }
}
