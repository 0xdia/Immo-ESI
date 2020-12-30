import java.util.*;
class Terrain extends Bien
{
	private String statutJuridique;
	private int nbFacades;
	public String nomClass(){return "Terrain";}
	public Terrain( ){ }
	public Terrain(Wilaya w, String a, double s, Proprietaire p, boolean n, double prix,
					String desc, Date date, String t, String statutJuridique, int nbFacades)
	{
		super( w, a, s, p, n, prix,desc, date, t);
		this.statutJuridique = statutJuridique;
		this.nbFacades = nbFacades;
	}

	public void afficherSansDetails() {
		System.out.println("\n~~ Terrain ~~");
		super.afficherSansDetails();
		double x = calculPrix();
		if (calculPrix() > 1000000) {
			x /= 1000000;
			System.out.println("Prix: " + x + " millions DA");
		}
		else System.out.println("Prix: " + x + " DA");
	}


	public void afficherAttributs() {
		super.afficherAttributs();
		System.out.println("11: statut juridique.");
		System.out.println("12: Nombre de facades.");
	}

	public void modifier(int choix) {
		if (1<=choix && choix<=10) {
			super.modifier(choix);
			return;
		}
		Scanner in = new Scanner(System.in);
		if (choix == 11) {
				System.out.print("Statut juridique: ");
				String st = in.next();
		}
		if (choix == 12) {
				System.out.println("Nombre de facades: ");
				this.nbFacades = in.nextInt();
		}
	} 

	private void setStatut(String stat) { statutJuridique = stat;}
	private void setNbFacades(int n) { nbFacades = n;}

	public void afficher() {
		System.out.println("~~ Terrain ~~");
		super.afficher();
		System.out.println("Statut juridique: " + statutJuridique);
		System.out.println("Nomrbe de facades: " + nbFacades);
		double x = calculPrix();
		if (calculPrix() > 1000000) {
			x /= 1000000;
			System.out.println("Prix: " + x + " millions DA");
		}
		else
			System.out.println("Prix: " + x + " DA");
	}

	public double calculPrix() {
		switch(getTypeTransaction()) {
			case "vente":
				return calculVente();
			case "location":
				return calculAllocation();
			case "echange":
				return calculEchange();
			default: break;
		}
		return 0.0;
	}

	public double  calculVente() {
		double x = super.calculVente();
		if(nbFacades > 1) return x+getPrix()*0.005*nbFacades;
		else return x;
	}

	public double calculAllocation() {
		return super.calculAllocation();
	}

	public double calculEchange() {
		double x = calculVente();
		if (!(getWilaya().equals(getWilayaDEchange()))) return x + getPrix()*0.0025;
		return x;
	}

}