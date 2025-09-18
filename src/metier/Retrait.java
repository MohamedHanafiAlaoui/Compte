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
	
	public void setdestination()
	{
		this.destination = destination;
	}
	
	
	  @Override
	    public void afficherDetails() {
		  
	  }
	
	
    
}
