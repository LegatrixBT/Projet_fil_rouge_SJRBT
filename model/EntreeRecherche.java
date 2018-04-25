package model;

public class EntreeRecherche implements Comparable<EntreeRecherche> {

	private String cheminFichier;
	private Float distance;
	
	public EntreeRecherche(String cheminFichier, Float distance) {
		this.cheminFichier = cheminFichier;
		this.distance = distance;
	}
	
	public int hashCode() {
		return cheminFichier.hashCode();
	}
	
	public String getCheminFichier() {
		return cheminFichier;
	}
	
	public Float getDistance() {
		return distance;
	}
	
	public String toString() {
		return cheminFichier + " - " + distance.toString() + "%";
	}

	@Override
	public int compareTo(EntreeRecherche o) {
		return this.cheminFichier.compareTo(o.cheminFichier);
}
	
}
