package org.example.boycott.Controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.example.boycott.Entities.Categorie;
import org.example.boycott.Entities.Produit;
import org.example.boycott.Entities.Utilisateur;
import org.example.boycott.Services.IGlobalServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Examen Boycott")
@RestController
@AllArgsConstructor
@RequestMapping("/boycott")
public class AllController {
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
}
