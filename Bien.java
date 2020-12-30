import java.util.*;

abstract class Bien implements Transaction, Comparable<Bien> {
	private static int compteur = 0;
	private int id;
	private Wilaya wilaya;
	private String adresse;
	private double superficie;
	private Proprietaire proprietaire;
	private boolean negociable;
	private double prix;
	private String descriptif;
	private Date dateAjout;
	private String typeTransaction;
	private Wilaya wilayaDEchange = null;
	public String nomClass() {return "Bien";}
	public Bien() {}
	public Bien(Wilaya w,String a,double s,Proprietaire p,boolean n,double prix,String desc,Date date, String t) {
		compteur++; id = compteur;
		this.wilaya       = w;
		this.adresse      = a;
		this.superficie   = s;
		this.proprietaire = p;
		this.negociable   = n;
		this.prix         = prix;
		this.descriptif   = desc;
		this.dateAjout    = date;
		this.typeTransaction = t;
	}

	public void afficherAttributs() {
		System.out.println("1: Wilaya");
		System.out.println("2: Adresse");
		System.out.println("3: Superficie");
		System.out.println("4: Proprietaire");
		System.out.println("5: Negociabilite");
		System.out.println("6: Prix");
		System.out.println("7: descriptif");
		System.out.println("8: date d'ajout");
		System.out.println("9: Type de transaction");
		System.out.println("10: Wilaya d'echange (cas d'echange)");
	}

	public void modifier(int choix) {
		Scanner in = new Scanner(System.in);
		if (choix == 1) {
				System.out.print("Donnez le nom de la wilaya: ");
				String nn = in.next();
				System.out.print("Donnez le prix du metre carre: ");
				double dd = in.nextDouble();
				this.wilaya = new Wilaya(nn, dd);
		}	
		if (choix == 2) {
				System.out.print("Donnez l'addresse: ");
				in.nextLine();
				this.adresse = in.nextLine();
		}
		if (choix == 3) {
				System.out.print("Donnez le superficie: ");
				this.superficie = in.nextDouble(); // par rapport à la superficie habitable
		}
		if (choix == 4) {
				System.out.println("Donnez le nom, le prenom, le numero, le mail et l'addresse: ");
				String nm = in.next();
				String prnm = in.next();
				String num = in.next();
				String mail = in.next();
				String add = in.next();
				this.proprietaire.setNom(nm);
				this.proprietaire.setPrenom(prnm);
				this.proprietaire.setNumero(num);
				this.proprietaire.setMail(mail);
				this.proprietaire.setAdress(add); 
		}
		if (choix == 5) {
				System.out.print("Le bien, est il negociable ? [OUI/NO] ");
				String rep = in.next();
				this.negociable = (rep.equalsIgnoreCase("OUI")) ? true : false; 
		}
		if (choix == 6) {
				System.out.print("Donnez le prix: ");
				this.prix = in.nextDouble();
		}
		if (choix == 7) {
				System.out.print("Donnez le descriptif: ");
				in.nextLine();
				this.descriptif = in.nextLine();
		}
		if (choix == 8) {
				System.out.println("Donnez la date d'ajout (jj/mm/aaaa) : ");
				// afficher les jours selon les mois, février selon l'année
				System.out.print("jour: "); int jour = in.nextInt();
				System.out.print("mois: "); int mois = in.nextInt();
				System.out.print("annee: "); int annee = in.nextInt();
				this.dateAjout.setJour(jour);
				this.dateAjout.setMois(mois);
				this.dateAjout.setAnnee(annee);
		}
		if (choix == 9) {
				System.out.println("Type de transaction: 1_vente, 2_location, 3_echange");
				int cx = in.nextInt();
				switch (cx) {
					case 1:
						this.typeTransaction = "vente";
						break;
					case 2:
						this.typeTransaction = "location";
						break;
					case 3:
						this.typeTransaction = "echange";
						System.out.print("Donnez le nom de la wilaya d'echange: ");
						String na = in.next();
						System.out.print("Donnez le prix du metre carre: ");
						double da = in.nextDouble();
						this.wilayaDEchange = new Wilaya(na, da);	
						break;
					default: break;
				}
		}
		if (choix == 10) {
				if (!this.typeTransaction.equals("echange")) return; // exception
				System.out.print("Donnez le nom de la wilaya: ");
				String ne = in.next();
				System.out.print("Donnez le prix du metre carre: ");
				double de = in.nextDouble();
				this.wilayaDEchange = new Wilaya(ne, de);
		}
	}

	public void setWilaya(Wilaya w) {this.wilaya = w;}
	public void setAdress(String a) {this.adresse = a;}
	public void setSuperficie(double s) {this.superficie = s;}
	public void setProprietaire(Proprietaire p) {this.proprietaire = p;}
	public void setNegociable(boolean n) {this.negociable = n;}
	public void setPrix(double p) {this.prix = p;}
	public void setDescriptif(String d) {this.descriptif = d;}
	public void setDateAjout(Date d) {this.dateAjout = d;}
	public void setTypeDeTransaction(String type) {	typeTransaction = type;}
	public void setWilayaDEchange(Wilaya w) { this.wilayaDEchange = w;}

	public int getID() { return id;}
	public double getPrix() { return prix;}
	public String getAdresse() { return adresse;}
	public double getSuperficie() { return superficie;}
	public String getTypeTransaction() { return typeTransaction;}
	public Wilaya getWilaya() { return wilaya;}
	public Date getDateAjout() { return dateAjout;}
	public Proprietaire getProprietaire() { return proprietaire;}
	public Wilaya getWilayaDEchange() { return wilayaDEchange;}
	public String getDescriptif() {return descriptif;}

	public void afficherSansDetails() {
		System.out.println("ID: " + this.id);
		System.out.println("Descriptif: " + getDescriptif());
		System.out.print("Date d'ajout: "); getDateAjout().afficher();
	} 

	public void afficher() {
		System.out.println("ID: " + this.id);
		System.out.println("Wilaya: " + wilaya.getName());
		System.out.println("Adresse: " + adresse);
		System.out.println("Superficie: " + superficie + " mm");
		System.out.println("Negociable: " + (negociable ? "OUI" : "NON"));
		System.out.println("Descriptif: " + descriptif);
		System.out.print("Date d'ajout: "); dateAjout.afficher();
		System.out.println("Type de transaction: " + typeTransaction);
		if (typeTransaction.equals("echange"))
			System.out.println("Wilaya d'echange: " + wilayaDEchange.getName());
	}

	abstract public double calculPrix();

	public double calculVente() {
		if( prix < 5000000) {
			if(wilaya.getPrix_mCarre() < 50000) return (prix + 0.03*prix);
			return (prix + prix*0.035);
		}
		else if( prix < 15000000) {
			if(wilaya.getPrix_mCarre() < 50000) return (prix + 0.02*prix);
			return(prix + 0.025*prix);
		}
		if( wilaya.getPrix_mCarre() < 70000) return (prix + 0.01*prix);
		return (prix + 0.02*prix);
	}

	public double calculAllocation() {
		if(superficie < 60) {
			if( wilaya.getPrix_mCarre() < 50000) return (prix + 0.01*prix);
			return (prix + 0.015*prix);
		}
		else if(superficie < 150) {
				if( wilaya.getPrix_mCarre() < 50000) return (prix + 0.02*prix);
				else return (prix + 0.025*prix);
		}

		if(wilaya.getPrix_mCarre() < 50000) return (prix + prix*0.03);
		return (prix + prix*0.035);
	}

	public double calculEchange() {
		double x = calculVente();
		if(!(wilaya.equals(wilayaDEchange))) return x + 0.0025*x;
		return x;
	}

	public int compareTo(Bien b) {
		if (this.dateAjout.compareTo(b.getDateAjout()) != 0)
			return this.dateAjout.compareTo(b.getDateAjout());

		if (b.getID() > this.id) return -1;
		if (b.getID() < this.id) return 1;
		return 0;
	}

	public boolean equals(Object bien) {
		return (((Bien) bien).getID() == this.id);
	} 

	public int hashCode() {
		return this.adresse.hashCode();
	}
}