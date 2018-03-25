package Model;

import java.util.ArrayList;
import java.util.List;

public class Recherche {

	private List<String> desc_mot1;
	private List<String> desc_mot2;
	private List<String> desc_mot3;
	private List<String> desc_mot4;
	
	public Recherche()
	{
		desc_mot1 = new ArrayList<>();
		desc_mot1.add("kebab");
		desc_mot1.add("broche");
		desc_mot1.add("santé");
		desc_mot1.add("moral");
		desc_mot1.add("diabète");
		desc_mot1.add("essort");
		
		desc_mot2 = new ArrayList<>();
		desc_mot2.add("ciel");
		desc_mot2.add("pollution");
		desc_mot2.add("aéroport");
		desc_mot2.add("manifestation");
		desc_mot2.add("police");
		desc_mot2.add("illegale");
		
		desc_mot3 = new ArrayList<>();
		desc_mot3.add("voiture");
		desc_mot3.add("suisse");
		desc_mot3.add("salon");
		desc_mot3.add("innovation");
		desc_mot3.add("française");
		desc_mot3.add("électrique");
		
		desc_mot4 = new ArrayList<>();
		desc_mot4.add("robots");
		desc_mot4.add("intelligence");
		desc_mot4.add("artificielle");
		desc_mot4.add("performants");
		desc_mot4.add("croissance");
		desc_mot4.add("mouvements");
	}
	
	public String recSon(int fichierCherche)
	{
		/*valeurs de choix :	1. beboop.wav
		 *						2. boobip.wav
		 *						3. test.wav
		 * */
		switch(fichierCherche)
		{
		case 1:
			return("Aucun résultat pour cette recherche !");
		
		case 2:
			return("- Klaxon_de_Honda.wav \n- Jouet_pour_enfant.wav \n- Michael_Jackson_billi_jean.wav\n");
			
		case 3:
			return("- Decollage_A380.wav \n- Patrouille_de_France2014.wav \n"
					+ "- Zone_Interdite_Airbus_230316.wav \n- Simulateur_avionChasse.wav");
		default :
			return("Erreur dans la saisie du fichier");
		}
		
	}
	
	public String recImage(int mode, int couleurCherchee)
	{
		/* mode = 1 >> couleur 
		 * 		= 2 >> niveaux de gris
		 * 
		 * si mode = 1
		 * 		couleurCherchee =
		 *  	1 >> Bleu
		 *		2 >> Rouge
		 *		3 >> Vert
		 * si mode = 2
		 * 		1 >> Blanc
		 * 		2 >> Gris
		 * 		3 >> Noir
		 * */
		
		if(mode == 1)
		{
			switch(couleurCherchee)
			{
			case 1:
				return("- Mer.png \n- AfficheNemoFilm.png \n- CielStockImage.png \n- PoissonTropicaux.png \n- DronesFurtifs.png");
			
			case 2:
				return("- CamionPompier.png \n- PommeDeluxe.png \n- DonnationSangBanniere.png "
						+ "\n- TomateOGM_2.png \n- CardinalRouge2001");
				
			case 3:
				return("- PelouseDider.png \n- PommeKitterd_23.png \n- Trefle4feuilles.png \n- LeroyMerlin_logo.png"
						+ " \n- Bulbizarre_sprite.png");
				
			default : return("Erreur dans la saisie");
			}
		}
		else
		{
			switch(couleurCherchee)
			{
			case 1:
				return("- Montagne_Alpes_2018.jpg \n- rueCoullond_jour.jpeg \n- MaMaison.png");
			
			case 2:
				return("- Tableau_Picasso_02.png \n- Visite_Londres_filtre.png \n- Selfie_Paris_273.jpg");
				
			case 3:
				return("- rueCoullond_soir.jpeg \n- PieceMoteur_recto.png \n- Ninja_costume_face.png");
				
			default : return("Erreur dans la saisie");
			}
		}
	}
	
	public String recTexte(List<String> motCherches)
	{
		int OccurenceTexte[] = new int[4];
		
		for(int j = 0; j < 6 ; j++)
		{
			for(int i = 0; i < motCherches.size() ; i++)
			{
				if(motCherches.get(i) == desc_mot1.get(j))
					OccurenceTexte[0] += 1;
				
				if(motCherches.get(i) == desc_mot2.get(j))
					OccurenceTexte[1] += 1;
				
				if(motCherches.get(i) == desc_mot3.get(j))
					OccurenceTexte[2] += 1;
				
				if(motCherches.get(i) == desc_mot4.get(j))
					OccurenceTexte[3] += 1;
			}
		}		
		
		return("- Texte1 : " + OccurenceTexte[0] + "\n- Texte2 : " + OccurenceTexte[1] + "\n- Texte3 : " 
				+ OccurenceTexte[2] + "\n- Texte4 : " + OccurenceTexte[3]);
	}
}
