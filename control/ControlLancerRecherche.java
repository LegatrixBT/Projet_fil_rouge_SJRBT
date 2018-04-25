package control;

import java.util.ArrayList;
import java.util.Comparator;
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
		TreeSet<EntreeRecherche> resRecherche = new TreeSet<>();
		for(EntreeRecherche entree : baseMoteur.getMoteurPrincipal().rechercheTexteMotCle(motCle)) {
			resRecherche.add(entree);
		}
		return resRecherche;
	}
	
	public Set<EntreeRecherche> lancerRechercheTexteFichier(String cheminFichier) throws FileNotFoundException, ParseException{
		TreeSet<EntreeRecherche> resRecherche = new TreeSet<>(new Comparator<EntreeRecherche>() {

			@Override
			public int compare(EntreeRecherche o1, EntreeRecherche o2) {
				return o1.getDistance().compareTo(o2.getDistance());
			}
		});
		for(EntreeRecherche entree : baseMoteur.getMoteurPrincipal().rechercheTexteFichier(cheminFichier)) {
			resRecherche.add(entree);
		}
		return resRecherche;
	}
	
public Set<EntreeRecherche> lancerMultiRechercheTexteFichier(String cheminFichier) throws FileNotFoundException, ParseException{
		
		Set<EntreeRecherche> resRecherche = new TreeSet<>(new Comparator<EntreeRecherche>() {

			@Override
			public int compare(EntreeRecherche o1, EntreeRecherche o2) {
				return o1.getDistance().compareTo(o2.getDistance());
			}
		});
		List<Set> listSetRecherche = new ArrayList<>();
		
		for (Moteur moteur : listeMoteurActif) {
			try {
				resRecherche = lancerRechercheTexteFichier(cheminFichier);
			} catch (FileNotFoundException | ParseException e) {
				e.printStackTrace();
			}
			listSetRecherche.add(resRecherche);
		}
		resRecherche.clear();
		for (Set<EntreeRecherche> set : listSetRecherche) {
			
			for (EntreeRecherche entreeRecherche : set) {
				resRecherche.add(entreeRecherche);
			}
		}
		return resRecherche;
	}
	
	
	public Set<EntreeRecherche> lancerRechercheImage(String cheminFichier) throws FileNotFoundException, ParseException{
		TreeSet<EntreeRecherche> resRecherche = new TreeSet<>(new Comparator<EntreeRecherche>() {

			@Override
			public int compare(EntreeRecherche o1, EntreeRecherche o2) {
				return o1.getDistance().compareTo(o2.getDistance());
			}
		});
		for(EntreeRecherche entree : baseMoteur.getMoteurPrincipal().rechercheImage(cheminFichier)) {
			resRecherche.add(entree);
		}
		return resRecherche;
	}
	
	public Set<EntreeRecherche> lancerMultiRechercheImage(String cheminImage) throws FileNotFoundException, ParseException{
		
		Set<EntreeRecherche> resRecherche = new TreeSet<>(new Comparator<EntreeRecherche>() {

			@Override
			public int compare(EntreeRecherche o1, EntreeRecherche o2) {
				return o1.getDistance().compareTo(o2.getDistance());
			}
		});
		List<Set> listSetRecherche = new ArrayList<>();
		
		for (Moteur moteur : listeMoteurActif) {
			try {
				resRecherche = lancerRechercheImage(cheminImage);
			} catch (FileNotFoundException | ParseException e) {
				e.printStackTrace();
			}
			listSetRecherche.add(resRecherche);
		}
		resRecherche.clear();
		for (Set<EntreeRecherche> set : listSetRecherche) {
			
			for (EntreeRecherche entreeRecherche : set) {
				resRecherche.add(entreeRecherche);
			}
		}
		return resRecherche;
	}
	
	public Set<EntreeRecherche> lancerRechercheImageCouleur(Integer R, Integer G, Integer B) throws FileNotFoundException, UnsupportedEncodingException, ParseException {
		TreeSet<EntreeRecherche> resRecherche = new TreeSet<>(new Comparator<EntreeRecherche>() {

			@Override
			public int compare(EntreeRecherche o1, EntreeRecherche o2) {
				return o1.getDistance().compareTo(o2.getDistance());
			}
		});
		for(EntreeRecherche entree : baseMoteur.getMoteurPrincipal().rechercheImageCouleur(R, G, B)) {
			resRecherche.add(entree);
		}
		return resRecherche;
	}	
	
	public Set<EntreeRecherche> lancerMultiRechercheImageCouleur(Integer R, Integer G, Integer B) throws FileNotFoundException, UnsupportedEncodingException, ParseException {
		Set<EntreeRecherche> resRecherche = new TreeSet<>(new Comparator<EntreeRecherche>() {

			@Override
			public int compare(EntreeRecherche o1, EntreeRecherche o2) {
				return o1.getDistance().compareTo(o2.getDistance());
			}
		});
		
		List<Set> listSetRecherche = new ArrayList<>();
		
		for (Moteur moteur : listeMoteurActif) {
			try {
				resRecherche = lancerRechercheImageCouleur(R, G, B);
			} catch (FileNotFoundException | ParseException e) {
				e.printStackTrace();
			}
			listSetRecherche.add(resRecherche);
		}
		resRecherche.clear();
		for (Set<EntreeRecherche> set : listSetRecherche) {
			
			for (EntreeRecherche entreeRecherche : set) {
				resRecherche.add(entreeRecherche);
			}
		}
		return resRecherche;
	}	
	
	public Set<EntreeRecherche> lancerMultiRechercheTexteMotCle(String mot) throws FileNotFoundException, ParseException{
		
		Set<EntreeRecherche> resRecherche = new TreeSet<>(new Comparator<EntreeRecherche>() {

			@Override
			public int compare(EntreeRecherche o1, EntreeRecherche o2) {
				return o1.getDistance().compareTo(o2.getDistance());
			}
		});
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
				resRecherche.add(entreeRecherche);
			}
		}
		return resRecherche;
	}
public Set<EntreeRecherche> lancerMultiRechercheTexteMotCleComplexe(String[] motsCle, String[] motsAExclure) throws FileNotFoundException, ParseException{
		
		Set<EntreeRecherche> resRecherche = new TreeSet<>(new Comparator<EntreeRecherche>() {

			@Override
			public int compare(EntreeRecherche o1, EntreeRecherche o2) {
				return o1.getDistance().compareTo(o2.getDistance());
			}
		});
		List<Set> listSetRecherche = new ArrayList<>();
		
		for (Moteur moteur : listeMoteurActif) {
			try {
				resRecherche = lancerRechercheTexteMotCleComplexe(motsCle, motsAExclure);
			} catch (FileNotFoundException | ParseException e) {
				e.printStackTrace();
			}
			listSetRecherche.add(resRecherche);
		}
		resRecherche.clear();
		for (Set<EntreeRecherche> set : listSetRecherche) {
			
			for (EntreeRecherche entreeRecherche : set) {
				resRecherche.add(entreeRecherche);
			}
		}
		return resRecherche;
	}
	

	
	public Set<EntreeRecherche> lancerRechercheTexteMotCleComplexe(String[] motsCle, String[] motsAExclure) throws FileNotFoundException, ParseException{ // Pour le moteur principal pas de multimoteur
		
		TreeSet<EntreeRecherche> resRechComplexe = new TreeSet<>(new Comparator<EntreeRecherche>() {

			@Override
			public int compare(EntreeRecherche o1, EntreeRecherche o2) {
				return o1.getDistance().compareTo(o2.getDistance());
			}
		});
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

	
}
