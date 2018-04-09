package control;

import model.Profil;

public class ControlChangerMdpAdmin {
	
	private Profil profil = Profil.getInstance();
		
	public boolean verifierMdp(String mdp) {
		boolean mdpOK = mdp.equals(profil.getMdp());
		return mdpOK;
	}
	
	public void changerMdp(String mdp) {
		profil.setMdp(mdp);
	}
}
