package com.JeVendsTOUS.JeVendsTOUS.repository;
import com.JeVendsTOUS.JeVendsTOUS.entity.Devis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DevisRepository extends JpaRepository<Devis, Long> {
    @Query("SELECT d FROM Devis d JOIN d.client c")
    List<Devis> findAllWithClients();
    @Query("SELECT L FROM Devis L JOIN L.ligneDevis ld")
    List<Devis> findAllWithLigneDevis();
}
