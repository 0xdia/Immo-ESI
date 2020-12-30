class Wilaya 
{
	private String name;
	private double prix_mCarre;
	public Wilaya(String name,double prix) {
		this.name   = name;
		prix_mCarre = prix;
	}
	public String getName() {
		return name;
	}
	public double getPrix_mCarre() {
		return prix_mCarre;
	}

	public void afficher() {
		System.out.println("Wilaya: " + this.name);
		System.out.println("Prix de metre carre: " + this.prix_mCarre + " DA/mm");
	}

	public boolean equals(Object o) {
		return name.equals(((Wilaya) o).getName());
	}

	public int hashCode() {
		return this.name.hashCode();
	}
}