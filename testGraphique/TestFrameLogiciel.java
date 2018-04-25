package testGraphique;

import model.BaseMoteur;
import model.FactoryMoteur;
import model.Moteur;
import model.TypeMoteur;
import vuegraphique.FrameLogiciel;

public class TestFrameLogiciel {
	
	private static FactoryMoteur usineMoteur = new FactoryMoteur();
	private static BaseMoteur baseMoteur = BaseMoteur.getInstance();
	private static Moteur moteur1 = usineMoteur.creerMoteur(TypeMoteur.MOTEUR1, "Basse precision");
	private static Moteur moteur2 = usineMoteur.creerMoteur(TypeMoteur.MOTEUR2, "Moyenne precision");
	private static Moteur moteur3 = usineMoteur.creerMoteur(TypeMoteur.MOTEUR3, "Haute precision");
	
	
	public static void main(String[] args) {
		
		baseMoteur.ajouterMoteur(moteur1);
		baseMoteur.ajouterMoteur(moteur2);
		baseMoteur.ajouterMoteur(moteur3);
		
		moteur1.setMoteurActif();
		moteur2.setMoteurActif();
		moteur1.setMoteurPrincipal();
		new FrameLogiciel();
	}
}
