//Developpeur: Ander Jimenez Garcia
//Date: 09/12/2017

#include "Indexeur_Image_Alpha3.h"
int bits_quant = 3;

/*
Cette fonction retourne la valeur quantifiée en fonction de la valeur brute en entrée, de la composante de couleur (R, G ou B)
et du nombre de bits de quantification (variable globale dans la bibliotheque de l'analyseur .config.
*/

int quantificateur(int val, int nb_matrice_composante) 
{
	if(((val>=0)&&(val<256))&&(nb_matrice_composante>=0)&&(nb_matrice_composante<3)) //On verifie que val est entre 0 et 255, nb_matrice_composante entre 0 et 2
	{
		int cpt_puiss, tab_decalage[5][3]={{5,6,7},{2,4,6},{-1,2,5},{-4,0,4},{-7,-2,3}},tab_puiss_2[8] = {1,2,4,8,16,32,64,128}; //tableau permettant d'obtenir la puissance de 2 fonction de l'index
		unsigned int val_sortie=0, tab_puiss_hexa[8]={0x0001,0x0002,0x0004,0x0008,0x0010,0x0020,0x0040,0x0080}; //, tab_decalage permet d'obtenir le nombre de decalages a faire
																											   //en fonction de la composante actuelle (R,G ou B) et du nombre de bits de quantification
		for(cpt_puiss=7;cpt_puiss>7-bits_quant;cpt_puiss--)															   //tab_puiss_hexa permet d'avoir les puissances de 2 en hexadecimal
		{
			if(val/tab_puiss_2[cpt_puiss] == 1) //On decompose val en puissances de 2 (on ne regarde que les 2 puissances de poids le plus fort, car quantifie sur 2 bits)
			{
				val_sortie = val_sortie + tab_puiss_hexa[cpt_puiss];
				val= val - tab_puiss_2[cpt_puiss]; 
			}
		}
		if(tab_decalage[bits_quant-1][nb_matrice_composante] >= 0)
			val_sortie = val_sortie >> tab_decalage[bits_quant-1][nb_matrice_composante]; //Apres avoir la valeur quantifie, on la decale en fonction de tab_decalage afin que la valeur RGB soir correcte
		else
		{
			val_sortie = val_sortie << abs(tab_decalage[bits_quant-1][nb_matrice_composante]);
		}
		return((int) val_sortie);
	}
	else
	{
		fprintf(stderr,"Erreur Quantification pixel! Valeur ou nombre de composantes RGB eronnee!\n");
		return -1;
	}
}

/*
Cette fonction réalise l'histogramme de la matrice RGB quantifiée préalablement,
taille_lig, taille_col représentent les tailles de matrice_RGB, val_max la valeur maximale dans cette matrice
chemin est le chemin du fichier qu'on indexer
Cette fonction renvoie le descripteur du fichier, avec son ID et son tableau d'occurences (l'histogramme)
*/

type_desc_img histogramme(int ** matrice_RGB, int taille_lig, int taille_col, int val_max, char chemin[])
{
	int i,j,k,cpt;
	
	type_desc_img d; //Structure du descripteur qui sera renvoyé
	
	FILE * resultat_ls;
	char commande[50] = "ls -i ";
	
	strcat(commande, chemin);
	resultat_ls = popen(commande, "r"); //on recupere l'inode du fichier, ca sera le ID du descripteur
	fscanf(resultat_ls, "%s ", d.nb_ID);
	pclose(resultat_ls);
	
	for(k=0;k<val_max;k++) //on fait l'histogramme
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

/*
Cette fonction retourne le descripteur du fichier dans chemin.
Le descripteur est fonction du nombre de bits de quantifications saisi dans le fichier .config
*/

type_desc_img indexeur_img(char chemin[])
{
	FILE * ptr_file;

	int tab_valmax[5]={8,64,511,4096,32768};
	int taille_col, taille_lig, nb_composantes;
	
	ptr_file = fopen(chemin, "r"); //Ouverture du fichier temporaire
	if(ptr_file != NULL) //Verif qu'on a bien ouvert le fichier
	{
		fscanf(ptr_file, "%d %d %d", &taille_lig, &taille_col, &nb_composantes);
		int nb_mat, cpt_col,cpt_lig,val_brute,i;
		int ** matrice_RGB = (int **)calloc(taille_lig,sizeof(int *));
		for(i=0;i<taille_col;i++)											//Allocation dynamique d'une matrice stockant la valeur RGB quantifiee
			matrice_RGB[i]=(int *)calloc(taille_col,sizeof(int));
		
		//---------------------------------------------Initialisation finie-------------------------------------------------------------
		
		for(nb_mat=0;nb_mat<nb_composantes;nb_mat++) //Pour chacune des matrices composantes de l'image
		{			
			for(cpt_lig=0;cpt_lig<taille_lig;cpt_lig++)
			{
				for(cpt_col=0;cpt_col<taille_col;cpt_col++)
				{
					fscanf(ptr_file, "%d ", &val_brute); //Lecture de la valeur du pixel
					if(nb_composantes == 1)
						matrice_RGB[cpt_lig][cpt_col]= val_brute; //On stocke dans une matrice la valeur du pixel
					else
						matrice_RGB[cpt_lig][cpt_col]= matrice_RGB[cpt_lig][cpt_col] + quantificateur(val_brute, nb_mat); //On somme les valeurs quantifies des valeurs R G et B du meme pixel
				}
			}
		}
		
		if(nb_composantes == 1)
			return(histogramme(matrice_RGB, taille_lig, taille_col, 256, chemin));
		else
			return(histogramme(matrice_RGB, taille_lig, taille_col, tab_valmax[bits_quant-1],chemin));
	}
	else
	{
		fprintf(stderr, "ERREUR! Ouverture du fichier temporaire echoué!");
	}
}

void main()
{
	type_desc_img d;
	int i;
	char chemin[100];
	int tab_valmax[5]={8,64,512,4096,32768};
	printf("Saissisez le chemin du fichier .txt de l'image à indexer: \n");
	scanf("%s", chemin);
	d = indexeur_img(chemin);
	printf("Nb ID = %s\n", d.nb_ID);
	for(i=0;i<tab_valmax[bits_quant-1];i++)
	{
		printf("Valeur %d : %d\n", i, d.tab[i]);
	}
}