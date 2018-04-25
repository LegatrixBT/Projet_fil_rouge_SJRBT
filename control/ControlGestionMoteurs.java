package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import model.BaseMoteur;
import model.Moteur;

public class ControlGestionMoteurs {
	
	private BaseMoteur baseMoteur = BaseMoteur.getInstance();
	
	public List<Moteur> getListeMoteurs(){
		return baseMoteur.getListeMoteur();
	}
	
	public List<Moteur> getListeMoteursInactifs(){
		List<Moteur> listeMoteur = baseMoteur.getListeMoteur();
		List<Moteur> listeMoteurInactif = new ArrayList<Moteur>();
		for (Moteur moteur : listeMoteur) {
			if(!moteur.isMoteurActif())
				listeMoteurInactif.add(moteur);
		}
		return listeMoteurInactif;
	}
	
	public List<Moteur> getListeMoteursActifs(){
		List<Moteur> listeMoteur = baseMoteur.getListeMoteur();
		List<Moteur> listeMoteurActif = new ArrayList<Moteur>();
		for (Moteur moteur : listeMoteur) {
			if(moteur.isMoteurActif())
				listeMoteurActif.add(moteur);
		}
		return listeMoteurActif;
	}
	
	public void setObserver(Observer o) {
		for (Moteur moteur : baseMoteur.getListeMoteur()) {
			moteur.addObserver(o);
		}
	}
	
	public void desactiverMoteurPrincipal() {
		baseMoteur.getMoteurPrincipal().unsetMoteurPrincipal();
	}
}
