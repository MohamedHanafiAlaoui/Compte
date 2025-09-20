# Gestionnaire de Comptes Bancaires — README

## 1. Présentation
Cette application console Java 8 permet de gérer des comptes bancaires (compte courant et compte épargne) et leurs opérations (versements, retraits, virements).  
Elle est conçue selon une architecture en couches (présentation / métier / utilitaire) et peut être étendue (persistence, services, API, tests, etc.).

---

## 2. Objectifs fonctionnels
- Créer un compte (Courant / Épargne)
- Effectuer un versement
- Effectuer un retrait
- Effectuer un virement entre comptes (réutilise versement + retrait)
- Consulter le solde d'un compte
- Consulter l'historique des opérations d'un compte

---

## 3. Architecture & couches
- `ui` — Interface console (menu principal, lecture clavier, affichage)
- `service` / `metier` — Logique métier (gestion des comptes, validation, règles)
- `model` — Entités : `Compte`, `CompteCourant`, `CompteEpargne`, `Operation`, `Versement`, `Retrait`
- `util` — Utilitaires (validation pattern, formatage dates, exceptions custom)
- Optionnel : `persistence` (sauvegarde/chargement fichiers ou base), `test` (JUnit)

---

## 4. Modèle des classes (résumé)
### 4.1 Compte (classe abstraite)
Attributs :
- `String code` — format obligatoire : `CPT-` suivi de 5 chiffres (ex : `CPT-12345`)
- `BigDecimal solde`
- `List<Operation> listeOperations`

Méthodes (abstraites) :
- `void retirer(BigDecimal montant)` — implémentation différente selon type
- `BigDecimal calculerInteret()` — retourne intérêt (0 si courant)
- `void afficherDetails()` — affiche résumé du compte

Remarques :
- Valider le code via regex `^CPT-\\d{5}$`
- Utiliser `BigDecimal` pour précision monétaire

### 4.2 CompteCourant (hérite de Compte)
Attributs supplémentaires :
- `BigDecimal decouvert` — limite autorisée en négatif (ex : 500.00)

Règle de retrait :
- Autorisé si `solde - montant >= decouvert.negate()` → autrement dit, le solde final ne doit pas être inférieur à `-decouvert`.

Implémentation :
- `calculerInteret()` retourne `BigDecimal.ZERO`

### 4.3 CompteEpargne (hérite de Compte)
Attributs supplémentaires :
- `BigDecimal tauxInteret` — ex : 0.02 pour 2%

Règle de retrait :
- Autorisé seulement si `solde >= montant`

Implémentation :
- `calculerInteret()` = `solde.multiply(tauxInteret)`

### 4.4 Operation (classe abstraite)
Attributs :
- `UUID numero` — identifiant unique
- `LocalDateTime date`
- `BigDecimal montant`

### 4.5 Versement (hérite d'Operation)
Attribut supplémentaire :
- `String source` — ex : "Virement externe", "Dépôt espèces", "Salaire"

### 4.6 Retrait (hérite d'Operation)
Attribut supplémentaire :
- `String destination` — ex : "Distributeur ATM", "Chèque", "Virement sortant"

---

## 5. Exemples de signatures Java (squelette)
```java
/package cli;

import java.util.Scanner;
import service.CompteService;
import service.OperationService;
import metier.Compte;
import metier.Operation;


public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CompteService service = new CompteService();
        OperationService serviceOperation = new OperationService();
        int choice = 0;
        do {

            afficherMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    service.ajouterCompte();
                    break;
                case 2:
                    System.out.print("Entrer le code du compte : ");
                    String code = scanner.nextLine();
                    Compte compte = service.rechercherCompte(code);

                    if (compte != null) {
                        System.out.println("#==========================#");
                        System.out.print("Entrer le montant à verser : ");
                        double montant = scanner.nextDouble();

                        scanner.nextLine();
                        System.out.print("Entrer la source du versement : ");
                        String source = scanner.nextLine();
                        serviceOperation.effectuerVersement(compte, montant, source);

                    } else {
                        System.out.println("Compte introuvable !");

                    }
                        System.out.println("#==========================#");


                    break;
                case 3:
                	 System.out.print("Entrez le code du compte : ");
                	 String codeRetrait = scanner.nextLine();
                	 Compte CompteRetrait = service.rechercherCompte(codeRetrait);
                	 
                	 if(CompteRetrait != null)
                	 {
                		 System.out.print("Entrer le montant à retirer : ");
                		 double montantRetrait = scanner.nextDouble();
                		 scanner.nextLine();
                	        System.out.print("Entrer la destination du retrait : ");
                	        String destination = scanner.nextLine();

                	        serviceOperation.effectuerRetrait(CompteRetrait, montantRetrait, destination);


                	 }else {
                	        System.out.println("Compte introuvable !");
                	    }
                	
                    break;
                case 4:
                	System.out.print("Entrez le code du compte source : ");
                    String codeSource = scanner.nextLine();
                    Compte compteSource = service.rechercherCompte(codeSource);

                    System.out.print("Entrez le code du compte destination : ");
                    String codeDest = scanner.nextLine();
                    Compte compteDest = service.rechercherCompte(codeDest);

                    if (compteSource != null && compteDest != null) {
                        System.out.print("Entrez le montant à transférer : ");
                        double montantVirement = scanner.nextDouble();
                        scanner.nextLine();

                        serviceOperation.effectuerVirement(compteSource, compteDest, montantVirement);

                    } else {
                        System.out.println("Un des comptes est introuvable !");
                    }
                    break;
                case 5:
                    System.out.print("Entrez le code du compte : ");
                    String codeSolde = scanner.nextLine();
                    Compte compteSolde = service.rechercherCompte(codeSolde);
                    if (compteSolde != null) {
                        System.out.println("Solde du compte " + compteSolde.getCode() + " : " + compteSolde.getSolde());
                    } else {
                        System.out.println("Compte introuvable !");
                    }
                    break;
                case 6:
                    System.out.print("Entrez le code du compte : ");
                    String codeOp = scanner.nextLine();
                    Compte compteOp = service.rechercherCompte(codeOp);

                    if (compteOp != null) {
                        System.out.println("Liste des opérations pour le compte " + compteOp.getCode() + " :");
                        if (compteOp.getListeOperations().isEmpty()) {
                            System.out.println("Aucune opération enregistrée.");
                        } else {
                            for (Operation op : compteOp.getListeOperations()) {
                                op.afficherDetails();
                            }
                        }
                    } else {
                        System.out.println("Compte introuvable !");
                    }
                    break;
                case 0:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 0);

        scanner.close();

    }

    public static void afficherMenu() {
        System.out.println("#===================menu===================#");
        System.out.println("|1- Ajouter Compte                         |");
        System.out.println("|2- Versement                              |");
        System.out.println("|3- Retrait                                |");
        System.out.println("|4- Virement                               |");
        System.out.println("|5- Consulter Solde                        |");
        System.out.println("|6- Consulter Opérations                   |");
        System.out.println("|0- Quitter                                |");
        System.out.println("#==========================================#");
    }
}
