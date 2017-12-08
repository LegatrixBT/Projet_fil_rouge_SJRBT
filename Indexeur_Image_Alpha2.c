//Developpeur: Ander Jimenez Garcia
//Date: 05/12/2017

#include "Indexeur_Image_Alpha2.h"

int quantificateur(int val, int nb_matrice_composante)
{
	if(((val>=0)&&(val<256))&&(nb_matrice_composante>=0)&&(nb_matrice_composante<3)) //On verifie que val est entre 0 et 255, nb_matrice_composante entre 0 et 2
	{
		int cpt_puiss, tab_decalage[3]={2,4,6},tab_puiss_2[8] = {1,2,4,8,16,32,64,128}; //tableau permettant d'obtenir la puissance de 2 fonction de l'index
		unsigned int val_sortie=0, tab_puiss_hexa[8]={0x0001,0x0002,0x0004,0x0008,0x0010,0x0020,0x0040,0x0080}; //, tab_decalage permet d'obtenir le nombre de decalages a faire
																											   //en fonction de la composante actuelle (R,G ou B)
		for(cpt_puiss=7;cpt_puiss>5;cpt_puiss--)															   //tab_puiss_hexa permet d'avoir les puissances de 2 en hexadecimal
		{
			if(val/tab_puiss_2[cpt_puiss] == 1) //On decompose val en puissances de 2 (on ne regarde que les 2 puissances de poids le plus fort, car quantifie sur 2 bits)
			{
				val_sortie = val_sortie + tab_puiss_hexa[cpt_puiss];
				val= val - tab_puiss_2[cpt_puiss]; 
			}
		}
		val_sortie = val_sortie >> tab_decalage[nb_matrice_composante]; //Apres avoir la valeur quantifie, on la decale en fonction de tab_decalage afin que la valeur RGB soir correcte
		return((int) val_sortie);
	}
	else
	{
		fprintf(stderr,"Erreur Quantification pixel! Valeur ou nombre de composantes RGB eronnee!\n");
		return -1;
	}
}

type_desc_img histogramme(int ** matrice_RGB, int taille_lig, int taille_col, int val_max)
{
	int i,j,k,cpt;
	
	type_desc_img d;
	
	d.nb_ID = rand() % 500; //Attribution au hasard d'un ID
	for(k=0;k<val_max;k++)
	{
		cpt=0;
		for(i=0;i<taille_lig;i++)
		{
			for(j=0;j<taille_col;j++)
			{
				if(matrice_RGB[i][j]==k)
					cpt++;
			}
		}
		d.tab[k]=cpt;
	}
	return d;
}

type_desc_img indexeur_img(char chemin[])
{
	FILE * ptr_file;
	char commande[100];
	int taille_col, taille_lig, nb_composantes;
	
	strcpy(commande, "more ");           //On crée une commande qui permet de 
	strcat(commande, chemin);			//stocker le .txt de l'image dans un fichier temporaire "fic_temp"
	strcat(commande, " > fic_temp");
	system(commande);
	
	ptr_file = fopen("fic_temp", "r"); //Ouverture du fichier temporaire
	if(ptr_file != NULL) //Verif qu'on a bien ouvert le fichier
	{
		fscanf(ptr_file, "%d %d %d", &taille_lig, &taille_col, &nb_composantes);
		
		int nb_mat, cpt_col,cpt_lig,val_brute,i;
		int ** matrice_RGB = (int **)calloc(taille_lig,sizeof(int *));
		for(i=0;i<taille_col;i++)											//Allocation dynamique d'une matrice stockant la valeur RGB quantifiee
			matrice_RGB[i]=(int *)calloc(taille_col,sizeof(int));
		
		//---------------------------------------------Initialisation finie-------------------------------------------------------------
		
		if(nb_composantes == 1) //SI image en noir et blanc, pas de quantification a faire, 256 valeurs possibles
		{
			for(cpt_lig=0;cpt_lig<taille_lig;cpt_lig++)
			{
				for(cpt_col=0;cpt_col<taille_col;cpt_col++)
				{
					fscanf(ptr_file, "%d ", &val_brute); //Lecture de la valeur du pixel
					matrice_RGB[cpt_lig][cpt_col]= val_brute; //On stocke dans une matrice la valeur du pixel
				}
			}
			system("rm fic_temp"); //Suppresion du fichier temporel
		
			return(histogramme(matrice_RGB, taille_lig, taille_col, 255));
		}
		else //Si 3 composantes (RGB), quantification a faire, 64 valeurs car quantifie sur 2 bits
		{
			for(nb_mat=0;nb_mat<nb_composantes;nb_mat++) //Pour chacune des matrices composantes de l'image
			{			
				for(cpt_lig=0;cpt_lig<taille_lig;cpt_lig++)
				{
					for(cpt_col=0;cpt_col<taille_col;cpt_col++)
					{
						fscanf(ptr_file, "%d ", &val_brute); //Lecture de la valeur du pixel
						matrice_RGB[cpt_lig][cpt_col]= matrice_RGB[cpt_lig][cpt_col] + quantificateur(val_brute, nb_mat); //On somme les valeurs quantifies des valeurs R G et B du meme pixel
					}
				}
			}
			system("rm fic_temp"); //Suppresion du fichier temporel
		
			return(histogramme(matrice_RGB, taille_lig, taille_col, 64));
		}
		
	}
	else
		fprintf(stderr, "ERREUR! Ouverture du fichier temporaire echoué!");
}

void main()
{
	
}