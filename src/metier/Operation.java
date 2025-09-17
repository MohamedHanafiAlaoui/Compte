package metier;
import java.time.LocalDateTime;
import  java.util.UUID;
public abstract class Operation
{
	protected final UUID numero;
	protected final LocalDateTime date;
	protected double montant;

	public Operation( double montant)
	{
		this.numero = UUID.randomUUID();
		this.date = LocalDateTime.now();
		this.montant = montant;
	}


	public UUID getNumero() {
		return numero;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public double getMontant() {
		return montant;
	}


	


	public void setMontant(double montant) {
		this.montant = montant;
	}


}