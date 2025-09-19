package service;

import metier.Operation;
import metier.Retrait;
import metier.Versement;
import metier.Compte;



public class OperationService
{

    public void effectuerVersement(Compte compte, double montant, String source) {
        compte.verser(montant, source);
        System.out.println("Versement de " + montant + " effectué sur le compte " + compte.getCode());
    }
    
}