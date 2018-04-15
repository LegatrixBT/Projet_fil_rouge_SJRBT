package testGraphique;

import model.Moteur;
import model.TypeMoteur;
import model.UsineMoteur;
import vueTextuelle.BoundaryChoisirMoteur;
import vuegraphique.FrameLogiciel;
import vuegraphique.PanelOngletRechercheAvancee;


public class TestFrameLogiciel {
	
	private static UsineMoteur usineMoteur = new UsineMoteur();
	private static Moteur moteur1 = usineMoteur.formerMoteur(TypeMoteur.MOTEUR1);
	private static Moteur moteur2 = usineMoteur.formerMoteur(TypeMoteur.MOTEUR2);
	
	private static BoundaryChoisirMoteur boundaryChoisirMoteur = new BoundaryChoisirMoteur();
	
	public static void main(String[] args) {

		moteur1.setMoteurActif();
		moteur2.setMoteurActif();
		moteur1.setMoteurPrincipal();
		new FrameLogiciel();
		while(true)
			boundaryChoisirMoteur.choisirMoteur();
	}
}
