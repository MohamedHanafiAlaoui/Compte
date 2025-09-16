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

            switch (choice) {
                case 1:
                    System.out.println("hzlo");
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
        
        
        

         
    }
    
    
    public static void afficherMenu() {
        System.out.println("#===================menu===================#");
        System.out.println("|1. Create compte 1                        |");
        System.out.println("|2. Register compte user 2                 |");
        System.out.println("|3. Exit 3                                 |");
        System.out.println("#==========================================#");
    }
}