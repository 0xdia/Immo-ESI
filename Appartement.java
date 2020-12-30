import java.util.*;

class Appartement extends Habitable
{
	private boolean ascenceur;
	private boolean simplexe;
	public String nomClass(){return "Appartement";}
	public Appartement() {}
	public Appartement (Wilaya w, String a, double s, Proprietaire p, boolean n,
						double prix, String desc, Date date, String t,int nbp,
						int nbe, boolean meublee, boolean assenceur, boolean simplex)
	{
		super(w, a, s, p, n, prix,desc, date, t,nbp,nbe,meublee);
		this.ascenceur = assenceur;
		this.simplexe  = simplex;
	}

	public void afficherSansDetails() {
		System.out.println("~~ Appartement ~~");
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
		System.out.println("14: Existense d'un ascenseur");
		System.out.println("15: Simplexe ou duplexe");
	}

	public void modifier(int choix) {
		if (1<=choix && choix<=13) {
			super.modifier(choix);
			return;
		}
		Scanner in = new Scanner(System.in);
		switch (choix) {
			case 14:
				System.out.print("Ascenceur ? [OUI/NON] ");
				String rp = in.next();
				ascenceur = (rp.equalsIgnoreCase("OUI")) ? true : false;
				break;
			case 15:
				System.out.print("Simplexe ? [OUI/NON] ");
				String p = in.next();
				ascenceur = (p.equalsIgnoreCase("OUI")) ? true : false;
				break;
			default: break;
		}
	}

	public void setAscenceur(boolean a) { this.ascenceur = a;}
	public void setSimplexe(boolean s) { this.simplexe = s;}

	public void afficher() {
		System.out.println("\n~~ Apartement ~~");
		super.afficher();
		System.out.println("Ascenceur: " + (ascenceur ? "OUI" : "NON"));
		System.out.println(simplexe ? "Simplexe" : "Duplexe");
		double x = calculPrix();
		if (calculPrix() > 1000000) {
			x /= 1000000;
			System.out.println("Prix: " + x + " millions DA");
		}
		else
			System.out.println("Prix: " + x + " DA");
	}

	public double calculPrix() {
		switch (getTypeTransaction()) {
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
		if(getNbEtage() <= 2) return x+50000.0;
		return x;
	}

	public double calculAllocation()
	{
		double x = super.calculAllocation();
		if( getNbEtage() <= 2) return x+5000;
		if(!(ascenceur)&&(getNbEtage() >= 6)) return (x -500*getSuperficie());
		return x;
	}

	public double calculEchange() {
		double x = calculVente();
		if (!(getWilaya().equals(getWilayaDEchange()))) return x + getPrix()*0.0025;
		return x;
	}
}