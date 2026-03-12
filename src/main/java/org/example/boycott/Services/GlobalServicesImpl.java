package org.example.boycott.Services;

import lombok.AllArgsConstructor;
import org.example.boycott.Entities.Categorie;
import org.example.boycott.Entities.Produit;
import org.example.boycott.Entities.Utilisateur;
import org.example.boycott.Repositories.RepoCategorie;
import org.example.boycott.Repositories.RepoProduit;
import org.example.boycott.Repositories.RepoUtilisateur;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GlobalServicesImpl implements IGlobalServices {

    RepoUtilisateur repoUtilisateur;
    RepoProduit repoProduit;
    RepoCategorie repoCategorie;

    public Utilisateur ajouterUtilisateur(Utilisateur u) {
        return repoUtilisateur.save(u);
    }

    public Produit ajouterProduitEtCategories(Produit p) {
        repoCategorie.saveAll(p.getCategories());
        return repoProduit.save(p);
    }

    public Categorie ajoutCategorie(Categorie c) {
        return repoCategorie.save(c);
    }
}
