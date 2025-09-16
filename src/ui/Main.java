package ui;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
    	
    
        Scanner scanner = new Scanner(System.in);

        int choice = 0;
        do {
        
        	   afficherMenu(); 
               System.out.print("Enter your choice: ");
               choice = scanner.nextInt();
               scanner.nextLine();

            switch (choice) {
                case 1:
                	Comptes.menuComptes(scanner);
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
        System.out.println("|1. Create compte 🏦                        |");
        System.out.println("|2. Register compte user 💻                 |");
        System.out.println("|3. Exit 3  📴                               |");
        System.out.println("#==========================================#");
    }
}