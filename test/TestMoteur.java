package test;

import model.Moteur;
import model.UsineMoteur;
import vueTextuelle.BoundaryChoisirMoteur;
import model.TypeMoteur;

public class TestMoteur {
	
	private static UsineMoteur usineMoteur = new UsineMoteur();
	private static Moteur moteur1 = usineMoteur.formerMoteur(TypeMoteur.MOTEUR1);
	private static Moteur moteur2 = usineMoteur.formerMoteur(TypeMoteur.MOTEUR2);
	
	
	private static BoundaryChoisirMoteur boundaryChoisirMoteur = new BoundaryChoisirMoteur();
	
	public static void main(String[] args) {
		
		TestMoteur.moteur1.setMoteurPrincipal();
		boundaryChoisirMoteur.choisirMoteur();
		
	
	}

	

}
