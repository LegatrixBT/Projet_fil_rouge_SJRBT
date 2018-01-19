#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

#include "DB_Loader.h"
#include "Compare_img.h"

#include "comp_text.h"

#ifndef Indexeur_Image_H
#define Indexeur_Image_H

#include "Indexeur_Image.h"

#endif

#ifndef FONCTION_DESCRIPTEUR_TEXTE_H
	#define FONCTION_DESCRIPTEUR_TEXTE_H
	#include "fonctions_descripteur_texte.h"
#endif

int bits_quant = 2;

int affichage_menu()
{
	int choix;
	printf("\n/********************Moteur de recherche texte/image********************/\n");
	printf("Que voulez-vous rechercher?\n");
	printf("	(1) Image\n");
	printf("	(2) Texte\n");
	printf("Saissisez l'option desirée :");
	scanf("%d",&choix);
	if(choix == 2)
	{
		printf("\nQue voulez-vous rechercher dans un texte?\n");
		printf("	(1) Fichier texte\n");
		printf("	(2) mot-clé\n");
		printf("Saissisez l'option desirée : ");
		scanf("%d",&choix);
		if(choix == 1)
			choix = 2;
		else{
			if(choix == 2)
				choix = 3;
			else
				choix = 0;
		}
	}
	else
	{
		if(choix != 1)
			choix = 0;
	}
	return(choix);
}

int affichage_fin(){
	int choix;
	printf("Fin de la recherche, voulez-vous relancer une recherche?\n");
	printf("	(1) Oui\n");
	printf("	(2) Non\n");
	printf("Saissisez l'option desirée :");
	scanf("%d",&choix);
	
	
	return(choix);
}

int getChemin_img(char ID_a_trouver[],char chemin_trouve[]){
	
	FILE * ptr_rech_ID;
	char commande[50] = "more liste_base_image | grep '";
	strcat(commande,ID_a_trouver);
	strcat(commande,"'");
	
	ptr_rech_ID=popen(commande,"r");
	fscanf(ptr_rech_ID,"%*s %s\n", chemin_trouve);
	fclose(ptr_rech_ID);
	
	if(strcmp(ID_a_trouver,"")==0)
		return 0;
	else
	{
		int i,longeur = strlen(chemin_trouve);
		for(i = longeur - 1;i > longeur - 5;i--)
			chemin_trouve[i]=0;
		return 1;
	}
}

int getChemin_texte(char ID_a_trouver[],char chemin_trouve[]){
	
	FILE * ptr_rech_ID;
	char commande[50] = "more liste_base_texte | grep '";
	strcat(commande,ID_a_trouver);
	strcat(commande,"'");
	
	ptr_rech_ID=popen(commande,"r");
	fscanf(ptr_rech_ID,"%*s %s\n", chemin_trouve);
	fclose(ptr_rech_ID);
	
	if(strcmp(ID_a_trouver,"")==0)
		return 0;
	else
		return 1;
}

void recherche_image(){
	char chemin_rech[50],commande[50], ID_rech[20],chemin_aff[50];
	FILE * ptr_rech_ID;
	type_desc_img daux,drech;
	float distance;
	pile_img p;
	
	printf("Saissisez le chemin : ");
	scanf("%s", chemin_rech);
	strcpy(commande,"more liste_base_image | grep '");
	strcat(commande,chemin_rech);
	strcat(commande,"' > fic_temp");
	system(commande);
	ptr_rech_ID=fopen("fic_temp","r");
	fclose(ptr_rech_ID);
	if(test_lecture==-1)
		printf("ERREUR! ID introuvable!\n");
	else
	{
		p=lire_db_img(bits_quant);
		while(pile_est_vide_img(p) == 0)
		{
			drech=depiler_img(&p);
			if(strcmp(drech.nb_ID,ID_rech) == 0)
				break;
		}
		if(strcmp(drech.nb_ID,ID_rech) != 0)
			printf("ERREUR! Descripteur %s introuvable!\n", ID_rech);
		else
		{
			
			init_pile_img(&p);
			p=lire_db_img(bits_quant);
			FILE * ptr_res_cmp;
			char chemin_aux[50];

			ptr_res_cmp=fopen("liste_cmp","w");
			while(pile_est_vide_img(p) == 0)
			{
				daux=depiler_img(&p);
				if((strcmp(daux.nb_ID,drech.nb_ID)!=0)&&(daux.nb_comp == drech.nb_comp))
				{
					distance=compare_img(drech,daux,bits_quant);
					if(distance > 0)
					{
						getChemin_img(daux.nb_ID,chemin_aux);
						fprintf(ptr_res_cmp,"%s - %2.2f pour cent de ressemblance\n",chemin_aux,distance);
					}
				}
			}
			fclose(ptr_res_cmp);
			
			system("sort liste_cmp -n -k 2 | head -n 5 | tee liste_res | head -n 1 > fic_a_ouvrir"); //On ne garde que les 5 premiers resultats dans liste_res, et on garde que le premier dans fic_a_ouvrir
			system("rm liste_cmp");
			printf("\nResultats de la comparaison:\n");
			system("more liste_res");
			
			FILE * ptr_fic_ouvrir;
			char ID_chemin_img_ouvrir[50],chemin_img_ouvrir[50];
			
			ptr_fic_ouvrir= popen("more fic_a_ouvrir","r");
			fscanf(ptr_fic_ouvrir,"%s %*s",ID_chemin_img_ouvrir);
			fclose(ptr_fic_ouvrir);
			
			getChemin_img(ID_chemin_img_ouvrir,chemin_img_ouvrir);
			
			strcpy(commande,"eog ");
			strcat(commande,chemin_img_ouvrir);
			
			if(drech.nb_comp == 1)
				strcat(commande, ".bmp");
			else
				strcat(commande, ".jpg");
			
			printf("\nOuverture de l'image la plus ressemblante...\n");
			
			system(commande);
			system("rm fic_a_ouvrir liste_res");
		}
	}
}

void recherche_texte_fic(){
	char chemin_rech[50], ID_rech[20],commande[50],chemin_aff[50];
	FILE * ptr_rech_ID;
	pile_texte p;
	type_desc_texte drech, daux;
	printf("Saissisez le chemin : ");
	scanf("%s", chemin_rech);
	strcpy(commande,"more liste_base_texte | grep '");
	strcat(commande,chemin_rech);
	strcat(commande,"' > fic_temp");
	system(commande);
	ptr_rech_ID=fopen("fic_temp","r");
	int test_lecture=fscanf(ptr_rech_ID,"%s %s", ID_rech,chemin_aff);
	fclose(ptr_rech_ID);
	if(test_lecture == -1)
		fprintf(stderr, "ERREUR! ID introuvable!\n");
	else
	{
		printf("Recherche du fichier %s lancée...\n", chemin_aff);
		init_pile_texte(&p);
		p=lire_db_texte();
		while(pile_est_vide_texte(p) == 0)
		{
			drech=depiler_texte(&p);
			if(strcmp(drech.nb_ID,ID_rech) == 0)
				break;
		}
		if(strcmp(drech.nb_ID,ID_rech) != 0)
			printf("ERREUR! Descripteur %s introuvable!\n", ID_rech);
		else
		{
			
			init_pile_texte(&p);
			p=lire_db_texte();
			FILE * ptr_res_cmp;
			float distance;
			char chemin_aux[50];
			
			ptr_res_cmp=fopen("liste_cmp","w");
			while(pile_est_vide_texte(p) == 0)
			{
				daux=depiler_texte(&p);
				if(strcmp(daux.nb_ID,drech.nb_ID)!=0)
				{
					distance=compare_Text_fichier(drech,daux);
					if(distance > 0)
					{
						getChemin_texte(daux.nb_ID,chemin_aux);
						fprintf(ptr_res_cmp,"%s - %2.2f pour cent de ressemblance\n",chemin_aux,distance);
					}
				}
			}
			fclose(ptr_res_cmp);
			system("sort liste_cmp -n -k 2 | head -n 5 > liste_res"); //On ne garde que les 5 premiers resultats dans liste_res, et on garde que le premier dans fic_a_ouvrir
			system("rm liste_cmp");
			printf("\nResultats de la comparaison:\n");
			system("more liste_res");
			
			system("rm liste_res");
		}
	}
}

void recherche_motcle(){
	char mot_cle[25];
	printf("Saissisez le mot-clé à rechercher: ");
	scanf("%s", mot_cle);
	
	pile_texte p;
	type_desc_texte daux;
	float distance;
	FILE * ptr_res_cmp;
	char chemin_rech[50];
	
	ptr_res_cmp=fopen("liste_cmp","w");
	p=lire_db_texte();
	while(pile_est_vide_texte(p) == 0)
	{
		daux = depiler_texte(&p);
		distance=compare_Texmot(mot_cle,daux);
		getChemin_texte(daux.nb_ID,chemin_rech);
		if(distance != 0)
			fprintf(ptr_res_cmp,"%s %2.2f\n",chemin_rech,distance);
	}
	fclose(ptr_res_cmp);
	system("sort liste_cmp -n -k 2 -r | head -n 5 > liste_res"); //On ne garde que les 5 premiers resultats dans liste_res, et on garde que le premier dans fic_a_ouvrir
	system("rm liste_cmp");
	printf("\nResultats de la comparaison:\n");
	system("more liste_res");
	
	system("rm liste_res");
}

void main(){
	int choix_menu,choix_quitter;
	
	//¡*******************************************************************Indexation (si necessaire)***********************************************************/
	
	FILE * ptr_db,* ptr_liste_index;
	pile_img p;
	pile_texte p1;
	
	char chemin_index[50];
	
	//Indexation images
	
	ptr_db=fopen("base_descripteur_image","r"); //On essaie d'ouvrir la base
	if(ptr_db == NULL) 
	{
		
		init_pile_img(&p);
		printf("Indexation des images en cours...\n");
		system("ls IMG_RGB/*.txt > fic_temp");
		system("ls IMG_NB/*.txt >> fic_temp");
		ptr_liste_index=fopen("fic_temp", "r");
		while(!feof(ptr_liste_index)) {
			fscanf(ptr_liste_index,"%s\n",chemin_index);
			empiler_img(&p,indexeur_img(chemin_index, bits_quant));
		}
		fclose(ptr_liste_index);
		system("rm fic_temp");
		ecrire_db_img(p);
		printf("Indexation et génération de base de descripteurs image finalisée!\n\n");
	}
	else
		fclose(ptr_db);
	ptr_db=fopen("liste_base_image","r");
	if(ptr_db == NULL)
	{
		system("ls -i IMG_RGB/*.txt > liste_base_image");
		system("ls -i IMG_NB/*.txt >> liste_base_image");
	}
	else
		fclose(ptr_db);
	
	//Indexation Textes
	ptr_db=fopen("base_descripteur_texte","r");
	if(ptr_db == NULL){
		init_pile_texte(&p1);
		printf("Indexation des textes en cours...\n");
		system("ls Textes/*.xml > fic_temp");
		ptr_liste_index=fopen("fic_temp", "r");
		while(!feof(ptr_liste_index)) {
			fscanf(ptr_liste_index,"%s\n",chemin_index);
			empiler_texte(&p1,indexeur_textes(chemin_index));
		}
		fclose(ptr_liste_index);
		system("rm fic_temp");
		ecrire_db_texte(p1);
	}
	else
		fclose(ptr_db);
	ptr_db=fopen("liste_base_texte","r");
	if(ptr_db == NULL)
	{
		system("ls -i Textes/*.xml > liste_base_texte");
	}
	else
		fclose(ptr_db);
	/******************************************************Recherche****************************************************************/
	
	
	do{
		choix_menu=affichage_menu();
		switch(choix_menu)
		{
			case 1:
			{
				recherche_image();
				break;
			}
			case 2:
			{
				recherche_texte_fic();
				break;
			}
			case 3:
			{
				recherche_motcle();
				break;
			}
			default:
				printf("Choix invalide!\n");
				break;
		}
		choix_quitter=affichage_fin();
	}while(choix_quitter != 2);
}