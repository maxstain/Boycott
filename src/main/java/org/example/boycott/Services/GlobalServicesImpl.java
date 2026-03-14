package org.example.boycott.Services;

import lombok.AllArgsConstructor;
import org.example.boycott.Entities.*;
import org.example.boycott.Repositories.RepoCategorie;
import org.example.boycott.Repositories.RepoProduit;
import org.example.boycott.Repositories.RepoUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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

    @Scheduled(fixedRate = 40000)
    public void afficherEtMettreAJourProduits() {

        List<Utilisateur> admins = repoUtilisateur.findByTypeUtilisateur(TypeUtilisateur.ADMIN);

        for (Utilisateur u : admins) {
            if (u.getProduits() != null) {
                for (Produit p : u.getProduits()) {
                    p.setEtat(Etat.BOYCOTT);
                    repoProduit.save(p);
                }
            }
        }

        List<Produit> produits = repoProduit.findAll();

        System.out.println("Liste des produits :");
        for (Produit p : produits) {
            System.out.println(p);
        }
    }

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

    @Transactional
    public Produit ajouterProduitEtCategories(Produit p) {
        if (p.getCategories() != null) {
            for (Categorie c : p.getCategories()) {
                if (c.getProduits() == null) {
                    c.setProduits(new ArrayList<>());
                }
                if (!c.getProduits().contains(p)) {
                    c.getProduits().add(p);
                }
                repoCategorie.save(c);
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

    public boolean chercherProduit(String nomProduit) {

        Produit produit = repoProduit.findByNomProduit(nomProduit);

        if (produit == null) {
            return false;
        }

        return produit.getEtat() == Etat.BOYCOTT;
    }

    public List<Utilisateur> recupererUtilisateursParCriteres(
            String nomCategorie, Date d, TypeUtilisateur tu) {

        return repoUtilisateur.recupererUtilisateursParCriteres(
                nomCategorie, d, tu);
    }
}
