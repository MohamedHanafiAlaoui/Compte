package  metier;
import java.util.ArrayList;
import metier.Operation;

public abstract class Compte
{	
	protected String code ;
	protected double solde;
	ArrayList <Operation>  listeOperations ;
	
	public  Compte(String code, double solde)
	{


        if (!code.matches("CPT-\\d{5}"))
        {
            throw new IllegalArgumentException(
                "Code invalide ! Le format doit être CPT-12345"
                );
        }

    
		this.code=code;
		this.solde= solde;
		this.listeOperations = new ArrayList<>();
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
    
    public abstract void retirer(double montant);

    public abstract double calculerInteret();

    public abstract  void afficherDetails();
	
	
	
	
}