package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public abstract class Moteur extends Observable{

	
	private int nbBitIndexationImage;
	private int nbMotDescripteur;
	private boolean moteurPrincipal;
	private boolean moteurActif;
	private String nomMoteur;   //    NOMMER CHAQUE MOTEUR SELON SES PARAMEMTRES 
	private FonctionsJNI fonctionsJNI;
	
	public Moteur() {
		fonctionsJNI = new FonctionsJNI();
	}

	public int getNbMotDescripteur() {
		return this.nbMotDescripteur;
	}
	
	public void setNbMotDescripteur(int nbMotDescripteur) {
		Integer[] parametres = new Integer[3];
		this.setChanged();
		this.nbMotDescripteur = nbMotDescripteur;
		parametres[0]=1; //changement parametres
		parametres[1] = nbBitIndexationImage;
		parametres[2] = nbMotDescripteur;
		this.notifyObservers(parametres);
	}
	
	public int getNnbBitIndexationImage() {
		return this.nbBitIndexationImage;
	}
	
	public void setNnbBitIndexationImage(int nbBitIndexationImage) {
		Integer[] parametres = new Integer[3];
		this.setChanged();
		this.nbBitIndexationImage = nbBitIndexationImage;
		parametres[0]=1; //changement parametres
		parametres[1] = nbBitIndexationImage;
		parametres[2] = nbMotDescripteur;
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
		Integer[] parametres = new Integer[3];
		this.moteurPrincipal = true;
		parametres[0]=0; //changement de moteur
		parametres[1] = nbBitIndexationImage;
		parametres[2] = nbMotDescripteur;
		this.notifyObservers(parametres);
	}
	
	public void unsetMoteurPrincipal() {
		this.moteurPrincipal = false;
	}
	
	public boolean isMoteurActif() {
		return moteurActif;
	}
	
	public void setMoteurActif() {
		this.setChanged();
		Integer[] parametres = new Integer[3];
		this.moteurActif = true;
		parametres[0]=2; //Activation/desactivation moteur
		this.notifyObservers(parametres);
	}
	
	public void unsetMoteurActif() {
		this.setChanged();
		Integer[] parametres = new Integer[3];
		this.moteurActif = false;
		parametres[0]=2; //Activation/desactivation moteur
		this.notifyObservers(parametres);
	}
	
	public boolean indexationTexte() {
		System.out.println("Indexation texte avec descripteur de taille :" + getNbMotDescripteur() + "\n");
		fonctionsJNI.indexationTexte();
		return true;
	}

	public boolean indexationImage() {
		System.out.println("Indexation image avec " + getNnbBitIndexationImage() + " bits de quantification...\n");
		fonctionsJNI.indexationImage();
		return true;
	}
	
	public void setNom(String nom) {
		nomMoteur = nom;
	}
	
	public String toString() {
		return nomMoteur;
	}
	
	public Set<EntreeRecherche> rechercheImage(String cheminImage){
		Set<EntreeRecherche> setRes = new TreeSet<>();
		File listeRes = new File("liste_res");
		String cheminTemp;
		Float valeurTemp;
		try {
			fonctionsJNI.rechercheImage(cheminImage);
			Scanner sc = new Scanner(listeRes); //On lit le fichier des resultats
			while(sc.hasNext()) { //tant qu'on a pas lu tout le fichier...
				cheminTemp = sc.next();
				try {
					valeurTemp = (Float) NumberFormat.getInstance().parse(sc.next()).floatValue(); //conversion en float d'un string de type xx,xx
					setRes.add(new EntreeRecherche(cheminTemp, valeurTemp)); //stockage dans le set du resultat
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			sc.close();
			try {
				Files.deleteIfExists(listeRes.toPath()); //Si le fichier liste_res existe, on le supprime
			} catch (IOException e) {
				e.printStackTrace();
			}
			return setRes;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Set<EntreeRecherche> rechercheTexteFichier(String cheminTexte){ //voir commentaires au dessus
		Set<EntreeRecherche> setRes = new TreeSet<>();
		File listeRes = new File("liste_res");
		String cheminTemp;
		Float valeurTemp;
		try {
			fonctionsJNI.rechercheTexteFichier(cheminTexte);
			Scanner sc = new Scanner(listeRes);
			while(sc.hasNext()) {
				cheminTemp = sc.next();
				try {
					valeurTemp = (Float) NumberFormat.getInstance().parse(sc.next()).floatValue();
					setRes.add(new EntreeRecherche(cheminTemp, valeurTemp));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			sc.close();
			try {
				Files.deleteIfExists(listeRes.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return setRes;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Set<EntreeRecherche> rechercheTexteMotCle(String motCle){ //voir commentaires au dessus
		Set<EntreeRecherche> setRes = new TreeSet<>();
		File listeRes = new File("liste_res");
		String cheminTemp;
		Float valeurTemp;
		try {
			fonctionsJNI.rechercheTexteMotCle(motCle);
			Scanner sc = new Scanner(listeRes);
			while(sc.hasNext()) {
				cheminTemp = sc.next();
				try {
					valeurTemp = (Float) NumberFormat.getInstance().parse(sc.next()).floatValue();
					setRes.add(new EntreeRecherche(cheminTemp, valeurTemp));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			sc.close();
			try {
				Files.deleteIfExists(listeRes.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return setRes;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void ajouterImage(String cheminImage) {
		fonctionsJNI.ajouterImage(cheminImage);
	}
	
	public void ajouterTexte(String cheminTexte) {
		fonctionsJNI.ajouterTexte(cheminTexte);
	}
}
