package model;

public class UsineMoteur {
	
	private FactoryMoteur factoryMoteur;
	
	 	
	public UsineMoteur() {
		
		this.factoryMoteur = new FactoryMoteur();
	}
	
	public Moteur formerMoteur(TypeMoteur type, String nom) {
		
		Moteur moteur = this.factoryMoteur.creerMoteur(type, nom);
		BaseMoteur.getInstance().ajouterMoteur(moteur);
		
		return moteur;
	}

}
