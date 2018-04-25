package model;

public class Moteur2 extends Moteur{
	
	//Singleton sur moteur2
	
	private static class MoteurHolder{	
		private static final Moteur2 instance = new Moteur2();
	}
	
	public static Moteur2 getInstance() {
		return MoteurHolder.instance;
	}
	
	// Méthodes du moteur 

	public Moteur2() {
		super();
		this.setNbMotDescripteur(10);
		this.setNnbBitIndexationImage(2);
	}
	
	

}




	
	