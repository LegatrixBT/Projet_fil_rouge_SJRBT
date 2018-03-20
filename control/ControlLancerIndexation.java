package control;

import model.Moteur;

public class ControlLancerIndexation {
	
	Moteur moteur;
	
	public ControlLancerIndexation() {
		moteur = Moteur.getInstance();
	}
	
	public boolean lancerIndexationImage() {
		return moteur.indexationImage();
	}
	
	public boolean lancerIndexationTexte() {
		return moteur.indexationTexte();
	}
	
}
