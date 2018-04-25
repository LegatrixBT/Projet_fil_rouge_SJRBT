package control;

import java.io.File;
import java.util.List;
import java.util.Observer;

import model.BaseMoteur;
import model.Moteur;

public class ControlGestionMoteurAdmin {
	
	private ControlGetMoteurPrincipal controlGetMoteurPrincipal;
	private BaseMoteur baseMoteur = BaseMoteur.getInstance();
	
	public ControlGestionMoteurAdmin() {
		controlGetMoteurPrincipal = new ControlGetMoteurPrincipal();
	}
	
	public ControlGestionMoteurAdmin(ControlGetMoteurPrincipal controlGetMoteurPrincipal) {
		this.controlGetMoteurPrincipal = controlGetMoteurPrincipal;
	}
	
	public void initialisationLogiciel() {
		File baseImg = new File("base_descripteur_image");
		File baseTxt = new File("base_descripteur_texte");
		File listeImg = new File("liste_base_image");
		File listeTxt = new File("liste_base_texte");
		if((!baseImg.isDirectory() && !baseImg.exists()) || (!listeImg.isDirectory() && !listeImg.exists())) {
			baseMoteur.getMoteurPrincipal().indexationImage();
		}
		if((!baseTxt.isDirectory() && !baseTxt.exists()) || (!listeTxt.isDirectory() && !listeTxt.exists())) {
			baseMoteur.getMoteurPrincipal().indexationTexte();
		}
	}
	
	public void modifierNbBitsIndex(int nbBits) {
		baseMoteur.getMoteurPrincipal().setNnbBitIndexationImage(nbBits);
	}
	
	public void modifierNbMotsDesc(int nbMotsDesc) {
		baseMoteur.getMoteurPrincipal().setNbMotDescripteur(nbMotsDesc);
	}
	
	public void setObserver(Observer o) {
		List<Moteur> listeMoteursActifs = baseMoteur.getListeMoteur();
		for (Moteur moteur : listeMoteursActifs) {
			moteur.addObserver(o);
		}
	}
	
	public boolean lancerIndexationImage() {
		return baseMoteur.getMoteurPrincipal().indexationImage();
	}
	
	public boolean lancerIndexationTexte() {
		return baseMoteur.getMoteurPrincipal().indexationTexte();
	}
	
	public Integer[] visualiserParamIndex() {
		Integer[] parametres = new Integer[2];		
		parametres[0] = baseMoteur.getMoteurPrincipal().getNnbBitIndexationImage();
		parametres[1] = baseMoteur.getMoteurPrincipal().getNbMotDescripteur();
		return parametres;
	}
	
}
