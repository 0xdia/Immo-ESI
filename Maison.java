import java.util.*;

class Maison extends Habitable
{
	private boolean garage;
	private boolean jardin;
	private boolean piscine;
	private double superficieHabitable;
	public String nomClass(){return "Maison";}
	public Maison() {}
	public Maison (Wilaya w, String a, double s, Proprietaire p, boolean n, double prix,
					String desc,Date date,String t,int nbp,int nbe,boolean meublee,
					boolean g,boolean j,boolean pi,double superficie)
					throws InAppropriteSurfaceException
	{
		super(w, a, s, p, n, prix, desc, date, t, nbp, nbe, meublee);
		if (getSuperficie() < superficie) throw new InAppropriteSurfaceException();
		this.garage = g;
		this.jardin = j;
		this.piscine = pi;
		this.superficieHabitable = superficie;
	}

	public void afficherAttributs() {
		super.afficherAttributs();
		System.out.println("14: Existence d'un garage");
		System.out.println("15: Existence d'un jardin");
		System.out.println("16: Existence d'un piscine");
		System.out.println("17: La superficie habitable");
	}
	public void modifier(int choix) {
		if (1<=choix && choix<=13) {
			super.modifier(choix);
			return;
		}
		Scanner in = new Scanner(System.in);
		if (choix == 14) {
				System.out.print("Garage ? [OUI/NON] ");
				String rrp = in.next();
				this.garage = (rrp.equalsIgnoreCase("OUI")) ? true : false;
		}
		if (choix == 15) {
				System.out.print("Jardin ? [OUI/NON] ");
				String p = in.next();
				this.jardin = (p.equalsIgnoreCase("OUI")) ? true : false;
		}
		if (choix == 16) {
				System.out.print("Piscine ? [OUI/NON] ");
				String rp = in.next();
				this.piscine = (rp.equalsIgnoreCase("OUI")) ? true : false;
		}
		if (choix == 17) {
				System.out.print("Donnez la superficie habitable ? ");
				double s = in.nextDouble();
				try {
					setSuperficieHabitable(s);
				} catch (InAppropriteSurfaceException e) {
					System.out.println("la superficie habitable et supérieure a la superficie !");
				}
		}
	}

	public void setGarage(boolean g) {	garage = g;}
	public void setJardin(boolean j) {	jardin = j;}
	public void setPiscine(boolean p) {	piscine = p;}
	public void setSuperficieHabitable(double s) throws InAppropriteSurfaceException {
		if (super.getSuperficie() < s) {
			throw new InAppropriteSurfaceException();
		}
		this.superficieHabitable = s;
	} 

	public void afficherSansDetails() {
		System.out.println("~~ Maison ~~");
		super.afficherSansDetails();
		double x = calculPrix();
		if (calculPrix() > 1000000) {
			x /= 1000000;
			System.out.println("Prix: " + x + " millions DA");
		}
		else System.out.println("Prix: " + x + " DA");
	}
	
	public void afficher() {
		System.out.println("\n~~ Maison ~~");
		super.afficher();
		System.out.println("Garage: " + (garage ? "OUI" : "NON"));
		System.out.println("Jardin: " + (jardin ? "OUI" : "NON"));
		System.out.println("Piscine: " + (piscine ? "OUI" : "NON"));
		System.out.println("Superficie Habitable: " + superficieHabitable + " mm");
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
		if(jardin || garage || piscine) return x + getPrix() * 0.005;
		return x;
	}

	public double calculAllocation() {
		double x = super.calculAllocation();
		if (piscine) return x+50000.0;
		return x;
	}

	public double calculEchange() {
		double x = calculVente();
		if (!(getWilaya().equals(getWilayaDEchange()))) return x + getPrix()*0.0025;
		return x;
	}
}