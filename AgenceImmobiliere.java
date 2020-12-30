import java.util.*;

class AgenceImmobiliere {
	private SortedSet<Bien> listBien;
	private HashSet<Wilaya> listDesWilayas;
	private HashSet<Proprietaire> listProprietaire;
	private SortedSet<Bien> biensArchives;
	private ArrayList<Message> messages;

	public AgenceImmobiliere() {
		listBien = new TreeSet<Bien>();
		listProprietaire = new HashSet<Proprietaire>();
		biensArchives = new TreeSet<Bien>();
		listDesWilayas = new HashSet<Wilaya>();
		messages = new ArrayList<Message>();
	}
	public boolean rechBien(Bien b) {
		Iterator<Bien> it = listBien.iterator();
		boolean trouv = false;
		Bien bien = null;
		while((it.hasNext())&&(! trouv)) {
			bien = it.next();
			if(b.equals(bien)) trouv = true;
		}
		return trouv;
	}

	public void envoyerMessage(Message m) {
		messages.add(m);
	}
	public void repondreClient(Client c) {
		for(Message message : messages) {
			if(message.getClient().equals(c)) {
				if(rechBien(message.getBien())) {
					message.getBien().afficher();
					if(message.getTransaction()) System.out.println("\n Bien disponible pour la transaction");
					if(message.getRenseignement()) afficherBienDetail(message.getBien().getID());
					messages.remove(message);
				} else System.out.println("\n Le bien que vous avez preciser n'est pas present");
			}
		}
	}

	public void afficherListBien() {
		Iterator<Bien> it = listBien.iterator();
		while(it.hasNext()) {
			it.next().afficherSansDetails();
		}
	}
	public void afficherBienDetail(int ide) {
		for (Bien bien: listBien)
			if (bien.getID() == ide) {
				bien.afficher();
				return;
			}
		System.out.println("Ce bien n'existe pas");
	}

	public void ajouterWilaya(Wilaya w) {
		listDesWilayas.add(w);
	}
	public void afficherListWilaya() {
		for (Wilaya wilaya: listDesWilayas) {
			wilaya.afficher();
			System.out.println("");
		}
	}
	public void ajouterProp(Proprietaire p) {
		listProprietaire.add(p);
	}

	public void afficherListProp() {
		for (Proprietaire prop: listProprietaire) {
			System.out.println("********************");
			prop.afficherSansDetails();
			System.out.println("");
			System.out.println("********************");
		}
	}

	public void afficherListBiensArchives() {
		System.out.println("\nLa liste des biens archives: (" + biensArchives.size() + " biens) *************");
		for (Bien bien: biensArchives) {
			System.out.println("#");
			bien.afficher();
		}
		System.out.println("\n*******************************");
	}
	public void ajouter(Bien bien) {
		for (Bien b: listBien)
			if (b.equals(bien))
				return;

		listBien.add(bien);
		bien.getProprietaire().ajouterBien(bien);
		listDesWilayas.add(bien.getWilaya());
		this.ajouterProp(bien.getProprietaire());
	}

	public void trier() {
		int i = 1;
		for (Bien bien: listBien) {
			System.out.println("***** " + i + " *****"); ++i;
			bien.afficher();
			System.out.println("");
		}
	}

	public Bien selectionnerBien(int ide) {
		for (Bien bien: listBien)
			if (bien.getID() == ide)
				return bien;

		return null;
	}

	public void modifier(int ide) {
		Bien bien = null;
		boolean found = false;
		for (Bien b: listBien)
			if (b.getID() == ide) {
				bien = b;
				found = true;
				break;
			}

		if (!found) {
			System.out.println("Ce bien n'existe pas");
			return;
		}

		bien.afficher();
		System.out.println(" # # #");
		bien.afficherAttributs();
		System.out.println("Choisissez l'attribut: ");
		Scanner in = new Scanner(System.in);
		int choix = in.nextInt();
		Iterator it = listBien.iterator();
		while (it.hasNext()) {
			if (((Bien) it.next()).equals(bien)) {
				bien.modifier(choix);
				bien.afficher();
			}
		}
	}

	public void supprimer(int ide) {
		Iterator it = listBien.iterator();
		while (it.hasNext()) {
			Bien i = (Bien) it.next();
			if (i.getID() == ide) {
				it.remove();
				i.getProprietaire().supprimerBien(i);
				if (i.getProprietaire().getListVide()) {
					listProprietaire.remove(i.getProprietaire());
				}
				break;
			}
		}
	}

	public void archiver(int ide) {
		Iterator it = listBien.iterator();
		while (it.hasNext()) {
			Bien i = (Bien) it.next();
			if (i.getID() == ide) {
				biensArchives.add(i);
				it.remove();
				break;
			}
		}
	}

	public void afficherBienProprio(int ide) {
		Iterator<Proprietaire> it = listProprietaire.iterator();
		boolean trouv = false;
		Proprietaire pr = null;
		while((it.hasNext()) && (!trouv)){
			pr = it.next();
			if (pr.getID() == ide) trouv = true;
		}
		if(! trouv) System.out.println("Proprietaire non trouve");
		else pr.afficherListBien();
	}

	public void filtrer(boolean[] parametres) { // les exceptions
		Scanner scanner = new Scanner(System.in);
		String typeTran, wil, typeBien;
		typeTran = wil = typeBien = null;
		double prixMin, prixMax, superficieMin;
		prixMin = prixMax = superficieMin = 0.0;
		int nbMinPiece = 0;
		Bien b = null;

		if (parametres[0]) {
			System.out.print("Donnez le type de transaction: ");
			typeTran = scanner.next();
		}

		if (parametres[1]) {
			System.out.print("Donnez le wilaya du bien: ");
			wil = scanner.next();
		}

		if (parametres[2]) {
			System.out.print("Voulez-vous fixer un prix minimale? (Oui/Non) ");
			String reponse;
			reponse = scanner.next();
			if (reponse.equalsIgnoreCase("oui")) {
				System.out.print("Donnez le prix minimale: ");
				prixMin = scanner.nextDouble();
			} else prixMin = 0;
			System.out.print("Voulez-vous fixer un prix maximale? (Oui/Non) ");
			reponse = scanner.next();
			if (reponse.equalsIgnoreCase("Oui")) {
				System.out.print("Donnez le prix maximale: ");
				prixMax = scanner.nextDouble();
			} else prixMax = Double.MAX_VALUE;
		}

		if (parametres[3]) {
			System.out.print("Donnez le type du bien: ");
			typeBien = scanner.next();
			if (typeBien.equalsIgnoreCase("Maison")) {
				b = new Maison();
			}
			else if (typeBien.equalsIgnoreCase("Terrain")) b = new Terrain();
			else if (typeBien.equalsIgnoreCase("Appartement")) b = new Appartement();
			else return;
		} else typeBien = "maison";

		if (parametres[4]) {
			System.out.print("Donnez la superficie minimale: ");
			superficieMin = scanner.nextDouble();
		}

		if (parametres[5] && !typeBien.equalsIgnoreCase("Terrain")) {
			System.out.print("Donnez le nombre minimale de pieces: ");
			nbMinPiece = scanner.nextInt();
		}
		
		for (Bien bien: listBien) {
			boolean v1 = true,v2 = true,v3 = true,v4 = true,v5 = true,v6 = true;
			if(parametres[0]) {
				if(!typeTran.equals(bien.getTypeTransaction())) v1 = false;
			}
			if(parametres[1]) {
				if(!wil.equalsIgnoreCase(bien.getWilaya().getName())) v2 = false;
			}
			if(parametres[2]) {
				if((bien.calculPrix()<prixMin) || (bien.calculPrix()>prixMax)) v3 = false;
			}
			if(parametres[3]) {
				if(! bien.nomClass().equals(b.nomClass())) v4 = false; 
			}
			if(parametres[4]) {
				if(bien.getSuperficie() < superficieMin) v5 = false;
			}
			if(parametres[5]) {
				if(bien.nomClass().equals("Terrain")) v6 = false;
				else {
					if(((Habitable) bien).getNbPiece() < nbMinPiece) v6 = false;
				}
			}
			if (v1 && v2 && v3 && v4 && v6) bien.afficher();
		}
	}

	public void ajouterMan() {
		System.out.print("Donnez le type du bien: ");
		System.out.println("1_vente | 2_location | 3_echange");
		Scanner in = new Scanner(System.in);
		String typtr ="";
		int e = in.nextInt();
		switch (e) {
			case 1: typtr = "vente"; break;
			case 2: typtr = "location"; break;
			case 3:	typtr = "echange"; break;
			default: break;
		}

		Wilaya w, wd; w = wd = null;
		if (typtr.equals("echange")) {
			String nww;
			double pmm;
			System.out.print("Donnez le nom de la wilaya d'echange: "); nww = in.next();
			System.out.print("Donnez le prix du metre carre: ");pmm = in.nextDouble();
			wd = new Wilaya(nww, pmm);
		}

		System.out.print("Donnez l'adresse: ");
		in.nextLine();
		String adr = in.nextLine();
		System.out.println("Donnez la date d'ajout: ");
		System.out.print("jour: "); int jour = in.nextInt();
		System.out.print("mois: "); int mois = in.nextInt();
		System.out.print("annee: "); int annee = in.nextInt();
		Date ladate = new Date(jour, mois, annee);

		System.out.println("Proprietaire existant ? [Oui/Non] ");
		String jawab = in.next();
		Proprietaire pr = null;
		if (jawab.equalsIgnoreCase("oui")) {
			if (listProprietaire.isEmpty()) {
				System.out.println("La liste des proprietaires est vide !");
				return;
			}
			afficherListProp();
			System.out.println("Preciser le proprietaire par son ID: ");
			int number = in.nextInt();
			boolean found = false;
			for (Proprietaire owner: listProprietaire) {
				if (owner.getID() == number) {
					found = true;
					pr = owner;
				}
			}
			if (!found) {
				System.out.println("Ce proprietaire n'existe pas !");
				return;
			}
		} else {
			System.out.println("Préciser le proprietaire:");
			System.out.println("Nom: "); String nom = in.next();
			System.out.println("Prenomom: "); String prenom = in.next();
			System.out.println("Mail: "); String mail = in.next();
			System.out.println("Numero: "); String num = in.next();
			System.out.println("Adresse: "); 
			in.nextLine();
			String adrs = in.nextLine();
			pr = new Proprietaire(nom, prenom, num, mail, adrs);
		}

		System.out.print("Donnez le nom de la wilaya du bien: "); String nw = in.next();
		System.out.print("Donnez le prix du metre carre: "); double pm = in.nextDouble();
		w = new Wilaya(nw, pm);

		System.out.print("Donnez un descriptif: ");
		in.nextLine();
		String des = in.nextLine();

		System.out.print("Donnez la superficie: ");
		double superficie = in.nextDouble();

		System.out.print("Negociable ou pas ? [Oui/Non] ");
		String rep = in.next();
		boolean neg = (rep.equalsIgnoreCase("oui")) ? true : false;

		System.out.print("Donnez le prix: ");
		double prix = in.nextDouble();

		System.out.print("Donnez le type du bien: 1_maison | 2_apparetement | 3_terrain  ");
		e = in.nextInt();
		int etg = 0, pic = 0;
		boolean meub = true;
		if (e == 1 || e == 2) {
			System.out.print("Etages: "); etg = in.nextInt();
			System.out.print("Le nombre de pieces: "); pic = in.nextInt();
			System.out.print("Meuble ou pas ? [Oui/Non] "); String ans = in.next();
			meub = (ans.equalsIgnoreCase("oui")) ? true : false;
		}
		
		boolean gar, jar, psin;
		double suphab = 0;
		String answer = "";
		if (e == 1) {
			System.out.print("Garage ? "); answer = in.next(); 
			gar = (answer.equalsIgnoreCase("oui")) ? true : false;
			System.out.print("Jardin ? "); answer = in.next();
			jar = (answer.equalsIgnoreCase("oui")) ? true : false;
			System.out.print("Piscine ?"); answer = in.next();
			psin = (answer.equalsIgnoreCase("oui")) ? true : false;
			System.out.print("Donnez la superficie habitable: ");
			suphab = in.nextDouble();
			try {
				Maison maison = new Maison(w, adr, superficie, pr, neg, prix, des, ladate, typtr, pic, etg, meub, gar, jar, psin, suphab);
				maison.setWilayaDEchange(wd);
				this.ajouter(maison);
			}
			catch (InAppropriteSurfaceException e2) {
				System.out.println("La superficie habitable ne depasse pas la superficie totale");
			}
		} else if (e == 2) {
			boolean asc,simpl;
			System.out.println("Ascenceur ?");answer = in.next();
			asc = (answer.equalsIgnoreCase("oui")) ? true : false;
			if(pic == 1) simpl = true;
			else {
				System.out.println("Simplexe ?");answer = in.next();
				simpl = (answer.equalsIgnoreCase("oui")) ? true : false;
			}
			Appartement appartement = new Appartement(w, adr, superficie, pr, neg, prix, des, ladate, typtr, pic, etg, meub,asc,simpl);
			appartement.setWilayaDEchange(wd);
			this.ajouter(appartement);
		} else if (e == 3) {
				int nbf = 0;
				System.out.println("Nombre de facades : ");
				nbf = in.nextInt();
				Terrain terrain = new Terrain(w, adr, superficie, pr, neg, prix, des, ladate, typtr,"_",nbf);
				terrain.setWilayaDEchange(wd);
				this.ajouter(terrain);
		}
	}

	public boolean listDesBiensEstVide() {	return this.listBien.isEmpty();}
	public boolean listDesPropsEstVide() {	return this.listProprietaire.isEmpty();}
}