package Boundary;

import java.util.ArrayList;
import java.util.List;

import Control.ControlRecherche;

public class BoundaryRecherche {

	private ControlRecherche controlRecherche ;
	private int choix, choix2, choix3;
	Clavier clavier = new Clavier();
	private List<String> motCherches = new ArrayList<>();
	private String mot = " ", ret ;
	
	public BoundaryRecherche (ControlRecherche cont)
	{
		controlRecherche = cont;
		choix = 0;
	}
	
	public void rechercherUnFichier()
	{
		System.out.println("Type de recherche :\n");
		while((choix != 1)&&(choix != 2)&&(choix!=3)&&(choix!=4))
		{
			System.out.println("1. Son");
			System.out.println("2. Image");
			System.out.println("3. Texte");
			System.out.println("4. Retour au menu précédent");
			choix = clavier.entrerClavierInt();
			
			switch(choix)
			{
			case 1: 
				System.out.println("Sélectionner le fichier son recherché : ");
				System.out.println("1. beboop.wav");
				System.out.println("2. boobip.wav");
				System.out.println("3. test_boop_boop_2000.wav");
				choix2 = clavier.entrerClavierInt();
				ret = controlRecherche.rechercheSon(choix2);
				System.out.println("Resultat de la recherche (meilleur résultat en premier) : ");
				System.out.println(ret);
				break;
				
			case 2: 
				do
				{
					System.out.println("Image en couleurs ou en niveaux de gris ?");
					System.out.println("1. Couleurs");
					System.out.println("2. Niveaux de gris");
					choix2 = clavier.entrerClavierInt();
					
					if((choix2 != 1)&&(choix2 != 2))
						System.out.println("Saisir 1 ou 2");
					
				}while((choix2 != 1)&&(choix2 != 2));
				
				if(choix2 == 1)
				{
					do
					{
						System.out.println("Sélectionner la couleur dominante recherchée");
						System.out.println("1. Bleu");
						System.out.println("2. Rouge");
						System.out.println("3. Vert");
						choix3 = clavier.entrerClavierInt();
						
						if((choix2 != 1)&&(choix2 != 2)&&(choix!=3))
							System.out.println("Saisir 1 ou 2 ou 3");
					}while((choix2 != 1)&&(choix2 != 2)&&(choix!=3));
					
					ret = controlRecherche.rechercheImage(choix2,choix3);
					System.out.println("RECHERCHE IMAGE COULEUR\nResultat de la recherche (meilleur résultat en premier) : ");
					System.out.println(ret);
					
				}
				else
				{
					do
					{
					System.out.println("Sélectionner la couleur dominante recherchée");
					System.out.println("1. Blanc");
					System.out.println("2. Gris");
					System.out.println("3. Noir");
					choix3 = clavier.entrerClavierInt();
					
					if((choix2 != 1)&&(choix2 != 2)&&(choix!=3))
						System.out.println("Saisir 1 ou 2 ou 3");
					}while((choix2 != 1)&&(choix2 != 2)&&(choix !=3));
					
					ret = controlRecherche.rechercheImage(choix2,choix3);
					System.out.println("RECHERCHE IMAGE NIVEAUX GRIS\nResultat de la recherche (meilleur résultat en premier) : ");
					System.out.println(ret);
				}
				break;
				
			case 3: 
				System.out.println("Entrer les mots recherchés.\nEntrer 1 pour finir la saisie.");
				
				do
				{
					if(mot != "1")
						motCherches.add(mot);
					
					mot = clavier.entrerClavierString();
					mot = mot.intern();
					
				}while(mot != "1");
				
				ret = controlRecherche.rechercheTexte(motCherches);
				System.out.println("Resultat de la recherche (Nombre d'occurence par texte) : ");
				System.out.println(ret);
				break;
				
			case 4: System.out.println("Retour au menu précédent ...");
			//insérer la méthode du menu précédent
				break;
				
			default: System.out.println("Choix invalide ! Saisir 1,2,3 ou 4.");
			break;
			}
		}
	}
}
