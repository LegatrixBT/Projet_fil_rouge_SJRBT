package model;

import java.util.Observable;

public class Profil extends Observable{
	
	String mdp;
	boolean isAdmin = false;
	
	
	//methodes singleton
	
	private Profil() {
		this.mdp = "0000";
	}

	private static class ProfilHolder{
		private static final Profil instance = new Profil();
	}
	
	public static Profil getInstance() {
		return ProfilHolder.instance;
	}
	
	//Methodes classe
	
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
		this.setChanged();
		this.isAdmin = true;
		this.notifyObservers(this.isAdmin);
	}
	
	public void deconnexionAdmin() {
		this.setChanged();
		this.isAdmin = false;
		this.notifyObservers(this.isAdmin);
	}
	
}
