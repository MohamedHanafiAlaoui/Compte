

package metier;

public class Versement extends Operation
{
    private String source;

    public Versement(double montant , String source)
    {
        super(montant);
        this.source = source;
    }

    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }


    @Override
    public void afficherDetails() {
        System.out.println("#==== Détails Versement ====#");
        System.out.println("Numéro      : " + getNumero());
        System.out.println("Date        : " + getDate());
        System.out.println("Montant     : " + getMontant() );
        System.out.println("Source      : " + source);
        System.out.println("#===========================#");
    }




}