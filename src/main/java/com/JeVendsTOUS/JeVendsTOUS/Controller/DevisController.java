package com.JeVendsTOUS.JeVendsTOUS.Controller;
import com.JeVendsTOUS.JeVendsTOUS.entity.Devis;
import com.JeVendsTOUS.JeVendsTOUS.entity.LigneDevis;
import com.JeVendsTOUS.JeVendsTOUS.services.DevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/Devis",method = RequestMethod.GET)
public class DevisController {
    @Autowired
    private DevisService DevisService;
    @GetMapping()
    @ResponseBody
    public List<Devis> getAllDevis() {
        return DevisService.getAllDevis();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Devis> getDevisById(@PathVariable Long id) {
        Devis devis = DevisService.findById(id);
        return ResponseEntity.ok(devis);
    }

    @PostMapping
    public ResponseEntity<Devis> createDevis(@RequestBody Devis devis) {
        Devis savedDevis = DevisService.save(devis);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDevis);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Devis> updateDevis(@PathVariable Long id, @RequestBody Devis devisDetails) {
        Devis updatedDevis = DevisService.updateDevis(id, devisDetails);
        return ResponseEntity.ok(updatedDevis);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevis(@PathVariable Long id) {
        DevisService.deleteDevis(id);
        return ResponseEntity.ok().build();
    }
}
