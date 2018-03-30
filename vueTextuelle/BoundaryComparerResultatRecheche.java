package vueTextuelle;

import java.util.ArrayList;
import java.util.List;

import control.ControlMoteurActifs;
import model.Moteur;

public class BoundaryComparerResultatRecheche {

	ControlMoteurActifs controlMoteurActifs = new ControlMoteurActifs();
	Control
	
	
	public void comparerResultatRecherche () {
		
		List<Moteur> listeMoteurActif = new ArrayList <>();
		listeMoteurActif = controlMoteurActifs.getMoteursActifs();
		
		for (Moteur moteur : listeMoteurActif) {
			
		}
	}
	
}
