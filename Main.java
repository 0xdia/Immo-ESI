import java.util.*;

class InAppropriteSurfaceException extends Exception{}
class AttributCannotBeNegativeException extends Exception{}
class NotADateException extends Exception {}

class Main {
	public static void clearScreen() {
		for(int i = 0 ; i < 50 ;i ++) System.out.println("");
	}
	public static void attente() {
		System.out.println("");
		System.out.println("Appuyer sur une touche pour continuer");
		Scanner sc = new Scanner(System.in);
		String at = sc.nextLine();
	}
	public static void main(String[] args) {
		AgenceImmobiliere agence = new AgenceImmobiliere();
		Proprietaire prop1 = new Proprietaire("prop1", "Dia", "062547", "id_zebbiche@esi.dz", "Ouled Si Ahmed");
		Proprietaire prop2 = new Proprietaire("prop2", "Imad", "062684", "ii_tamelghaghet@esi.dz", "Dar Bida");
		Proprietaire prop3 = new Proprietaire("prop3", "Jamel", "062997", "im_jamel@esi.dz", "Si okba");
		Proprietaire prop4 = new Proprietaire("Linus", "Trovalds", "066512", "linus.trovalds@linux.org", "Helinski");  
		agence.ajouterProp(prop1);
		agence.ajouterProp(prop2);
		agence.ajouterProp(prop3);
		agence.ajouterProp(prop4);

		Wilaya w1 = new Wilaya("Alger", 100000);
		Wilaya w2 = new Wilaya("Setif", 30000);
		Wilaya w3 = new Wilaya("Oran", 60000);
		agence.ajouterWilaya(w1);
		agence.ajouterWilaya(w2);
		agence.ajouterWilaya(w3);

		Appartement bien1 = new Appartement(w2, "adr1", 120, prop2, true, 4000000, "Bien 1", new Date(1,3,2020), "vente", 4, 1, false, false, true);
		Appartement bien4 = new Appartement(w3, "adr4", 100, prop2, true, 40000, "Bien 4", new Date(1,4,2020), "location", 3, 1, false, false, true);
		Appartement bien6 = new Appartement(w3, "adr6", 50, prop2, true, 600000, "Bien 6", new Date(5,1,2020), "location", 1, 6, false, false, true);
		Maison bien2, bien5, bien8;
		bien2 = bien5 = bien8 = null;
		try {
			bien2 = new Maison(w3, "adr2", 200, prop1, false, 10000000, "Bien 2", new Date(1, 1, 2019), "vente", 5, 2, true, false, true, false, 150);
			bien5 = new Maison(w2, "adr5", 160, prop3, true, 150000, "Bien 5", new Date(11, 5, 2019), "location", 4, 1, false, false, false, true, 100);
			bien8 = new Maison(w2, "adr8", 200, prop2, false, 14000000, "Bien 8", new Date(2, 2, 2020), "echange", 4, 3, true, true, false, false, 180);
			bien8.setWilayaDEchange(w2);
		} catch(InAppropriteSurfaceException e) {}
		String statut_juri = "____";
		Terrain bien3 = new Terrain(w1, "adr3", 500, prop1, true, 20000000, "Bien 3", new Date(5, 5, 2019), "vente", statut_juri, 3);
		Terrain bien7 = new Terrain(w1, "adr7", 650, prop1, false, 18000000, "Bien 7", new Date(3, 8, 2020), "echange", statut_juri, 1);
				bien7.setWilayaDEchange(w3);



		agence.ajouter(bien5);
		agence.ajouter(bien6);
		agence.ajouter(bien7);
		agence.ajouter(bien8);
		agence.ajouter(bien1);
		agence.ajouter(bien2);
		agence.ajouter(bien3);
		agence.ajouter(bien4);



				Scanner sc = new Scanner(System.in);

				int choix1 = 0,choix2 = 0,choix3 = 0,choix4 = 0,pos = 0,num = 0;
				String ch ="",mdp = "mdp";
				do {
					System.out.println("Menu : \n\n");
					System.out.println("Que voulez vous faire : ");
					System.out.println("0 : Quitter.");
					System.out.println("1 : Se connecter en tant qu'admin");
					System.out.println("2 : Se connecter en tant que client");
					System.out.println(" ");
					System.out.println("Votre choix : ");
					choix1 = sc.nextInt();
					switch(choix1) {
					case 1 : 
						clearScreen();
						System.out.println("Entrez le mot de passe : ");
						ch = sc.nextLine();
						ch = sc.nextLine();
						if(! ch.equals(mdp)) System.out.println("Mot de passe Errone");
						else {
							clearScreen();
							do {
								System.out.println("Que voulez vous faire dans le systeme ?");
								System.out.println("0 : retour au menu principale.");
								System.out.println("1 : Afficher la liste des biens");
								System.out.println("2 : Ajouter un bien .");
								System.out.println("3 : Supprimer un bien .");
								System.out.println("4 : Archiver un bien");
								System.out.println("5 : Voir la liste des proprietaire .");
								System.out.println("6 : Voir les biens d'un proprietaire .");
								System.out.println("7 : modifier un bien .");
								System.out.println("8 : afficher un bien avec tout ses details .");
								System.out.println("9 : afficher les biens archivees .\n");
								System.out.println("Votre choix : ");
								choix2 = sc.nextInt();

								switch(choix2) {
								case 1 : 
									if (agence.listDesBiensEstVide()) {
										System.out.println("La liste des biens est vide.");
										break;
									}
									System.out.println("Voici la liste des Biens\n");
									agence.afficherListBien();
									break;
								case 2 : 
									agence.ajouterMan();
									break;
								case 3 :
									if (agence.listDesBiensEstVide()) {
										System.out.println("La liste des biens est vide.");
										break;
									}
									agence.afficherListBien();
									System.out.print("Donner l'ID du bien (Selon la liste): ");
									num = sc.nextInt();
									agence.supprimer(num);
									break;
								case 4 :
									if (agence.listDesBiensEstVide()) {
										System.out.println("La liste des biens est vide.");
										break;
									}
									agence.afficherListBien();
									System.out.println("Donner l'ID du bien (Selon la liste)");
									num = sc.nextInt();
									agence.archiver(num);
									break;
								case 5 : 
									agence.afficherListProp();
									break;
								case 6 :
									if (agence.listDesPropsEstVide()) {
										System.out.println("La liste des proprietaires est vide");
										return;
									}
									agence.afficherListProp();
									System.out.println("Donner l'ID du proprietaire: ");
									num = sc.nextInt();
									agence.afficherBienProprio(num);
									break;
								case 7 :
									agence.afficherListBien();
									if (agence.listDesBiensEstVide()) {
										System.out.println("La liste des biens est vide");
										break;
									}
									System.out.println("Donner l'ID du bien (Selon la liste)");
									num = sc.nextInt();
									agence.modifier(num);
									break;
								case 8 : 
									if (agence.listDesBiensEstVide()) {
										System.out.println("La liste des biens est vide.");
										break;
									}
									agence.afficherListBien();
									System.out.println("Donner l'ID du bien a afficher avec ses details: ");
									num = sc.nextInt();
									agence.afficherBienDetail(num);
									break;
								case 9 : 
									System.out.println("Les biens archivees : ");
									agence.afficherListBiensArchives();
									break;
								}
								
								attente();
								clearScreen();
							} while((choix2 > 0)&&(choix2 <= 9));	
						}
						attente();
						break;
					case 2 :
						clearScreen();
						System.out.println("Bienvenue sur ImmoESI !!");
						String nom="",prenom="";
						System.out.println("Donner votre nom");
						sc.nextLine();
						sc.nextLine();
						System.out.println("Donner votre prenom :");
						sc.nextLine();
						sc.nextLine();
						Client c = new Client(nom,prenom);
						do {
							System.out.println("Que souhaitez vous faire ?");
							System.out.println("0 : Retour au menu Principale .");
							System.out.println("1 : Afficher la liste des biens");
							System.out.println("2 : Afficher un bien avec tout ses details .");
							System.out.println("3 : Filtrer les biens");
							System.out.println("4 : Envoyer un message .");
							System.out.println("5 : Voir les reponses de vos messages");
							System.out.println("");
							System.out.println("Votre choix : ");
							choix3 = sc.nextInt();
							clearScreen();
							switch(choix3) {
							case 1 :
								if (agence.listDesBiensEstVide()) {
									System.out.println("La liste des biens est vide.");
									break;
								}
								agence.afficherListBien();
								break;
							case 2 :
								if (agence.listDesBiensEstVide()) {
									System.out.println("La liste des biens est vide.");
									break;
								} 
								agence.afficherListBien();
								System.out.println("Donner l'ID du bien (Selon la liste)");
								num = sc.nextInt();
								agence.afficherBienDetail(num);
								break;
							case 3 : 
								boolean[] mesures = new boolean[6];
								Scanner in = new Scanner(System.in);
								String reponse;
								System.out.print("Voulez-vous preciser le type de transaction ? [Oui/Non] ");
									reponse = in.next();
									mesures[0] = (reponse.equalsIgnoreCase("Oui")) ? true : false;
								System.out.print("Voulez-vous preciser la wilaya du bien ? [Oui/Non] ");
									reponse = in.next();
									mesures[1] = (reponse.equalsIgnoreCase("Oui")) ? true : false;
								System.out.print("Voulez-vous preciser le prix max et/ou min du bien ? [Oui/Non]");
									reponse = in.next();
									mesures[2] = (reponse.equalsIgnoreCase("Oui")) ? true : false;
								System.out.print("Voulez-vous preciser le type du bien ? [Oui/Non] ");
									reponse = in.next();
									mesures[3] = (reponse.equalsIgnoreCase("Oui")) ? true : false;
								System.out.print("Voulez-vous preciser la superficie minimale ? [Oui/Non] ");
									reponse = in.next();
									mesures[4] = (reponse.equalsIgnoreCase("Oui")) ? true : false;
								System.out.print("Voulez-vous preciser le nombre minimale de pieces ? [Oui/Non] ");
									reponse = in.next();
									mesures[5] = (reponse.equalsIgnoreCase("Oui")) ? true : false;

								agence.filtrer(mesures);
								
								break;
							case 4 :
								if (agence.listDesBiensEstVide()) {
									System.out.println("La liste des biens est vide.");
									break;
								}
								agence.afficherListBien();
								System.out.println("Donner l'ID du bien (Selon la liste)");
								num = sc.nextInt();
								Bien b = agence.selectionnerBien(num);
								if (b == null) {
									System.out.println("Ce bien n'existe pas");
									break;
								}
								String rep ="",contenu ="";
								boolean t = false, r = false;
								System.out.println("Voulez vous demander une transaction");
								rep = sc.nextLine();
								rep = sc.nextLine();
								t = (rep.equalsIgnoreCase("Oui")) ? true : false;
								System.out.println("Voulez vous demander des renseignement");
								rep = sc.nextLine();
								rep = sc.nextLine();
								r = (rep.equalsIgnoreCase("Oui")) ? true : false;
								System.out.println("Ajouter un contenu ");
								contenu = sc.nextLine();
								contenu = sc.nextLine();
								agence.envoyerMessage(new Message(c, b, t, r, contenu));
								break;
							case 5 :
								agence.repondreClient(c);
								break;
							}
							attente();
						}while((choix3 > 0)&&(choix3 <= 5));
						break;
					}
					clearScreen();
				} while((choix1 > 0)&&(choix1 <= 2));
				System.out.println("");
				System.out.println("Fin du programme");
	}
}