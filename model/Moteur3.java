package model;

public class Moteur3 extends Moteur{
	
	//Singleton sur moteur3
	
	private static class MoteurHolder{	
		private static final Moteur3 instance = new Moteur3();
	}
	
	public static Moteur3 getInstance() {
		return MoteurHolder.instance;
	}
	
	// Méthodes du moteur 

	public Moteur3() {
		super();
		this.setNbMotDescripteur(15);
		this.setNnbBitIndexationImage(3);
	}
	
	

}