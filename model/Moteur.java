package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public abstract class Moteur extends Observable{

	
	private int nbBitIndexationImage;
	private int nbMotDescripteur;
	private boolean moteurPrincipal;
	private boolean moteurActif;
	private String nomMoteur;   //    NOMMER CHAQUE MOTEUR SELON SES PARAMEMTRES 

	public int getNbMotDescripteur() {
		return this.nbMotDescripteur;
	}
	
	public void setNbMotDescripteur(int nbMotDescripteur) {
		Integer[] parametres = new Integer[2];
		this.setChanged();
		this.nbMotDescripteur = nbMotDescripteur;
		parametres[0] = nbBitIndexationImage;
		parametres[1] = nbMotDescripteur;
		this.notifyObservers(parametres);
	}
	
	public int getNnbBitIndexationImage() {
		return this.nbBitIndexationImage;
	}
	
	public void setNnbBitIndexationImage(int nbBitIndexationImage) {
		Integer[] parametres = new Integer[2];
		this.setChanged();
		this.nbBitIndexationImage = nbBitIndexationImage;
		parametres[0] = nbBitIndexationImage;
		parametres[1] = nbMotDescripteur;
		this.notifyObservers(parametres);
	}
	
	public List<String> getSpecMoteur() {
		//System.out.println("Les paramètres du moteur sont les suivants : \n");
		List<String> listeParametreMoteur = new ArrayList<String>();
		listeParametreMoteur.add(String.valueOf(nbBitIndexationImage));
		listeParametreMoteur.add(String.valueOf(nbMotDescripteur));
		listeParametreMoteur.add(String.valueOf(moteurActif));
		listeParametreMoteur.add(String.valueOf(moteurPrincipal));
		//System.out.println("1 : bit indexation 2 mot desc 3 moteur actif 4 moteur principal : " + listeParametreMoteur.toString());
		return listeParametreMoteur;
		
	}
	
	public boolean isMoteurPrincipal() {
		return moteurPrincipal;
	}
	
	public void setMoteurPrincipal() {
		this.setChanged();
		this.moteurPrincipal = true;
		this.notifyObservers(this);
	}
	
	public void unsetMoteurPrincipal() {
		this.moteurPrincipal = false;
	}
	
	public boolean isMoteurActif() {
		return moteurActif;
	}
	
	public void setMoteurActif() {
		this.moteurActif = true;
	}
	
	public void unsetMoteurActif() {
		this.moteurActif = false;
	}
	
	public boolean indexationTexte() {
		System.out.println("Indexation texte avec descripteur de taille :" + getNbMotDescripteur() + "\n");
		return true;
	}

	public boolean indexationImage() {
		System.out.println("Indexation image avec " + getNnbBitIndexationImage() + " bits de quantification...\n");
		return true;
	}
	
	public void setNom(String nom) {
		nomMoteur = nom;
	}
	
	public String toString() {
		return nomMoteur;
	}
}
