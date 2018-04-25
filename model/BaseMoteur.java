package model;

import java.util.ArrayList;
import java.util.List;

public class BaseMoteur {

	List<Moteur> listeMoteur;
	
	
	private BaseMoteur() {
		listeMoteur = new ArrayList<Moteur>();
	}
	
	private static class baseMoteurHolder{
		private static final BaseMoteur instance = new BaseMoteur();
	}

	public static BaseMoteur getInstance() {
		return baseMoteurHolder.instance;
	}
	
	
	public void ajouterMoteur(Moteur moteur){
		listeMoteur.add(moteur);
	}
	
	public String toString() {
		return ("Menu [listeMoteur=" + listeMoteur + "]");
	}
		
	public List<Moteur> getListeMoteur(){
		return listeMoteur;
	}
	
	public Moteur choixMoteur(int numeroMoteur){
		return listeMoteur.get(numeroMoteur - 1);
		
	}
	
	public Moteur getMoteurPrincipal() {
		
		Moteur moteurPrincipal = null;
		for (Moteur moteur : listeMoteur) {
			if (moteur.isMoteurPrincipal())
				moteurPrincipal = moteur;
		}
		return moteurPrincipal;
	}
		
}
