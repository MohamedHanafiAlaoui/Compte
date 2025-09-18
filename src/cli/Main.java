package cli;

import java.util.Scanner;
import service.CompteService;
import service.OperationService;
import metier.Compte;

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
                    // service.effectuerRetrait();
                    break;
                case 4:
                    // service.effectuerVirement();
                    break;
                case 5:
                    // service.consulterSolde();
                    break;
                case 6:
                    // service.consulterOperations();
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