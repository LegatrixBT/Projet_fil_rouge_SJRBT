package control;

import java.util.Set;

import model.BaseMoteur;
import model.EntreeRecherche;

public class ControlRecherche {
	private BaseMoteur baseMoteur = BaseMoteur.getInstance();
	
	public Set<EntreeRecherche> lancerRechercheTexteMotCle(String motCle){
		return baseMoteur.getMoteurPrincipal().rechercheTexteMotCle(motCle);
	}
	
	public Set<EntreeRecherche> lancerRechercheTexteFichier(String cheminFichier){
		return baseMoteur.getMoteurPrincipal().rechercheTexteFichier(cheminFichier);
	}
	
	public Set<EntreeRecherche> lancerRechercheImage(String cheminFichier){
		return baseMoteur.getMoteurPrincipal().rechercheImage(cheminFichier);
	}
}
