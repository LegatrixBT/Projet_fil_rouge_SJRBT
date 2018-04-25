package test;

import model.Moteur1;

public class TestRechercheJNI {
	
	public static Moteur1 moteur = new Moteur1();
	
	public static void main(String[] args) {
		moteur.ajouterImage("IMG_NB/63.txt");
		moteur.indexationImage();
	}
	
}
