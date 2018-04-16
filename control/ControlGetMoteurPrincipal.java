package control;

import java.util.List;

import model.Moteur;

public class ControlGetMoteurPrincipal {
	ControlMoteurActifs controlMoteurActifs;
	
	public ControlGetMoteurPrincipal() {
		controlMoteurActifs = new ControlMoteurActifs();
	}
	
	public ControlGetMoteurPrincipal(ControlMoteurActifs controlMoteurActifs) {
		this.controlMoteurActifs = new ControlMoteurActifs();
	}
	
	public Moteur getMoteurPrincipal() {
		List<Moteur> listeMoteursActifs = controlMoteurActifs.getMoteursActifs();
		for (Moteur moteur : listeMoteursActifs) {
			if(moteur.isMoteurPrincipal())
				return moteur;
		}
		return null;
	}
}
