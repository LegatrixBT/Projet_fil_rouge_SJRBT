//Developpeur: Ander Jimenez Garcia
//Date: 05/12/2017

int nb_quant = 2;
int tab_puiss_2[8] = {1,2,4,8,16,32,64,128};
int matrice[500][500];

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

typedef struct descp_img
{
	int nb_ID;
	int tab[];
}type_desc_img;

type_desc_img desc;

void lecture_taille_img(FILE * ptr_fichier, int * longeur, int * largeur, int * nb_composantes) //permet de lire la taille des matrices
{
	fscanf(ptr_file, "%d %d %d", &longeur, &largeur, &nb_composantes); //Lecture premiere ligne qui contient la longeur, la largeur et le nombre de matrices
}

int histogramme(int ligne, int colonne, int val_max)
{
	int i,j,k,cpt;
	desc.nb_ID = 1;
	for(k=0;k<256;k++)
	{
		cpt=0;
		for(i=0;i<ligne;i++)
		{
			for(j=0;j<colonne;j++)
			{
				if(matrice[i][j]==k)
					cpt++;
			}
		}
		desc.tab[k]=cpt;
	}
	return 1;
}

unsigned int quantificateur(int nb_mat, int val)
{
	int cpt_puiss, tab_decalage[3]={2,4,6};
	unsigned int val_sortie=0, tab_puiss_hexa[8]={0x0001,0x0002,0x0004,0x0008,0x0010,0x0020,0x0040,0x0080};
	
	for(cpt_puiss=7;cpt_puiss>5;cpt_puiss--)
	{
		if(val/tab_puiss_2[cpt_puiss] == 1)
		{
			val_sortie = val_sortie + tab_puiss_hexa[cpt_puiss];
			val= val - tab_puiss_2[cpt_puiss]; 
		}
	}
	val_sortie = val_sortie >> tab_decalage[nb_mat];
	return( val_sortie);
}

//type_desc_img index_img(char chemin[])
void gen_matrice_RGB(char chemin[])
{
	int nb_lig, nb_col,nb_mat;
	
	FILE * ptr_file;
	char commande[100];
	//char chemin[100] = "Images/TEST_RGB/04.txt";
	
	strcpy(commande, "more ");           //On crée une commande qui permet de 
	strcat(commande, chemin);			//stocker le .txt de l'image dans un fichier temporaire "fic_temp"
	strcat(commande, " > fic_temp");
	system(commande);
	
	ptr_file = fopen("fic_temp", "r");
	if(ptr_file != NULL)						//Verif que le fichier est bien ouvert
	{
		int cpt_col,cpt_lig;
		
		int val_brute; //On cree une matrice dans laquelle on stockera peu à peu les valeurs quantifiées
		
		for(cpt_lig=0;cpt_lig<nb_lig;cpt_lig++) //Traitement pour une matrice
		{
			for(cpt_col=0;cpt_col<nb_col;cpt_col++)
			{
				fscanf(ptr_file, "%d ", &val_brute); //Traitement d'une seule valeur
				matrice[cpt_lig][cpt_col]=(int) quantificateur(0,val_brute);
			}
		}
		
		for(cpt_lig=0;cpt_lig<nb_lig;cpt_lig++) //Traitement pour une matrice
		{
			for(cpt_col=0;cpt_col<nb_col;cpt_col++)
			{
				fscanf(ptr_file, "%d ", &val_brute); //Traitement d'une seule valeur
				matrice[cpt_lig][cpt_col]=matrice[cpt_lig][cpt_col] + (int) quantificateur(1,val_brute);
			}
		}
		
		for(cpt_lig=0;cpt_lig<nb_lig;cpt_lig++) //Traitement pour une matrice
		{
			for(cpt_col=0;cpt_col<nb_col;cpt_col++)
			{
				fscanf(ptr_file, "%d ", &val_brute); //Traitement d'une seule valeur
				matrice[cpt_lig][cpt_col]= matrice[cpt_lig][cpt_col] + (int) quantificateur(2,val_brute);
			}
		}
	}
	else
	{
		fprintf(stderr, "ERREUR! Ouverture du fichier temporaire echoué!");
	}
}

void main()
{
	printf("resultat avec 174 (matrice R)= %u\n", quantificateur(0, 174));
	printf("resultat avec 57 (matrice G)= %u\n", quantificateur(1, 57));
	printf("resultat avec 203 (matrice B)= %u\n", quantificateur(2, 203));
	printf("Valeur RGB avec R=174 G=57 B=203 est : %u\n", (quantificateur(0, 174) + quantificateur(1, 57) + quantificateur(2, 203)));
	gen_matrice_RGB("Images/TEST_RGB/22.txt");
	histogramme(200,200,64);
	int i;
	for(i=0;i<64;i++)
		printf("%d ", desc.tab[i]);
	printf("\n");
}