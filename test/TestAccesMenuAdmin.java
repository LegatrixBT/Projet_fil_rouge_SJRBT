package test;

import vueTextuelle.BoundaryMenuAdmin;
import vueTextuelle.Clavier;

public class TestAccesMenuAdmin {
	
	private static BoundaryMenuAdmin boundaryMenuAdmin = new BoundaryMenuAdmin();
	
	public static void main(String[] args) {
		boolean condition = false;
		Clavier clavier = new Clavier();
		int choix;
		
		do {
			boundaryMenuAdmin.menuAdmin();
			choix = clavier.entrerClavierInt();
			if(choix == 1)
				condition = true;
		} while(!condition);
	}

}