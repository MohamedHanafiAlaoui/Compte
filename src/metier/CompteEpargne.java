package metier;
import  metier.Compte;


public class CompteEpargne extends Compte
{
	private double tauxInteret;
	
	public CompteEpargne(double tauxInteret,double solde)
	{
		super(solde);
		this.tauxInteret =tauxInteret;
	}
	
	
	@Override
	public void retirer(double montant) {
	    if (!(solde >= montant)) {
	        System.out.println("solde insuffisant → arrêt");
	        return;
	    }

	    solde -= montant;
	    System.out.println("enregistrer le retrait: solde = " + solde + "/ -" + montant);
	}
	@Override
    public  double calculerInteret()
    {
		solde =  solde + (solde *(tauxInteret/ 100));
    	return solde;
    };
    @Override
    public   void afficherDetails()
    {
        System.out.println("Code: " + code);
        System.out.println("Solde: " + solde);
        System.out.println("Taux d'intérêt: " + tauxInteret + "%");
    };

	
	
}