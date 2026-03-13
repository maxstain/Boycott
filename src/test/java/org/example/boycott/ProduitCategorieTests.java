package org.example.boycott;

import org.example.boycott.Entities.Categorie;
import org.example.boycott.Entities.Etat;
import org.example.boycott.Entities.Produit;
import org.example.boycott.Services.IGlobalServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProduitCategorieTests {

    @Autowired
    private IGlobalServices globalServices;

    @Test
    @Transactional
    public void testAjouterProduitEtCategories() {
        Produit p = new Produit();
        p.setNomProduit("Produit Test");
        p.setEtat(Etat.BOYCOTT);

        Categorie c1 = new Categorie();
        c1.setNomCategorie("Catégorie 1");

        Categorie c2 = new Categorie();
        c2.setNomCategorie("Catégorie 2");

        List<Categorie> categories = new ArrayList<>();
        categories.add(c1);
        categories.add(c2);
        p.setCategories(categories);

        Produit savedProduit = globalServices.ajouterProduitEtCategories(p);

        assertNotNull(savedProduit.getId());
        assertEquals(2, savedProduit.getCategories().size());
        assertNotNull(savedProduit.getCategories().get(0).getId());
        assertNotNull(savedProduit.getCategories().get(1).getId());
    }
}
