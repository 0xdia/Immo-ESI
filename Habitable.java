import java.util.*;

class Habitable extends Bien
{
	private int nbPiece;
	private int nbEtage;
	private boolean meublee;

	public Habitable() {}
	public Habitable(Wilaya w, String a, double s, Proprietaire p, boolean n,
					double prix, String desc, Date date, String t, int nbp,
					int nbe,boolean meublee)
	{
		super(w, a, s, p, n, prix, desc, date, t);
		this.nbPiece = nbp;
		this.nbEtage = nbe;
		this.meublee  = meublee;
	}

	public void afficherAttributs() {
		super.afficherAttributs();
		System.out.println("11: Nombre de pieces");
		System.out.println("12: nombre d'etages");
		System.out.println("13: meuble ou pas");
	}

	public void modifier(int choix) {
		if (1<=choix && choix<=10) {
			super.modifier(choix);
			return;
		}
		Scanner in = new Scanner(System.in);
		if (choix == 11) {
				System.out.print("Donnez le nombre de pieces: ");
				this.nbPiece = in.nextInt();
		}
		else if (choix == 12) {
				System.out.print("Donnez le nombre d'etage: ");
				this.nbEtage = in.nextInt();
		}
		else if	(choix == 13) {
				System.out.print("Meuble ? [OUI/NON] ");
				String rep = in.next();
				this.meublee = (rep.equalsIgnoreCase("OUI")) ? true : false;
		}
	}

	public void setNbPiece(int n) {	nbEtage = n;}
	public void setNbEtage(int n) { nbEtage = n;}
	public void setMeublee(boolean m) { meublee = m;}

	public int getNbEtage() { return nbEtage;}
	public int getNbPiece() { return nbPiece;}
	public boolean getMeublee() { return meublee;}

	public void afficher() {
		super.afficher();
		System.out.println("Nombre de pieces: " + nbPiece);
		System.out.println("Nombre d'etages: " + nbEtage);
		System.out.println("Meuble : " + (meublee ? "OUI" : "NON"));
	}

	public double calculPrix() {
		return 0.0;
	}
	
	public double  calculVente() {
		return super.calculVente();
	}

	public double calculAllocation() {
		return super.calculAllocation();
	}

	public double calculEchange() {
		return super.calculEchange();
	}
}
