package model;

public class UsineMoteur {
	
	private FactoryMoteur factoryMoteur;
	
	 	
	public UsineMoteur() {
		
		this.factoryMoteur = new FactoryMoteur();
	}
	
	public Moteur formerMoteur(TypeMoteur type) {
		
		Moteur moteur = this.factoryMoteur.creerMoteur(type);
		BaseMoteur.getInstance().ajouterMoteur(moteur);
		
		return moteur;
	}

}
