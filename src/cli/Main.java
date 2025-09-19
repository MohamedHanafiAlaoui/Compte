package cli;

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
                        System.out.print("Entrer le montant à verser : ");
                        double montant = scanner.nextDouble();

                        scanner.nextLine();
                        System.out.print("Entrer la source du versement : ");
                        String source = scanner.nextLine();
                        serviceOperation.effectuerVersement(compte, montant, source);

                    } else {
                        System.out.println("Compte introuvable !");

                    }

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