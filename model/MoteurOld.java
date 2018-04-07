package model;

import java.util.Observable;

public class MoteurOld extends Observable{
	
	private int nbBitsIndexationImage;
	
	//Methodes du singleton
	
	private MoteurOld() {
		nbBitsIndexationImage = 3;
	}

	private static class MoteurHolder{
		private static final MoteurOld instance = new MoteurOld();
	}
	
	public static MoteurOld getInstance() {
		return MoteurHolder.instance;
	}
	
	//Méthodes de la classe
	
	public int getNbBitsIndexationImage() {
		return nbBitsIndexationImage;
	}

	public void setNbBitsIndexationImage(int nbBitsIndexationImage) {
		this.setChanged();
		this.nbBitsIndexationImage = nbBitsIndexationImage;
		this.notifyObservers(nbBitsIndexationImage);
	}
	
	public int getParamIndex() {
		return nbBitsIndexationImage;
	}
	
	public boolean indexationImage() {
		//TODO integrer partie C
		System.out.println("Indexation image\n");
		return true;
	}
	
	public boolean indexationTexte() {
		//TODO integrer partie C
		System.out.println("Indexation texte\n");
		return true;
	}
	
}
