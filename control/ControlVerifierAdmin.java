package control;

import model.Profil;

public class ControlVerifierAdmin {
	
	Profil profil = Profil.getInstance();
	
	public boolean verifierConnexionAdmin() {
		return profil.isAdmin();
	}
}
