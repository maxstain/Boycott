package org.example.boycott.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private Date dateInsri;

    private TypeUtilisateur typeUtilisateur;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Produit> produits = new ArrayList<Produit>();
}
