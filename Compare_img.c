//Developpeur: Ander Jimenez Garcia
//Date: 10/01/2018
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include "Compare_img.h"

float compare_img(type_desc_img d1, type_desc_img d2)
{
	
	if(d1.nb_comp != d2.nb_comp)
	{
		fprintf(stderr, "ERREUR dans la comparaison d'images: nombre de composantes différentes.\n");
		return(-1);
	}
	else
	{
		int i=0, tab_valmax[5]={8,64,511,4096,32768};
		float distance = 0, val_max=0;
		for(i=0;i<tab_valmax[bits_quant-1];i++)
		{
			distance = distance + abs((d1.tab[i] - d2.tab[i]));
			val_max= val_max + d1.tab[i] + d2.tab[i];
		}
		distance = (distance * 100)/val_max;
		return(100 - distance);
	}
}