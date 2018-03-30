package model;

public class Moteur1 extends Moteur{


	//Singleton sur moteur1
	
	private static class MoteurHolder{	
		private static final Moteur1 instance = new Moteur1();
	}
	
	public static Moteur1 getInstance() {
		return MoteurHolder.instance;
	}
	
	// Méthodes du moteur 
	
	public Moteur1() {
		this.setNbMotDescripteur(5);
		this.setNnbBitIndexationImage(2);
		this.getNnbBitIndexationImage();
		this.getNbMotDescripteur();
		this.setMoteurPrincipal();
		this.setMoteurActif();
	}
	
	
	
	
	
}
