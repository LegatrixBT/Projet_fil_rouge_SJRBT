package Control;

import java.util.List;

import Model.Recherche;

public class ControlRecherche {

	private Recherche rec;
	
	public ControlRecherche()
	{
		rec = new Recherche();
	}
	
	public String rechercheSon(int choix)
	{
		return (rec.recSon(choix));
	}
	
	public String rechercheImage(int mode, int couleurRecherchee)
	{
		return(rec.recImage(mode, couleurRecherchee));
	}
	
	public String rechercheTexte(List<String> motCherches)
	{
		return(rec.recTexte(motCherches));
	}
}
