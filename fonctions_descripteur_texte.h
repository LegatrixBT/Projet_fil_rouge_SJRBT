
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>


#define TAILLE_DESC_T 10 
#define TAILLE_MAX_MOT 50


typedef struct descp_texte // structure correspondant au descripteur de texte 
{
	int nbr_mot_texte;
	char mot[10][50];
	int occurence[10];
	char nb_ID[20];
	
}type_desc_texte;

void tri_decroissant_desc_t (int tab[], char tabc[][TAILLE_MAX_MOT], int taille);
void affiche_descripteur (type_desc_texte d);
type_desc_texte indexeur_textes (char chemin[]);



