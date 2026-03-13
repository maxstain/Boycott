package org.example.boycott.Services;

import lombok.AllArgsConstructor;
import org.example.boycott.Entities.Categorie;
import org.example.boycott.Entities.Produit;
import org.example.boycott.Entities.Utilisateur;
import org.example.boycott.Repositories.RepoCategorie;
import org.example.boycott.Repositories.RepoProduit;
import org.example.boycott.Repositories.RepoUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GlobalServicesImpl implements IGlobalServices {

    @Autowired
    RepoUtilisateur repoUtilisateur;
    @Autowired
    RepoProduit repoProduit;
    @Autowired
    RepoCategorie repoCategorie;

    public Utilisateur ajouterUtilisateur(Utilisateur u) {
        return repoUtilisateur.save(u);
    }

    public Produit affecterProduitAUtilisateur(Produit p, Utilisateur u) {
        u.getProduits().add(p);
        return repoProduit.save(p);
    }

    public Produit affecterCategorieAProduit(Categorie c, Produit p) {
        p.getCategories().add(c);
        return repoProduit.save(p);
    }

    public Produit ajouterProduitEtCategories(Produit p) {
        if (p.getCategories() != null) {
            for (Categorie c : p.getCategories()) {
                if (c.getProduits() == null) {
                    c.setProduits(new ArrayList<>());
                }
                c.getProduits().add(p);
            }
        }
        return repoProduit.save(p);
    }

    public Categorie ajoutCategorie(Categorie c) {
        return repoCategorie.save(c);
    }

    public void affecterProdAUser(List<String> nomProuit, String email) {
        Utilisateur u = repoUtilisateur.findByEmail(email);
        for (String nom : nomProuit) {
            Produit p = repoProduit.findProduitByNomProduit(nom);
            if (p != null && u != null) {
                u.getProduits().add(p);
            }
        }
        repoUtilisateur.save(u);
    }
}
