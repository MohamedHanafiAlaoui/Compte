package service;

import metier.Compte;
import metier.CompteCourant;
import metier.CompteEpargne;

import  java.util.HashMap;
import java.util.Scanner;




public class CompteService
{
    private HashMap<String, Compte> comptes;

    public CompteService() {
        comptes = new HashMap<>();
    }
	
	
	public void ajouterCompte()
	{
		
        Scanner sca = new Scanner(System.in);
        
        System.out.println( "|1-Compte Courant   |\n"
        		+ 			"|2-Compte Epargne   |");
        
        int choix = sca.nextInt();
        
        if(choix == 1)
        {
        	System.out.print("entre solde : ");
        	double solde = sca.nextDouble();
        	
        	System.out.print("entre découvert : ");
        	double decouvert  = sca.nextDouble();
        	
        	
        	
        	
        	
        	CompteCourant NewCompteCourant = new CompteCourant(decouvert,solde);  
        	
        	comptes.put(NewCompteCourant.getCode(),NewCompteCourant);
        	
        	 NewCompteCourant.afficherDetails();;
        }else if(choix == 2)
        {
        	System.out.print("entre solde : ");
        	double solde = sca.nextDouble();
        	
        	System.out.print("entre tauxInteret : ");
        	double tauxInteret  = sca.nextDouble();
        	
        	CompteEpargne NewCompteEpargne = new CompteEpargne(tauxInteret,solde);  
        	
        	comptes.put(NewCompteEpargne.getCode(),NewCompteEpargne);
        	
        	NewCompteEpargne.afficherDetails();
        }else {
            System.out.println("Choix invalide !");
        }
        
   

		
	}
	
     public Compte rechercherCompte(String code)
     {
    	 return comptes.get(code);
     };

	
}
