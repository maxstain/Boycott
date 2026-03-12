package org.example.boycott.Repositories;

import org.example.boycott.Entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoProduit extends JpaRepository<Produit, Long> {

    Produit findProduitByNomProduit(String nomProduit);
}
