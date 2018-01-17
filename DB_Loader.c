//Developpeur: Ander Jimenez Garcia
//Date: 26/12/2017

#include "DB_Loader.h"

/*************************************************Fonctions de gestion de pile img************************************************************/

void init_pile_img(pile_img * ptr_pile)
{
	(*ptr_pile) = NULL;
}

int pile_est_vide_img(pile_img p)
{
	if(p == NULL)
		return 1;
	else
		return 0;//Si la tete ne pointe rien
}

void empiler_img(pile_img * ptr_pile, type_desc_img d)
{
	if(pile_est_vide_img(*ptr_pile)) //Si la pile est vide
	{
		*ptr_pile = (type_cel_img *) malloc(sizeof(type_cel_img)); //la pile pointe sur la nouvelle cellule
		(*(*ptr_pile)).elt = d;
		(*(*ptr_pile)).suiv = NULL;
	}
	else
	{
		type_cel_img * ptr_aux = *ptr_pile; //memorisation de l'ancienne tete
		*ptr_pile = (type_cel_img *) malloc(sizeof(type_cel_img)); //la pile pointe sur la nouvelle cellule
		(*(*ptr_pile)).elt = d;
		(*(*ptr_pile)).suiv = ptr_aux;
		
	}
}

type_desc_img depiler_img(pile_img * ptr_pile)
{
	type_cel_img aux;
	
	if(pile_est_vide_img(*ptr_pile))
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

/*************************************************Fonctions de gestion de pile texte************************************************************/

void init_pile_texte(pile_texte * ptr_pile)
{
	(*ptr_pile) = NULL;
}

int pile_est_vide_texte(pile_texte p)
{
	if(p == NULL)
		return 1;
	else
		return 0;
}

void empiler_texte(pile_texte * ptr_pile, type_desc_texte d)
{
	if(pile_est_vide_texte(*ptr_pile)) //Si la pile est vide
	{
		*ptr_pile = (type_cel_texte *) malloc(sizeof(type_cel_texte)); //la pile pointe sur la nouvelle cellule
		(*(*ptr_pile)).elt = d;
		(*(*ptr_pile)).suiv = NULL;
	}
	else
	{
		type_cel_texte * ptr_aux = *ptr_pile; //memorisation de l'ancienne tete
		*ptr_pile = (type_cel_texte *) malloc(sizeof(type_cel_texte)); //la pile pointe sur la nouvelle cellule
		(*(*ptr_pile)).elt = d;
		(*(*ptr_pile)).suiv = ptr_aux;
		
	}
}

type_desc_texte depiler_texte(pile_texte * ptr_pile)
{
	type_cel_texte aux;
	
	if(pile_est_vide_texte(*ptr_pile))
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

/*****************************************************Fonctions gestion DB Image****************************************/

void ecrire_db_img(pile_img p)
{
	if(pile_est_vide_img(p) == 0)
	{
		type_desc_img desc_aux;
		int tab_valmax[5]={8,64,511,4096,32768}, i,index_tab_max;
		FILE * ptr_fic;
		ptr_fic = fopen("base_descripteur_image","w");
		while(pile_est_vide_img(p) == 0)
		{
			desc_aux = depiler_img(&p);
			fprintf(ptr_fic,"ID=%s nb_comp=%d\n", desc_aux.nb_ID, desc_aux.nb_comp);
			for(i=0;i<tab_valmax[bits_quant-1];i++)
			{
				fprintf(ptr_fic,"%d %d\n", i, desc_aux.tab[i]);
			}
			fprintf(ptr_fic,"%d %d\n", -1, -1);
		}
		fclose(ptr_fic);
	}
	else
		fprintf(stderr, "ERREUR! pile_img de descripteurs image vide!\n");
}

pile_img lire_db_img(void)
{
	FILE * ptr_fic = fopen("base_descripteur_image","r");
	if(ptr_fic != NULL)
	{
		pile_img p_aux;
		init_pile_img(&p_aux);
		type_desc_img desc_aux;
		int index_tab, val_tab;
		do
		{
			fscanf(ptr_fic,"ID=%s nb_comp=%d\n", desc_aux.nb_ID, &(desc_aux.nb_comp));
			do
			{
				fscanf(ptr_fic,"%d %d\n",&index_tab,&val_tab);
				if((index_tab != -1))
					desc_aux.tab[index_tab] = val_tab;
			}while(index_tab != -1);
			empiler_img(&p_aux,desc_aux);
		}while(!feof(ptr_fic));
		fclose(ptr_fic);
		return(p_aux);
	}
	else
	{
		fprintf(stderr,"ERREUR dans lecture db image! base de descripteurs introuvable!");
	}
	
}

/*****************************************************Fonctions gestion DB Texte****************************************/

void ecrire_db_texte(pile_texte p)
{
	if(pile_est_vide_texte(p) == 0)
	{
		type_desc_texte desc_aux;
		int tab_valmax[5]={8,64,511,4096,32768},i,index_tab_max;
		FILE * ptr_fic;
		ptr_fic = fopen("base_descripteur_texte","w");
		while(pile_est_vide_texte(p) == 0)
		{
			desc_aux = depiler_texte(&p);
			fprintf(ptr_fic,"ID=%s nbr_mot_texte=%d\n", desc_aux.nb_ID, desc_aux.nbr_mot_texte);
			for(i=0;i<10;i++)
			{
				fprintf(ptr_fic,"%s %d\n",desc_aux.mot[i], desc_aux.occurence[i]);
			}
			fprintf(ptr_fic,"%s %d\n", "-1", -1);
		}
		fclose(ptr_fic);
	}
	else
		fprintf(stderr, "ERREUR! pile_img de descripteurs image vide!\n");
}

pile_texte lire_db_texte(void)
{
	FILE * ptr_fic = fopen("base_descripteur_texte","r");
	if(ptr_fic != NULL)
	{
		pile_texte p_aux;
		init_pile_texte(&p_aux);
		type_desc_texte desc_aux,d1;
		int i, val_tab;
		char mot[50];
		do
		{
			i=0;
			fscanf(ptr_fic,"ID=%s nbr_mot_texte=%d\n", desc_aux.nb_ID, &(desc_aux.nbr_mot_texte));
			do
			{
				fscanf(ptr_fic,"%s %d\n",mot,&val_tab);
				if(val_tab != -1)
				{
					strcpy(desc_aux.mot[i],mot);
					desc_aux.occurence[i]=val_tab;
					i++;
				}
			}while(val_tab != -1);
			empiler_texte(&p_aux,desc_aux);
		}while(!feof(ptr_fic));
		fclose(ptr_fic);
		return(p_aux);
	}
	else
	{
		fprintf(stderr,"ERREUR dans lecture db texte! base de descripteurs introuvable!");
	}
	
}
