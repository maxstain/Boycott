package org.example.boycott.Repositories;

import org.example.boycott.Entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoCategorie extends JpaRepository<Categorie, Long> {
    Categorie findByNomCategorie(String nomCategorie);
}
