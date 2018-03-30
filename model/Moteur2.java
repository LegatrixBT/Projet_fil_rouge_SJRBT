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
	
	this.setNbMotDescripteur(10);
	this.setNnbBitIndexationImage(1);
	this.getNnbBitIndexationImage();
	this.getNbMotDescripteur();
}
	
	

}




	
	