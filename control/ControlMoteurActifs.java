package control;

import java.util.ArrayList;
import java.util.List;

import model.BaseMoteur;
import model.Moteur;

public class ControlMoteurActifs {
	
	
	BaseMoteur baseMoteur = BaseMoteur.getInstance();
	
	public List<Moteur> getMoteursActifs () {
		
		List<Moteur> listeMoteurActif = new ArrayList <>();// attention si null pointer exception
		List<Moteur> listeMoteurLue = new ArrayList <>();
		
		listeMoteurLue = baseMoteur.getListeMoteur();
		
		for (Moteur moteur : listeMoteurLue) {
			if (moteur.isMoteurActif()) {
				//System.out.println("Moteur x actif  " + moteur + "\n");
				listeMoteurActif.add(moteur);
			}	
		}
		if (listeMoteurActif.isEmpty())
			System.out.println("La liste de moteur actifs est vide");
		return listeMoteurActif;

	}
	
}
	
	

