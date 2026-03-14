package org.example.boycott.Controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.example.boycott.Entities.Categorie;
import org.example.boycott.Entities.Produit;
import org.example.boycott.Entities.TypeUtilisateur;
import org.example.boycott.Entities.Utilisateur;
import org.example.boycott.Services.IGlobalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Tag(name = "Examen Boycott")
@RestController
@AllArgsConstructor
@RequestMapping("/boycott")
public class AllController {

    @Autowired
    IGlobalServices globalServices;

    @PostMapping("/utilisateur/ajout")
    public Utilisateur ajouterUtilisateur(@RequestBody Utilisateur u) {
        return globalServices.ajouterUtilisateur(u);
    }

    @PostMapping("/categorie/ajout")
    public Categorie ajoutCategorie(@RequestBody Categorie c) {
        return globalServices.ajoutCategorie(c);
    }

    @PostMapping("/produit/ajout")
    public Produit ajouterProduitEtCategories(@RequestBody Produit p) {
        return globalServices.ajouterProduitEtCategories(p);
    }

    @PostMapping("/affecterProdAUser")
    public void affecterProdAUser(@RequestBody List<String> nomProuit, @RequestParam String email) {
        globalServices.affecterProdAUser(nomProuit, email);
    }

    @GetMapping("/produit/chercher/{nomProduit}")
    public boolean chercherProduit(@PathVariable String nomProduit) {
        return globalServices.chercherProduit(nomProduit);
    }

    @GetMapping("/utilisateurs/criteres")
    public List<Utilisateur> recupererUtilisateursParCriteres(
            @RequestParam String nomCategorie,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date d,
            @RequestParam TypeUtilisateur tu) {

        return globalServices.recupererUtilisateursParCriteres(nomCategorie, d, tu);
    }
}
