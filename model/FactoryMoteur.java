package model;

public class FactoryMoteur { // permet de creer des moteurs 
	
	// la creation du moteur en fonction du type dans la fabrique
	
	public Moteur creerMoteur(TypeMoteur type,String nom) {
		
		Moteur moteur  = null;
		
		switch(type) {
		
			case MOTEUR1 :
				moteur = Moteur1.getInstance();
				moteur.setNom(nom);
			break;
			
			case MOTEUR2 :
				moteur = Moteur2.getInstance();
				moteur.setNom(nom);
			break;
		}
		return moteur; 	
	}

}
