package control;

import model.Moteur;

public class ControlModifierParamIndex {
	
	Moteur moteur;
	
	public ControlModifierParamIndex() {
		moteur = Moteur.getInstance();
	}
	
	public void modifierNbBitsIndex(int nbBits) {
		moteur.setNbBitsIndexationImage(nbBits);
	}
	
}
