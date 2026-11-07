# TP1 JEE : Micro-service de gestion de comptes bancaires

Ce projet est un micro-service développé avec **Spring Boot** qui permet de gérer des comptes bancaires (création, consultation, modification, suppression) à travers une **API REST**.

Réalisé par **Hanae Bouichnad**, dans le cadre du module JEE.

---

## Technologies utilisées

- **Java** et **Spring Boot**
- **Spring Web** : création des endpoints REST
- **Spring Data JPA** : accès à la base de données
- **H2 Database** : base de données en mémoire
- **Lombok** : génération automatique des getters, setters, constructeurs et builders
- **Springdoc OpenAPI (Swagger)** : documentation de l'API
- **Spring Data REST** : exposition automatique du repository
- **Postman** : tests de l'API

---

## Travail réalisé

1. Création d'un projet Spring Boot avec les dépendances Web, Spring Data JPA, H2 et Lombok
2. Création de l'entité JPA `BankAccount`
3. Création de l'interface `BankAccountRepository` basée sur Spring Data
4. Test de la couche DAO
5. Création du Web service RESTful de gestion des comptes
6. Test du micro-service avec Postman
7. Génération et test de la documentation Swagger
8. Exposition d'une API RESTful avec Spring Data REST et des projections
9. Création des DTOs et du Mapper
10. Création de la couche Service (métier)

---

## Architecture

Le projet suit une **architecture en couches** : chaque couche a une seule responsabilité et ne communique qu'avec sa voisine.

```
Client (Postman, appli web, appli mobile)
        |  Requete HTTP + JSON          ^  Reponse HTTP + JSON
        v                               |
+-----------------------------------------------+
|  web          AccountRestController           |  recoit les requetes HTTP
+-----------------------------------------------+
        |  RequestDTO                   ^  ResponseDTO
        v                               |
+-----------------------------------------------+
|  services     AccountService (interface)      |  le contrat
|               AccountServiceImpl              |  les regles metier
+-----------------------------------------------+
        |  Entite                       ^
        v                               |
+----------------------+      +--------------------------+
|  repositories        |      |  mappers                 |
|  BankAccountRepository|     |  AccountMapper           |
|  (Spring Data JPA)   |      |  Entite --> ResponseDTO  |
+----------------------+      +--------------------------+
        |
        v
+-----------------------------------------------+
|  Base de donnees H2                           |
+-----------------------------------------------+
```

### Structure des packages

```
org.sid.bankaccountservice
├── dtos           BankAccountRequestDTO, BankAccountResponseDTO
├── entities       BankAccount
├── enums          AccountType
├── mappers        AccountMapper
├── repositories   BankAccountRepository
├── services       AccountService, AccountServiceImpl
└── web            AccountRestController
```

### Rôle de chaque couche

| Couche | Rôle |
|---|---|
| **Entité** (`BankAccount`) | Représente la table des comptes en base de données |
| **Repository** | Accès aux données : `findAll`, `findById`, `save`, `delete`... |
| **DTO** | Données échangées avec le client : `RequestDTO` (ce que le client envoie), `ResponseDTO` (ce qu'il reçoit) |
| **Mapper** | Convertit une entité en DTO |
| **Service** | Contient la logique métier (génération de l'id et de la date de création, mise à jour partielle, vérification de l'existence du compte) |
| **Controller** | Expose les endpoints REST et délègue le traitement au service |

---

## Modèle de données

**BankAccount**

| Attribut | Type | Description |
|---|---|---|
| `id` | `String` | Identifiant unique, généré automatiquement (UUID) |
| `createdAt` | `Date` | Date de création, générée automatiquement |
| `balance` | `Double` | Solde du compte |
| `currency` | `String` | Devise (exemple : MAD) |
| `type` | `AccountType` | Type de compte : `CURRENT_ACCOUNT` ou `SAVING_ACCOUNT` |

---

## Endpoints de l'API REST

URL de base : `http://localhost:8080/api`

| Méthode | URL | Description |
|---|---|---|
| GET | `/api/bankAccounts` | Lister tous les comptes |
| GET | `/api/bankAccounts/{id}` | Consulter un compte |
| POST | `/api/bankAccounts` | Créer un compte |
| PUT | `/api/bankAccounts/{id}` | Modifier un compte |
| DELETE | `/api/bankAccounts/{id}` | Supprimer un compte |
