package control;

import model.Profil;

public class ControlSeConnecter {
	
	private Profil profil = Profil.getInstance();
	
	public boolean seConnecter(String mdp) {
		boolean mdpOK = mdp.equals(profil.getMdp());
		if(mdpOK)
			profil.connexionAdmin();
		return mdpOK;
	}
	
	public void seDeconnecter() {
		profil.deconnexionAdmin();
	}
	
}
