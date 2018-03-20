package test;

import control.ControlAccesMenuAdmin;
import vueTextuelle.BoundaryAccesMenuAdmin;
import vueTextuelle.BoundaryAfficherMenuAdministrateur;

public class TestAccesMenuAdmin {
	
	private static ControlAccesMenuAdmin c1 = new ControlAccesMenuAdmin();
	private static BoundaryAfficherMenuAdministrateur b2 = new BoundaryAfficherMenuAdministrateur();
	private static BoundaryAccesMenuAdmin b1 = new BoundaryAccesMenuAdmin(c1, b2);
	
	public static void main(String[] args) {
		b1.connexionAdministrateur();
	}

}