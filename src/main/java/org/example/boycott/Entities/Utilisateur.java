package org.example.boycott.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String email;
    
    @Temporal(TemporalType.DATE)
    private Date dateInscri;
    
    @Enumerated(EnumType.STRING)
    private TypeUtilisateur typeUtilisateur;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "utilisateur_id")
    private List<Produit> produits;
}
