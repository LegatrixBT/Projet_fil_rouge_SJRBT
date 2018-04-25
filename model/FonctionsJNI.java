package model;

public class FonctionsJNI {


	
	public native void indexationImage();
	public native void indexationTexte();
	public native void rechercheImage(String cheminImage);
	public native void rechercheTexteFichier(String cheminTexte);
	public native void rechercheTexteMotCle(String motCle);
	public native void ajouterImage(String cheminImage);
	public native void ajouterTexte(String cheminTexte);
	
	static {
		
		System.load(System.getProperty("user.dir") + "/FonctionsJNI.dll");
	}
	
	public static void main(String[] args) {
		FonctionsJNI f = new FonctionsJNI();
		f.rechercheTexteMotCle("affiche");
		
	}

}
