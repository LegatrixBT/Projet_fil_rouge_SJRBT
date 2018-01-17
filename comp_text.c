#include "comp_text.h"

float compare_Text_fichier(type_desc_texte desc1, type_desc_texte desc2)
{
	/* Cette fonction compare les deux descripteurs en entree et retourne leur distance en pourcentage
	100% = Fichiers descripteurs 
	0%   = Fichiers completements differents */
	//////////////////////////////////////////////////////////////////////////////////
	int i,j,trouve = 0 ; // i et j sont des cpt pour des boucles for(),
	//trouve est une variable qui permet de savoir si un mot du premier descripteur est present dans le second
	float tab_dist[10]; //tableau qui comprend la valeur de la distance au meme indice dans le tableau mot du descripteur
	float dist1mot, distTotalMot,distance,dist_ret = 0;
	/*dist1mot : rapport entre l'occurence d'un meme mot dans les deux textes
	  distTotalMot est le rapport entre le total de mots de chaque texte
	  distance est la somme des deux rapports precedents, divise par 2 (pour retomber entre 0 et 1 comme valeur)
	  dist_ret valeur sommee des dist de chaque mot, multiplie par 10 pour avoir un pourcentage*/
	
	
	
	for(i = 0; i < 10; i++) //je regarde dans desc1
	{
		for(j = 0; (j < 10) && (trouve == 0); j++)//je cherche si il y a correspondance avec le mot de desc1 dans desc2
		{
			if(strcmp(desc1.mot[i],desc2.mot[j])==0)
			{
				//on regarde par la suite si la taille du texte 1 est plus grande que celle du texte2
				if(desc1.nbr_mot_texte <= desc2.nbr_mot_texte)
				{
					//de meme avec les mots, pour savoir lequel apparait le plus grand nombre de fois
					if(desc1.occurence[i] <= desc2.occurence[j])
					{
						dist1mot = desc1.occurence[i] / (float)desc2.occurence[j];
						distance = dist1mot + (desc1.nbr_mot_texte / (float)desc2.nbr_mot_texte) ;
						tab_dist[i] = distance/2;
						trouve = 1;
					}
					else
					{
						dist1mot = desc2.occurence[j] / (float)desc1.occurence[i];
						distance = dist1mot + (desc1.nbr_mot_texte / (float)desc2.nbr_mot_texte) ;
						tab_dist[i] = distance/2;
						trouve = 1;
					}	
				}
				else
				{
					if(desc1.occurence[i] < desc2.occurence[j])
					{
						dist1mot = desc1.occurence[i] / (float)desc2.occurence[j];
						distance = dist1mot + (desc2.nbr_mot_texte / (float)desc1.nbr_mot_texte) ;
						tab_dist[i] = distance/2;
						trouve = 1;
					}
					else
					{
						dist1mot = desc2.occurence[j] / (float)desc1.occurence[i];
						distance = dist1mot + (desc2.nbr_mot_texte / (float)desc1.nbr_mot_texte) ;
						tab_dist[i] = distance/2;
						trouve = 1;
					}
				}
			}
		}
		if(trouve == 0) //mot n'est pas dans commun aux deux descripteurs, donc il n'y a pas de ressemblance
			tab_dist[i] = 0 ;
		trouve = 0;
	}
	for(i = 0; i < 10; i++)
	{
		dist_ret += tab_dist[i];
		//printf("distance mot %s :  %f\n",desc1.mot[i],tab_dist[i]);
	}
	return(dist_ret*10);
}

float compare_Texmot(char mot[], type_desc_texte desc)
{
	int i, trouve = 0 ;
	float distance;
	
	for(i = 0; (i < 10)&&(trouve==0); i++)
	{
		if(strcmp(mot,desc.mot[i])==0)
		{			
			distance = desc.occurence[i] / (float)desc.nbr_mot_texte;
			trouve = 1;
		}
	}
	return(distance*100);
}