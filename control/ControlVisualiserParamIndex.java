package control;

import model.Moteur;

public class ControlVisualiserParamIndex {
	
	Moteur moteur;

	public ControlVisualiserParamIndex() {
		this.moteur = Moteur.getInstance();
	}
	
	public String visualiserParamIndex() {
		
		String texte = "----------Parametres d'indexation----------\n";
		texte += moteur.getParamIndex();
		
		return texte;
	}
	
}
