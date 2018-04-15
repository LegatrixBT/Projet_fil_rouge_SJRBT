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
				listeMoteurActif.add(moteur);
			}	
		}
		if (listeMoteurActif.isEmpty())
			System.out.println("La liste de moteur actifs est vide");
		return listeMoteurActif;

	}
  
	public List<Moteur> getMoteurInactifs() {
		
		List<Moteur> listeMoteurInactif = new ArrayList <>();// attention si null pointer exception
		List<Moteur> listeMoteurLue = new ArrayList <>();
		
		listeMoteurLue = baseMoteur.getListeMoteur();
		
		for (Moteur moteur : listeMoteurLue) {
			if (!moteur.isMoteurActif()) {
				listeMoteurInactif.add(moteur);
			}			
		}
		
		if (listeMoteurInactif.isEmpty())
			System.out.println("Tous les moteurs sont d�j� activ�s");
		return listeMoteurInactif;
		
	}
}
	
	

