package org.example.boycott.Repositories;

import org.example.boycott.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoUtilisateur extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByEmail(String email);
}
