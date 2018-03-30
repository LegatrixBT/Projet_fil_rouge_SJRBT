package vueTextuelle;

import java.util.List;

import control.ControlChangerMoteur;
import control.ControlMoteurActifs;
import model.BaseMoteur;
import model.Moteur;
import model.TypeMoteur;

public class BoundaryChoisirMoteur {
	
	ControlChangerMoteur controlChangerMoteur = new ControlChangerMoteur();
	BoundaryVisualiserParametreMoteur boundaryVisualiserParametreMoteur = new BoundaryVisualiserParametreMoteur();
	
	BaseMoteur baseMoteur = BaseMoteur.getInstance();
	
	Moteur moteur1 = baseMoteur.choixMoteur(1);
	Moteur moteur2 = baseMoteur.choixMoteur(2);
	
	public void choisirMoteur() {
		String choixMoteur = "0"; 
		Clavier clavier = new Clavier();
		
		while(!choixMoteur.equals("5")) {
			while((!choixMoteur.equals("1")) && (!choixMoteur.equals("2"))
					&& (!choixMoteur.equals("3")) && (!choixMoteur.equals("4"))) {
				System.out.println("(1) Moteur n°1 de type \n");
				System.out.println("(2) Moteur n°2 de type \n");
				System.out.println("(3) Afficher les paramètres des moteurs \n");
				System.out.println("(4) Annuler les Modifications en cours \n");
				choixMoteur = clavier.entrerClavierString();
				
				if ((!choixMoteur.equals("1")) && (!choixMoteur.equals("2"))
						&& (!choixMoteur.equals("3")) && (!choixMoteur.equals("4"))) {
					System.out.println("Choix Incorrect !!! \n");
				}
			}
			System.out.println("votre choix est : " + choixMoteur + "\n");
			
			switch(choixMoteur) {
			
			case "1" :
				controlChangerMoteur.setMoteurPrincipal(moteur1);
				System.out.println(" le nouveau moteur principal est : " + controlChangerMoteur.getMoteurPrincipal());
				choixMoteur = "5";
				break;
				
			case "2" :
				controlChangerMoteur.setMoteurPrincipal(moteur2);
				System.out.println(" le nouveau moteur principal est : " + controlChangerMoteur.getMoteurPrincipal());
				choixMoteur = "5";
				break;
			case "3" : 
				boundaryVisualiserParametreMoteur.afficherParametreMoteur(moteur1);
				boundaryVisualiserParametreMoteur.afficherParametreMoteur(moteur2);
				choixMoteur = "5";
				break;
			case "4" : 
				System.out.println("Annulation des modifications");
				choixMoteur = "5";
				break;
			case "5" : 
				System.out.println("Vous quittez le choix des moteurs principaux");
				break;
			default : System.out.println("Erreur dans le choix moteur : defaut du case BoundaryChoisirMoteur \n");
			}
		}
		
	}
	
	

}
