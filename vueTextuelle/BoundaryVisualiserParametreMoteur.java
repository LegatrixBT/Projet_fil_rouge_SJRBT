package vueTextuelle;

import java.util.ArrayList;
import java.util.List;

import control.ControlParametreMoteur;
import model.Moteur;

public class BoundaryVisualiserParametreMoteur {
	
	ControlParametreMoteur controlParametreMoteur = new ControlParametreMoteur();
	
	public void afficherParametreMoteur(Moteur moteur) {
		
		System.out.println("Affichage des parametres du moteur : " + moteur.toString());
		List<String> listeParametreMoteur = new ArrayList<String>();
		listeParametreMoteur =  controlParametreMoteur.getSpecMoteur(moteur);
		if (!listeParametreMoteur.isEmpty()) {
		System.out.println("Les paramètres du moteurs sont : \n" + "nbBitIndexationImage : " + listeParametreMoteur.get(0));
		System.out.println("nbBitIndexation : " + listeParametreMoteur.get(1));
		System.out.println("Moteur actif ?  : " + listeParametreMoteur.get(2));
		System.out.println("Moteur Principal ?: " + listeParametreMoteur.get(3) );
		System.out.println("Fin des parametres du moteur \n");
		}
		else System.out.println("Erreur dans la liste de parametre du moteur : " + moteur.toString());
	}

}
