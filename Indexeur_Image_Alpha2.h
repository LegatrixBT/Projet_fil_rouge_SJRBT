//Developpeur: Ander Jimenez Garcia
//Date: 09/12/2017

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

typedef struct descp_img
{
	int nb_ID;
	int tab[256];
}type_desc_img;

int quantificateur(int val, int nb_matrice_composante);
type_desc_img histogramme(int ** matrice_RGB, int taille_lig, int taille_col, int val_max);
type_desc_img indexeur_img(char chemin[]);