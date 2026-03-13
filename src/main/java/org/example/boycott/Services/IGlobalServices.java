package org.example.boycott.Services;

import org.example.boycott.Entities.Categorie;
import org.example.boycott.Entities.Produit;
import org.example.boycott.Entities.Utilisateur;

import java.util.List;

public interface IGlobalServices {

    Utilisateur ajouterUtilisateur(Utilisateur u);

    Produit ajouterProduitEtCategories(Produit p);

    Categorie ajoutCategorie(Categorie c);

    Produit affecterCategorieAProduit(Categorie c, Produit p);

    Produit affecterProduitAUtilisateur(Produit p, Utilisateur u);

    void affecterProdAUser(List<String> nomProuit, String email);
}
