package org.example.boycott.Services;

import org.example.boycott.Entities.*;
import java.util.Date;
import java.util.List;

public interface IBoycottService {
    Utilisateur ajouterUtilisateur(Utilisateur u);
    Produit ajouterProduitEtCategories(Produit p);
    void affecterProdAUser(List<String> nomProduit, String email);
    boolean chercherProduit(String nomProduit);
    List<Utilisateur> recupererUtilisateursParCriteres(String nomCategorie, Date d, TypeUtilisateur tu);
    void desAffecterCatDeProd(List<String> nomCategories, String nomProduit);
    void afficherEtMettreAJourProduits();
}