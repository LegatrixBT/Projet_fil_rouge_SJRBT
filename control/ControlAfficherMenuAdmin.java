package control;

import model.Moteur;

public class ControlAfficherMenuAdmin {
	
	private Moteur moteur;
	
	public ControlAfficherMenuAdmin() {
		moteur = Moteur.getInstance();
	}
	
	public boolean verifierMdp(String mdp) {
		return(mdp.equals(moteur.getMdp()));
	}
	
	public void changerMdp(String mdp) {
		moteur.setMdp(mdp);
	}
}
