### Boycott Product Management System - Sample Requests

#### 1. Ajouter Utilisateurs
**POST** `http://localhost:8080/api/boycott/utilisateurs`

```json
{
  "email": "a.a@esprit.tn",
  "dateInscri": "2024-01-21",
  "typeUtilisateur": "ADMIN"
}
```

```json
{
  "email": "b.b@esprit.tn",
  "dateInscri": "2023-11-07",
  "typeUtilisateur": "VOLONTAIRE"
}
```

#### 2. Ajouter Produit et Catégories
**POST** `http://localhost:8080/api/boycott/produits`

```json
{
  "nomProduit": "Carrefour",
  "etat": "VERIF_EN_COURS",
  "categories": [
    { "nomCategorie": "Nourriture" },
    { "nomCategorie": "Boisson" }
  ]
}
```

```json
{
  "nomProduit": "Arial",
  "etat": "VERIF_EN_COURS",
  "categories": [
    { "nomCategorie": "Nettoyage" }
  ]
}
```

```json
{
  "nomProduit": "Amazon",
  "etat": "BOYCOTT",
  "categories": [
    { "nomCategorie": "Technologie" }
  ]
}
```

#### 3. Affecter Produits à un Utilisateur
**PUT** `http://localhost:8080/api/boycott/affecter?nomProduits=Carrefour,Arial&email=a.a@esprit.tn`

**PUT** `http://localhost:8080/api/boycott/affecter?nomProduits=Amazon&email=b.b@esprit.tn`

#### 4. Chercher Produit
**GET** `http://localhost:8080/api/boycott/chercher/Carrefour`

#### 5. Récupérer Utilisateurs par Critères
**GET** `http://localhost:8080/api/boycott/utilisateurs/criteres?nomCategorie=Nourriture&date=2023-01-01&typeUtilisateur=ADMIN`

#### 6. Désaffecter Catégories de Produit
**DELETE** `http://localhost:8080/api/boycott/desaffecter?nomCategories=Nourriture,Boisson&nomProduit=Carrefour`

#### Swagger UI
`http://localhost:8080/swagger-ui.html`
