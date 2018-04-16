package model;

public class EntreeRecherche implements Comparable<EntreeRecherche> {

	private String cheminFichier;
	private Float distance;
	
	public EntreeRecherche(String cheminFichier, Float distance) {
		this.cheminFichier = cheminFichier;
		this.distance = distance;
	}
	
	@Override
	public int compareTo(EntreeRecherche o) {
		return this.distance.compareTo(o.distance);
	}
	
	public String getCheminFichier() {
		return cheminFichier;
	}
	
	public Float getDistance() {
		return distance;
	}
	
}
