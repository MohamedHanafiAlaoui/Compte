package service;

import metier.Operation;
import metier.Retrait;
import metier.Versement;
import metier.Compte;
import metier.CompteEpargne;
import metier.CompteCourant;



public class OperationService
{

    public void effectuerVersement(Compte compte, double montant, String source) {
        compte.verser(montant, source);
        System.out.println("Versement de " + montant + " effectué sur le compte " + compte.getCode());
    }
    
    public void effectuerRetrait(Compte compte, double montant, String destination)
    {
    	boolean retraitAutorise = false;
    	if(compte instanceof CompteCourant)
    	{
    		CompteCourant compteCourant = (CompteCourant) compte;
    		retraitAutorise = (compteCourant.getSolde() + compteCourant.getDecouvert() >= montant);
    	}else
    	{
    		retraitAutorise = (compte.getSolde() >= montant);
    	}
    	if(retraitAutorise)
    	{
    		compte.retirer(montant);
    		Retrait retrait = new Retrait(montant,destination);
    		compte.getListeOperations().add(retrait);
            System.out.println("Retrait de " + montant + " effectué vers " + destination + " sur le compte " + compte.getCode());

    		
    	}else
    	{
            System.out.println("Retrait impossible ! Solde insuffisant.");
            if (compte instanceof CompteCourant) {
                CompteCourant compteCourant = (CompteCourant) compte;
                System.out.println("Solde actuel : " + compteCourant.getSolde() + ", découvert autorisé : " + compteCourant.getDecouvert());
            } else {
                System.out.println("Solde actuel : " + compte.getSolde());
            }
    	}
    }
    
    
    public void effectuerVirement(Compte compteSource, Compte compteDest, double montant) {
        boolean retraitAutorise = false;

        
        if (compteSource instanceof CompteCourant) {
            CompteCourant compteCourant = (CompteCourant) compteSource;
            retraitAutorise = (compteCourant.getSolde() + compteCourant.getDecouvert() >= montant);
        } else {
            retraitAutorise = (compteSource.getSolde() >= montant);
        }

        if (retraitAutorise) {
            
            compteSource.retirer(montant);
            Retrait retrait = new Retrait(montant, "Virement vers " + compteDest.getCode());
            compteSource.getListeOperations().add(retrait);

            
            compteDest.verser(montant, "Virement reçu de " + compteSource.getCode());
            Versement versement = new Versement(montant, "Virement de " + compteSource.getCode());
            compteDest.getListeOperations().add(versement);

            System.out.println("Virement de " + montant + " effectué de " + compteSource.getCode() + " vers " + compteDest.getCode());
        } else {
            System.out.println("Virement impossible ! Solde insuffisant sur le compte source.");
        }
    }

    
}