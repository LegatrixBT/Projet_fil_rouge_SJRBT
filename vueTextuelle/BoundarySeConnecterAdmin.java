package vueTextuelle;

import control.ControlSeConnecter;

public class BoundarySeConnecterAdmin {
	
	private ControlSeConnecter controlSeConnecter;
	
	public BoundarySeConnecterAdmin() {
		controlSeConnecter = new ControlSeConnecter();
	}
	
	public boolean seConnecter() {
		Clavier clavier = new Clavier();
		String mdp;
		
		System.out.println("Saissisez le mot de passe:\n");
		mdp = clavier.entrerClavierString();
		return controlSeConnecter.seConnecter(mdp);
	}
	
	public void seDeconnecter() {
		controlSeConnecter.seDeconnecter();
	}
	
}
