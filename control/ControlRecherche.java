package control;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Set;

import model.BaseMoteur;
import model.EntreeRecherche;

public class ControlRecherche {
	private BaseMoteur baseMoteur = BaseMoteur.getInstance();
	
	public Set<EntreeRecherche> lancerRechercheTexteMotCle(String motCle) throws FileNotFoundException, ParseException{
		return baseMoteur.getMoteurPrincipal().rechercheTexteMotCle(motCle);
	}
	
	public Set<EntreeRecherche> lancerRechercheTexteFichier(String cheminFichier) throws FileNotFoundException, ParseException{
		return baseMoteur.getMoteurPrincipal().rechercheTexteFichier(cheminFichier);
	}
	
	public Set<EntreeRecherche> lancerRechercheImage(String cheminFichier) throws FileNotFoundException, ParseException{
		return baseMoteur.getMoteurPrincipal().rechercheImage(cheminFichier);
	}
}
