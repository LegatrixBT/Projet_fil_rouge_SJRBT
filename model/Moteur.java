package model;

public class Moteur {
	
	private int nbBitsIndexationImage;
	
	//Methodes du singleton
	
	private Moteur() {
		nbBitsIndexationImage = 3;
	}

	private static class MoteurHolder{
		private static final Moteur instance = new Moteur();
	}
	
	public static Moteur getInstance() {
		return MoteurHolder.instance;
	}
	
	//Méthodes de la classe
	
	public int getNbBitsIndexationImage() {
		return nbBitsIndexationImage;
	}

	public void setNbBitsIndexationImage(int nbBitsIndexationImage) {
		this.nbBitsIndexationImage = nbBitsIndexationImage;
	}
	
	public String getParamIndex() {
		String texte = "";
		texte += "Image:\n";
		texte += "	Nombre de bits de quantification : " + this.getNbBitsIndexationImage() + "\n";
		return texte;
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
