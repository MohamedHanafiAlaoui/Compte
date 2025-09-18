package metier;

public class Retrait extends Operation {
	
	
	private String destination;
	
	public Retrait(double montant,String destination)
	{
		super(montant);
		this.destination=destination;
		
	}
	
	
	public String getdestination()
	{
		return destination;
	}
	
	public void setdestination(String destination)
	{
		this.destination = destination;
	}
	
	
	  @Override
	public void afficherDetails()
	  {
		    System.out.println("#==== Détails Retrait ====#");
		    System.out.println("Numéro      : " + getNumero());
		    System.out.println("Date        : " + getDate());
		    System.out.println("Montant     : " + getMontant());
		    System.out.println("Destination : " + destination);
		    System.out.println("#=========================#"); 
	  }
	
	
    
}
