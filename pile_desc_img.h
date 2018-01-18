//Developpeur: Ander Jimenez Garcia
//Date: 09/12/2017

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include "Indexeur_Image.h"

typedef struct cellule //Structure cellule
{
	type_desc_img elt; //Element de la pile
	struct cellule * suiv; //Pointeur vers la cellule suivante
}type_cel;

typedef type_cel * pile; //la pile pointe vers la tete de la pile (qui est une cellule)

void init_pile(pile * ptr_pile);
int pile_est_vide(pile p);
void empiler(pile * ptr_pile, type_desc_img d);
type_desc_img depiler(pile * ptr_pile);