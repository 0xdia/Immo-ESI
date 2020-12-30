class Client {
	private String nom;
	private String prenom;
	public Client (String nom,String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}
	public String getNom() {return nom;}
	public String getPrenom() {return prenom;}
	public void afficher() {
		System.out.println("Client : "+ nom + "  "+prenom);
	}
	public boolean equals(Object o) {
		return (nom.equals(((Client) o).getNom()) &&
				prenom.equals(((Client) o).getNom()));
	}
}