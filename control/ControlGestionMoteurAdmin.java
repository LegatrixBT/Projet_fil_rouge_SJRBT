package control;

import java.util.List;
import java.util.Observer;

import model.BaseMoteur;
import model.Moteur;

public class ControlGestionMoteurAdmin {
	
	private Moteur moteurPrincipal;
	private ControlGetMoteurPrincipal controlGetMoteurPrincipal;
	private BaseMoteur baseMoteur = BaseMoteur.getInstance();
	
	public ControlGestionMoteurAdmin() {
		controlGetMoteurPrincipal = new ControlGetMoteurPrincipal();
		moteurPrincipal = controlGetMoteurPrincipal.getMoteurPrincipal();
	}
	
	public ControlGestionMoteurAdmin(ControlGetMoteurPrincipal controlGetMoteurPrincipal) {
		this.controlGetMoteurPrincipal = controlGetMoteurPrincipal;
		moteurPrincipal = controlGetMoteurPrincipal.getMoteurPrincipal();
	}
	
	public void modifierNbBitsIndex(int nbBits) {
		moteurPrincipal.setNnbBitIndexationImage(nbBits);
	}
	
	public void modifierNbMotsDesc(int nbMotsDesc) {
		moteurPrincipal.setNbMotDescripteur(nbMotsDesc);
	}
	
	public void setObserver(Observer o) {
		List<Moteur> listeMoteursActifs = baseMoteur.getListeMoteur();
		for (Moteur moteur : listeMoteursActifs) {
			moteur.addObserver(o);
		}
	}
	
	public boolean lancerIndexationImage() {
		return moteurPrincipal.indexationImage();
	}
	
	public boolean lancerIndexationTexte() {
		return moteurPrincipal.indexationTexte();
	}
	
	public void miseAJourMoteurPrincipal() {
		moteurPrincipal = controlGetMoteurPrincipal.getMoteurPrincipal();
	}
	
	public Integer[] visualiserParamIndex() {
		Integer[] parametres = new Integer[2];		
		parametres[0] = moteurPrincipal.getNnbBitIndexationImage();
		parametres[1] = moteurPrincipal.getNbMotDescripteur();
		return parametres;
	}
	
}