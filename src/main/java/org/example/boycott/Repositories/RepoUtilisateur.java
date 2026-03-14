package org.example.boycott.Repositories;

import org.example.boycott.Entities.TypeUtilisateur;
import org.example.boycott.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RepoUtilisateur extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByEmail(String email);

    @Query("SELECT DISTINCT u FROM Utilisateur u " +
            "JOIN u.produits p " +
            "JOIN p.categories c " +
            "WHERE c.nomCategorie = :nomCategorie " +
            "AND u.dateInsri > :d " +
            "AND u.typeUtilisateur = :tu")
    List<Utilisateur> recupererUtilisateursParCriteres(
            @Param("nomCategorie") String nomCategorie,
            @Param("d") Date d,
            @Param("tu") TypeUtilisateur tu
    );

    List<Utilisateur> findByTypeUtilisateur(TypeUtilisateur typeUtilisateur);
}
