package control;

import model.Moteur;

public class ControlAccesMenuAdmin {

	private Moteur moteur;
	
	public ControlAccesMenuAdmin() {
		this.moteur = Moteur.getInstance();
	}
	
	public boolean verifierMdp(String mdp) {
		return(mdp.equals(moteur.getMdp()));
	}
	
}
