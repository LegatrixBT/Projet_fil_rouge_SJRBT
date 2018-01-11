//Developpeur: Ander Jimenez Garcia
//Date: 26/12/2017

#include "DB_Loader.h"

/*************************************************Fonctions de gestion de pile img************************************************************/

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

/*****************************************************Fonctions gestion DB****************************************/

void ecrire_db_img(pile p)
{
	if(pile_est_vide(p) == 0)
	{
		type_desc_img desc_aux;
		int tab_valmax[5]={8,64,511,4096,32768}, i,index_tab_max;
		FILE * ptr_fic;
		ptr_fic = fopen("base_descripteur_image","w");
		while(pile_est_vide(p) == 0)
		{
			desc_aux = depiler(&p);
			fprintf(ptr_fic,"ID=%s nb_comp=%d\n", desc_aux.nb_ID, desc_aux.nb_comp);
			for(i=0;i<tab_valmax[bits_quant-1];i++)
			{
				if(desc_aux.tab[i] != 0)
					fprintf(ptr_fic,"%d %d\n", i, desc_aux.tab[i]);
			}
			fprintf(ptr_fic,"%d %d\n", -1, -1);
		}
		fclose(ptr_fic);
	}
	else
		fprintf(stderr, "ERREUR! Pile de descripteurs image vide!\n");
}

pile lire_db_img(void)
{
	FILE * ptr_fic = fopen("base_descripteur_image","r");
	if(ptr_fic != NULL)
	{
		pile p_aux;
		init_pile(&p_aux);
		type_desc_img desc_aux,d1;
		int index_tab, val_tab;
		do
		{
			fscanf(ptr_fic,"ID=%s nb_comp=%d\n", desc_aux.nb_ID, &(desc_aux.nb_comp));
			do
			{
				fscanf(ptr_fic,"%d %d\n",&index_tab,&val_tab);
				if((index_tab != -1)&&(index_tab != -50))
					desc_aux.tab[index_tab] = val_tab;
			}while(index_tab != -1);
			empiler(&p_aux,desc_aux);
		}while(!feof(ptr_fic));
		fclose(ptr_fic);
		return(p_aux);
	}
	else
	{
		fprintf(stderr,"ERREUR dans lecture db image! base de descripteurs introuvable!");
	}
	
}