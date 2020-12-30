class Message{
	private Client client;
	private Bien bien;
	private boolean transaction;
	private boolean renseignement;
	private String contenu;
	public Message(Client client,Bien bien,boolean transaction,boolean renseignement,String contenu) {
		this.client = client;
		this.bien = bien;
		this.transaction = transaction;
		this.renseignement = renseignement;
		this.contenu = contenu;
	}

	public Client getClient() {return client;}
	public Bien getBien() {return bien;}
	public boolean getTransaction() {return transaction;}
	public boolean getRenseignement() {return renseignement;}
	public String getContenu() { return contenu;}

	public void afficher() {
		System.out.println("Message :");
		client.afficher();
		if(transaction) System.out.print("Demande de transaction");
		if(renseignement) System.out.print("Demande de Renseignement");
		System.out.println("Contenu : \n "+contenu);
	}

	public boolean equals (Message m) {
		return (this.client.equals(m.getClient()) &&
			        this.bien.equals(m.getBien()) &&
			(this.transaction==m.getTransaction()) &&
		(this.renseignement==m.getRenseignement()) &&
			this.contenu.equals(m.getContenu()));
	}
}