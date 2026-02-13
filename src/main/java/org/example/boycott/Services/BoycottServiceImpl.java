package org.example.boycott.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boycott.Entities.*;
import org.example.boycott.Repositories.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoycottServiceImpl implements IBoycottService {

    private final UtilisateurRepository utilisateurRepository;
    private final ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;

    @Override
    public Utilisateur ajouterUtilisateur(Utilisateur u) {
        return utilisateurRepository.save(u);
    }

    @Override
    @Transactional
    public Produit ajouterProduitEtCategories(Produit p) {
        if (p.getCategories() != null) {
            List<Categorie> categories = p.getCategories().stream().map(cat -> {
                return categorieRepository.findByNomCategorie(cat.getNomCategorie())
                        .orElseGet(() -> categorieRepository.save(cat));
            }).collect(Collectors.toList());
            p.setCategories(categories);
            for (Categorie cat : categories) {
                if (cat.getProduits() == null) cat.setProduits(new ArrayList<>());
                if (!cat.getProduits().contains(p)) {
                    cat.getProduits().add(p);
                }
            }
        }
        return produitRepository.save(p);
    }

    @Override
    @Transactional
    public void affecterProdAUser(List<String> nomProduit, String email) {
        Utilisateur user = utilisateurRepository.findByEmail(email).orElse(null);
        if (user != null) {
            List<Produit> produits = user.getProduits();
            if (produits == null) produits = new ArrayList<>();
            for (String nom : nomProduit) {
                Produit p = produitRepository.findByNomProduit(nom).orElse(null);
                if (p != null && !produits.contains(p)) {
                    produits.add(p);
                }
            }
            user.setProduits(produits);
            utilisateurRepository.save(user);
        }
    }

    @Override
    public boolean chercherProduit(String nomProduit) {
        return produitRepository.findByNomProduit(nomProduit)
                .map(p -> p.getEtat() == Etat.BOYCOTT)
                .orElse(false);
    }

    @Override
    public List<Utilisateur> recupererUtilisateursParCriteres(String nomCategorie, Date d, TypeUtilisateur tu) {
        return utilisateurRepository.findAll().stream()
                .filter(u -> u.getTypeUtilisateur() == tu)
                .filter(u -> u.getDateInscri() != null && u.getDateInscri().after(d))
                .filter(u -> u.getProduits() != null && u.getProduits().stream()
                        .anyMatch(p -> p.getCategories() != null && p.getCategories().stream()
                                .anyMatch(c -> c.getNomCategorie().equals(nomCategorie))))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    @Scheduled(fixedRate = 40000)
    public void afficherEtMettreAJourProduits() {
        List<Utilisateur> admins = utilisateurRepository.findAll().stream()
                .filter(u -> u.getTypeUtilisateur() == TypeUtilisateur.ADMIN)
                .collect(Collectors.toList());

        for (Utilisateur admin : admins) {
            if (admin.getProduits() != null) {
                for (Produit p : admin.getProduits()) {
                    p.setEtat(Etat.BOYCOTT);
                    produitRepository.save(p);
                }
            }
        }

        List<Produit> allProducts = produitRepository.findAll();
        System.out.println("--- Liste des produits (Mise à jour Scheduler) ---");
        allProducts.forEach(p -> System.out.println("Produit: " + p.getNomProduit() + " - Etat: " + p.getEtat()));
    }

    @Override
    @Transactional
    public void desAffecterCatDeProd(List<String> nomCategories, String nomProduit) {
        Produit p = produitRepository.findByNomProduit(nomProduit).orElse(null);
        if (p != null && p.getCategories() != null) {
            List<Categorie> toRemove = p.getCategories().stream()
                    .filter(cat -> nomCategories.contains(cat.getNomCategorie()))
                    .collect(Collectors.toList());
            
            for (Categorie cat : toRemove) {
                p.getCategories().remove(cat);
                if (cat.getProduits() != null) {
                    cat.getProduits().remove(p);
                }
            }
            produitRepository.save(p);
        }
    }
}