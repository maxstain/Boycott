package org.example.boycott.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nomProduit;
    
    @Enumerated(EnumType.STRING)
    private Etat etat;
    
    @ManyToMany(mappedBy = "produits", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Categorie> categories;
}
