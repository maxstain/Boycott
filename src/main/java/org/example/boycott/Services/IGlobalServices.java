package org.example.boycott.Services;

import org.example.boycott.Entities.Categorie;
import org.example.boycott.Entities.Produit;
import org.example.boycott.Entities.Utilisateur;

public interface IGlobalServices {

    Utilisateur ajouterUtilisateur(Utilisateur u);

    Produit ajouterProduitEtCategories(Produit p);

    Categorie ajoutCategorie(Categorie c);
}
