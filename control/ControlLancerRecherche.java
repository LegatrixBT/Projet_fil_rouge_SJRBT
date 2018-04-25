package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;


import model.BaseMoteur;
import model.EntreeRecherche;
import model.Moteur;

public class ControlLancerRecherche{
	
	ControlGestionMoteurs controlGestionMoteurs = new ControlGestionMoteurs();
	private BaseMoteur baseMoteur = BaseMoteur.getInstance();
	
	List<Moteur> listeMoteurActif = controlGestionMoteurs.getListeMoteursActifs();

	public Set<EntreeRecherche> lancerRechercheTexteMotCle(String motCle) throws FileNotFoundException, ParseException{
		return baseMoteur.getMoteurPrincipal().rechercheTexteMotCle(motCle);
	}
	
	public Set<EntreeRecherche> lancerRechercheTexteFichier(String cheminFichier) throws FileNotFoundException, ParseException{
		return baseMoteur.getMoteurPrincipal().rechercheTexteFichier(cheminFichier);
	}
	
	public Set<EntreeRecherche> lancerRechercheImage(String cheminFichier) throws FileNotFoundException, ParseException{
		return baseMoteur.getMoteurPrincipal().rechercheImage(cheminFichier);
	}
	public Set<EntreeRecherche> lancerRechercheImageCouleur(Integer R, Integer G, Integer B) throws FileNotFoundException, UnsupportedEncodingException, ParseException {
		return baseMoteur.getMoteurPrincipal().rechercheImageCouleur(R, G, B);
	}
	
	/*
	public Set<EntreeRecherche> lancerRechercheTexteMot(String mot){
		
		Set<EntreeRecherche> resRecherche = new TreeSet<>();
		
		for (Moteur moteur : listeMoteurActif) {
			resRecherche = (moteur.rechercheTexteMotCle(mot));
		}
		return resRecherche;
				
	}*/
	
	
	public Set<EntreeRecherche> lancerMultiRechercheTexte(String mot) throws FileNotFoundException, ParseException{
		
		Set<EntreeRecherche> resRecherche = new TreeSet<>();
		List<Set> listSetRecherche = new ArrayList<>();
		
		for (Moteur moteur : listeMoteurActif) {
			try {
				resRecherche = moteur.rechercheTexteMotCle(mot);
			} catch (FileNotFoundException | ParseException e) {
				e.printStackTrace();
			}
			listSetRecherche.add(resRecherche);
		}
		resRecherche.clear();
		for (Set<EntreeRecherche> set : listSetRecherche) {
			
			for (EntreeRecherche entreeRecherche : set) {
				System.out.println(entreeRecherche.getCheminFichier().toString());
				resRecherche.add(entreeRecherche);
			}
		}
		return resRecherche;
	}
	

	
	public Set<EntreeRecherche> lancerRechercheTexteMotCleComplexe(String[] motsCle, String[] motsAExclure) throws FileNotFoundException, ParseException{ // Pour le moteur principal pas de multimoteur
		
		Set<EntreeRecherche> resRechPositif = new TreeSet<>();
		Set<EntreeRecherche> resRechNegatif = new TreeSet<>();
		TreeSet<EntreeRecherche> resRechComplexe = new TreeSet<>();
		ArrayList<Set<EntreeRecherche>> listeResMotsPos = new ArrayList<>();
		ArrayList<Set<EntreeRecherche>> listeResMotsNeg = new ArrayList<>();
		boolean motInclusNonTrouve = false;
		boolean motExclusTrouve = false;
		
		for (String mot : motsCle) {
			listeResMotsPos.add(this.lancerRechercheTexteMotCle(mot));
		}
		for (String mot : motsAExclure) {
			listeResMotsNeg.add(this.lancerRechercheTexteMotCle(mot));
		}
		for (EntreeRecherche entree : listeResMotsPos.get(0)) {
			for(int i = 1; i < listeResMotsPos.size(); i++) {
				if(!listeResMotsPos.get(i).contains(entree)) {
					motInclusNonTrouve = true;
					break;
				}
			}
			if(motInclusNonTrouve == false) {
				for (Set<EntreeRecherche> set : listeResMotsNeg) {
					if(set.contains(entree)) {
						motExclusTrouve = true;
						break;
					}
				}
			}
			if(!motExclusTrouve && !motInclusNonTrouve)
				resRechComplexe.add(entree);
		}
		return resRechComplexe;	
	}
	
	public Set<EntreeRecherche> lancerMultiRechercheImage(String cheminImage) throws FileNotFoundException, ParseException{
		
		Set<EntreeRecherche> resRecherche = new TreeSet<>();
		List<Set> listSetRecherche = new ArrayList<>();
		
		for (Moteur moteur : listeMoteurActif) {
			try {
				resRecherche = moteur.rechercheImage(cheminImage);
			} catch (FileNotFoundException | ParseException e) {
				e.printStackTrace();
			}
			listSetRecherche.add(resRecherche);
		}
		resRecherche.clear();
		for (Set<EntreeRecherche> set : listSetRecherche) {
			
			for (EntreeRecherche entreeRecherche : set) {
				System.out.println(entreeRecherche.getCheminFichier().toString());
				resRecherche.add(entreeRecherche);
			}
		}
		return resRecherche;
	}
	
	

	
}
