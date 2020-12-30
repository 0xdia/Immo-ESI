import java.util.*;

class Proprietaire {
	private static int counter = 0;
	private int id;
	private String nom;
	private String prenom;
	private String mail;
	private String numero;
	private String address;
	private SortedSet<Bien> listDesBiens;

	public Proprietaire(String nom, String prenom, String numero, String mail, String address) {
		counter++; id = counter;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.numero = numero;
		this.address = address;
		listDesBiens = new TreeSet<Bien>();
	}

	public void setNom(String n) {	nom = n;}
	public void setPrenom(String p) {	prenom = p;}
	public void setMail(String m) {	mail = m;}
	public void setNumero(String n) {	numero = n;}
	public void setAdress(String a) {	address = a;}

	public boolean getListVide() { return this.listDesBiens.isEmpty();}
	public int getID() { return this.id; }

	public void ajouterBien(Bien b) {
		listDesBiens.add(b);
	}

	public void supprimerBien(Bien b) {
		Iterator it = listDesBiens.iterator();

		while (it.hasNext()) {
			Bien i = (Bien) it.next();
			if (i.equals(b)) {
				it.remove();
				break;
			}
		}
	}

	public void afficherSansDetails() {
		System.out.print("Le proprietaire: ");
		System.out.print(nom + " " + prenom);
		System.out.println("ID: " + this.id);
		System.out.println(" (a " + listDesBiens.size() + " biens)");
	}

	public void afficherInfo() {
		System.out.println("Le proprietaire:");
		System.out.println("ID: " + this.id);
		System.out.println("  Nom: " + nom);
		System.out.println("  Prenom: " + prenom);
		System.out.println("  Mail: " + mail);
		System.out.println("  Numero: " + numero);
		System.out.println("  Adresse: " + address);
	}

	public void afficherListBien() {
		for (Bien bien: listDesBiens) {
			System.out.println("#");
			bien.afficher();
			System.out.println("#");
		}
	}

	public String getNom() {return nom;}
	public String getPrenom() {return prenom;}
	public String getNumero() {return numero;}	

	public boolean equals(Object o) {
		return (this.id == ((Proprietaire)o).getID());
	}

	public int hashCode() {
		return this.mail.hashCode();
	}
}