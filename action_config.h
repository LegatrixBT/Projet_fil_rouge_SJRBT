#include <stdlib.h>
#include <stdio.h>
#include <string.h>


typedef struct config
{
	//image
	int nb_bit_quanti;
	
	//texte
	int taille_mot;
	int taille_desc;
	
	//comparateur
	float seuil_comp_im;
	float seuil_comp_text;
	
}type_config;