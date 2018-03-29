package control;

import model.Moteur;

public class ControlVisualiserParamIndex {
	
	Moteur moteur;

	public ControlVisualiserParamIndex() {
		this.moteur = Moteur.getInstance();
	}
	
	public int visualiserParamIndex() {
		
		return moteur.getParamIndex();
	}
	
}
