package  metier;
import java.util.ArrayList;
import java.util.Random;
import metier.Operation;
import metier.Versement;

public abstract class Compte
{	
	protected String code ;
	protected double solde;
	ArrayList <Operation>  listeOperations ;
	
	public  Compte( double solde)
	{




    
		this.code=generercode();
		this.solde= solde;
		this.listeOperations = new ArrayList<>();
	}
	
	
	public String generercode()
	{
		Random random = new Random();
		
		int  nombre =10000 + random.nextInt(90000);
		
		return "CPT-"+ nombre ;
	}


    public String getCode() {
        return code;
    }

    public double getSolde() {
        return solde;
    }

    public ArrayList<Operation> getListeOperations() {
        return listeOperations;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public void setListeOperations(ArrayList<Operation> listeOperations) {
        this.listeOperations = listeOperations;
    }
    
    
    public void verser(double montant) {
        solde += montant;
        Versement  versement = new Versement(montant,code);
        
        listeOperations.add(versement);
        System.out.println("Versement effectué : " + montant + " | Nouveau solde : " + solde);
    }
    
    public abstract void retirer(double montant);

    public abstract double calculerInteret();

    public abstract  void afficherDetails();
	
	
    public void afficherOperations() {
        if (listeOperations.isEmpty()) {
            System.out.println("Aucune opération trouvée.");
        } else {
            for (Operation op : listeOperations) {
                op.afficherDetails();
            }
        }
    }
	
}