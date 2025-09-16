package ui;
import java.util.Scanner;


public class Comptes{
	public static void menuComptes(Scanner scanner){
        int choice = 0;
        do {
        
        	afficherMenuTypeComptes();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    System.out.println("ab");
                    break;
                case 2:
                	System.out.println("abc");
                    break;
                case 3:
                    System.out.println("Exiting Type Comptes menu.");

                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            
        } while (choice != 3);
        
		
	}
	
	
    public static void afficherMenuTypeComptes()
    {
        System.out.println("#===================Type Comptes===================#");
        System.out.println("|1. CompteCourant                                  |");
        System.out.println("|2. CompteEpargne                                  |");
        System.out.println("|3. Back                                           |");
        System.out.println("#==================================================#");
    }
}