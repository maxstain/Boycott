package org.example.boycott.Services;

import org.example.boycott.Entities.Categorie;
import org.example.boycott.Entities.Produit;
import org.example.boycott.Entities.TypeUtilisateur;
import org.example.boycott.Entities.Utilisateur;

import java.util.Date;
import java.util.List;

public interface IGlobalServices {

    void afficherEtMettreAJourProduits();

    Utilisateur ajouterUtilisateur(Utilisateur u);

    Produit ajouterProduitEtCategories(Produit p);

    Categorie ajoutCategorie(Categorie c);

    boolean chercherProduit(String nomProduit);

    void affecterProdAUser(List<String> nomProuit, String email);

    List<Utilisateur> recupererUtilisateursParCriteres(String nomCategorie, Date d, TypeUtilisateur tu);
}
