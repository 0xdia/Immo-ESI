class Date implements Comparable<Date> {
	private int jour;
	private int mois;
	private int annee;
	public Date(int jour, int mois, int annee) {
		this.jour  = jour;
		this.mois  = mois;
		this.annee = annee; 
	}

	public int getJour() {return this.jour;}
	public int getMois() {return this.mois;}
	public int getAnnee() {return this.annee;}

	public void setJour(int jour) {this.jour = jour;}
	public void setMois(int mois) {this.mois = mois;}
	public void setAnnee(int annee) {this.annee = annee;}

	public void afficher() {
		System.out.println(jour + "/" + mois + "/" + annee);
	}

	public boolean equals(Date date) {
		return (   annee == date.getAnnee()
				&& mois  == date.getMois()
				&& jour  == date.getJour());
	}

	public int compareTo(Date date) {
		if (annee > date.getAnnee()) return -1;
		if (annee < date.getAnnee()) return 1;
		if (mois  > date.getMois())  return -1;
		if (mois  < date.getMois())  return 1;
		if (jour  > date.getJour())  return -1;
		return 1;
	}
}