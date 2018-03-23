package model;

public class Profil {
	
	String mdp;
	boolean isAdmin = false;
	
	
	//méthodes singleton
	
	private Profil() {
		this.mdp = "0000";
	}

	private static class ProfilHolder{
		private static final Profil instance = new Profil();
	}
	
	public static Profil getInstance() {
		return ProfilHolder.instance;
	}
	
	//Méthodes classe
	
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}
	public void connexionAdmin() {
		this.isAdmin = true;
	}
	
	public void deconnexionAdmin() {
		this.isAdmin = false;
	}
	
}
