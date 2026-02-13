package org.example.boycott.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.boycott.Entities.Produit;
import org.example.boycott.Entities.TypeUtilisateur;
import org.example.boycott.Entities.Utilisateur;
import org.example.boycott.Services.IBoycottService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/boycott")
@RequiredArgsConstructor
@Tag(name = "Boycott Management System", description = "APIs for managing boycotted products")
public class BoycottController {

    private final IBoycottService boycottService;

    @PostMapping("/utilisateurs")
    @Operation(summary = "Ajouter un utilisateur")
    public Utilisateur ajouterUtilisateur(@RequestBody Utilisateur u) {
        return boycottService.ajouterUtilisateur(u);
    }

    @PostMapping("/produits")
    @Operation(summary = "Ajouter un produit et ses catégories")
    public Produit ajouterProduitEtCategories(@RequestBody Produit p) {
        return boycottService.ajouterProduitEtCategories(p);
    }

    @PutMapping("/affecter")
    @Operation(summary = "Affecter des produits à un utilisateur")
    public void affecterProdAUser(@RequestParam List<String> nomProduits, @RequestParam String email) {
        boycottService.affecterProdAUser(nomProduits, email);
    }

    @GetMapping("/chercher/{nomProduit}")
    @Operation(summary = "Vérifier si un produit est boycotté")
    public boolean chercherProduit(@PathVariable String nomProduit) {
        return boycottService.chercherProduit(nomProduit);
    }

    @GetMapping("/utilisateurs/criteres")
    @Operation(summary = "Récupérer des utilisateurs par critères")
    public List<Utilisateur> recupererUtilisateursParCriteres(
            @RequestParam String nomCategorie,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            @RequestParam TypeUtilisateur typeUtilisateur) {
        return boycottService.recupererUtilisateursParCriteres(nomCategorie, date, typeUtilisateur);
    }

    @DeleteMapping("/desaffecter")
    @Operation(summary = "Désaffecter des catégories d'un produit")
    public void desAffecterCatDeProd(@RequestParam List<String> nomCategories, @RequestParam String nomProduit) {
        boycottService.desAffecterCatDeProd(nomCategories, nomProduit);
    }
}