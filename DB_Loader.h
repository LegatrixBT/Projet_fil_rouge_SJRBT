//Developpeur: Ander Jimenez Garcia
//Date: 09/12/2017

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

#ifndef Indexeur_Image_H
#define Indexeur_Image_H

#include "Indexeur_Image.h"

#endif

#ifndef FONCTION_DESCRIPTEUR_TEXTE_H
	#define FONCTION_DESCRIPTEUR_TEXTE_H
	#include "fonctions_descripteur_texte.h"
#endif

//Partie Image

typedef struct cellule_img //Structure cellule
{
	type_desc_img elt; //Element de la pile
	struct cellule_img * suiv; //Pointeur vers la cellule suivante
}type_cel_img;

typedef type_cel_img * pile_img; //la pile pointe vers la tete de la pile (qui est une cellule)

void init_pile_img(pile_img * ptr_pile);
void empiler_img(pile_img * ptr_pile, type_desc_img d);
type_desc_img depiler_img(pile_img * ptr_pile);
int pile_est_vide_img(pile_img p);

void ecrire_db_img(pile_img p, int bits_quant);
pile_img lire_db_img();

//Partie texte

typedef struct cellule_texte //Structure cellule
{
	type_desc_texte elt; //Element de la pile
	struct cellule_texte * suiv; //Pointeur vers la cellule suivante
}type_cel_texte;

typedef type_cel_texte * pile_texte; //la pile pointe vers la tete de la pile (qui est une cellule)

void init_pile_texte(pile_texte * ptr_pile);
void empiler_texte(pile_texte * ptr_pile, type_desc_texte d);
type_desc_texte depiler_texte(pile_texte * ptr_pile);
int pile_est_vide_texte(pile_texte p);

void ecrire_db_texte(pile_texte p);
pile_texte lire_db_texte(void);