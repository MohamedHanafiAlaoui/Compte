package cli;
import java.util.Scanner;
import service.CompteService;


public class Main {

    public static void main(String[] args) {
    	
    
        Scanner scanner = new Scanner(System.in);
        CompteService service = new CompteService();
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
                	System.out.println("hzlo");
                    break;
                case 3:
                    System.out.println("Exiting the program.");

                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            
        } while (choice != 3);
        
        
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