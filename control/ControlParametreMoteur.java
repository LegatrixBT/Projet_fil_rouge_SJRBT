package control;

import java.util.ArrayList;
import java.util.List;

import model.BaseMoteur;
import model.Moteur;

public class ControlParametreMoteur {
	
	
	public List<String> getSpecMoteur(Moteur moteur) {
		
		List<String> listeParametreMoteur = new ArrayList<String>();
		
		listeParametreMoteur = moteur.getSpecMoteur();
		
		return listeParametreMoteur;
	}

}
