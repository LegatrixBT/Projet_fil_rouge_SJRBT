package model;

public class FactoryMoteur { // permet de cr�er des moteurs 
	
	// la cr�ation du moteur en fonction du type dans la fabrique
	
	public Moteur creerMoteur(TypeMoteur type) {
		
		Moteur moteur  = null;
		
		switch(type) {
		
			case MOTEUR1 :
				moteur = new Moteur1();
				moteur = Moteur1.getInstance();
			break;
			
			case MOTEUR2 :
				moteur = new Moteur2();
				moteur = Moteur2.getInstance();
			break;
		}
		return moteur; 	
	}

}
