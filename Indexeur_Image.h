//Developpeur: Ander Jimenez Garcia
//Date: 09/12/2017

typedef struct descp_img
{
	char nb_ID[20];
	int tab[32768];
	int nb_comp;
}type_desc_img;

int quantificateur(int val, int nb_matrice_composante, int bits_quant);
type_desc_img histogramme(int ** matrice_RGB, int taille_lig, int taille_col, int val_max, char chemin[]);
type_desc_img indexeur_img(char chemin[],int bits_quant);