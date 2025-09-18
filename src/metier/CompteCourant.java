package metier;
import metier.Compte;


public class CompteCourant extends Compte
{
	private double decouvert;
	public CompteCourant(double decouvert, double solde)
	{
		super(solde);
		this.decouvert=decouvert;
	}
	
	@Override
    public  void retirer(double montant)
    {
		if(decouvert + solde >= montant )
		{
            solde -= montant;
            System.out.println("Retrait effectué : " + montant);
			
		}else
		{
            System.out.println("Retrait impossible ! Dépassement du découvert.");

		}
		
    	
    }

	@Override
    public  double calculerInteret()
    {
    	return 0;
    }
	
	@Override
    public   void afficherDetails()
    {
        System.out.println("Code: " + code);
        System.out.println("Solde: " + solde);
        System.out.println("Découvert autorisé: " + decouvert);
    	
    }
	
}