#include "pile_desc_img.h"

void init_pile(pile * ptr_pile)
{
	(*ptr_pile) = NULL;
}

int pile_est_vide(pile p)
{
	if(p == NULL)
		return 1;
	else
		return 0;//Si la tete ne pointe rien
}

void empiler(pile * ptr_pile, type_desc_img d)
{
	if(pile_est_vide(*ptr_pile)) //Si la pile est vide
	{
		*ptr_pile = (type_cel *) malloc(sizeof(type_cel)); //la pile pointe sur la nouvelle cellule
		(*(*ptr_pile)).elt = d;
		(*(*ptr_pile)).suiv = NULL;
	}
	else
	{
		type_cel * ptr_aux = *ptr_pile; //memorisation de l'ancienne tete
		*ptr_pile = (type_cel *) malloc(sizeof(type_cel)); //la pile pointe sur la nouvelle cellule
		(*(*ptr_pile)).elt = d;
		(*(*ptr_pile)).suiv = ptr_aux;
		
	}
}

type_desc_img depiler(pile * ptr_pile)
{
	type_cel aux;
	
	if(pile_est_vide(*ptr_pile))
	{
		printf("ERREUR dans depile! La pile est vide!\n");
	}
	else
	{
		if((*(*ptr_pile)).suiv == NULL) //Si il n'y a pas d'element suivant la tete de la pile (donc la pile n'a qu'un type_desc_img)
		{
			aux = *(*ptr_pile); //memorisation de la tete de pile
			*ptr_pile = NULL;
		}
		else
		{
			aux = *(*ptr_pile); //memorisation de la tete de pile
			*ptr_pile = (*(*ptr_pile)).suiv; //La cellule suivante de l'ancienne tete deviens la nouvelle tete de pile
		}
	}
	return aux.elt;
}