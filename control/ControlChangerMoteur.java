package control;

import model.BaseMoteur;
import model.Moteur;

public class ControlChangerMoteur {
	
	private Moteur moteurPrincipal = BaseMoteur.getInstance().getMoteurPrincipal();
	
	public void setMoteurPrincipal(Moteur nouveauMoteurPrincipal) {
		
		System.out.println("le moteur Principal est :" + moteurPrincipal);
		System.out.println("\n Le futur nouveau moteur est : "+ nouveauMoteurPrincipal);
		moteurPrincipal.unsetMoteurPrincipal();
		nouveauMoteurPrincipal.setMoteurPrincipal();
		moteurPrincipal = nouveauMoteurPrincipal;
		moteurPrincipal.setMoteurActif();
	}
	
	public Moteur getMoteurPrincipal() {
		
		return this.moteurPrincipal;
	}

}
