#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

#include "DB_Loader.h"
#include "Compare_img.h"
#ifndef Indexeur_Image_H
#define Indexeur_Image_H

#include "Indexeur_Image.h"

#endif

#ifndef FONCTION_DESCRIPTEUR_TEXTE_H
	#define FONCTION_DESCRIPTEUR_TEXTE_H
	#include "fonctions_descripteur_texte.h"
#endif

int bits_quant = 2;

char affichage_menu()
{
	char choix;
	printf("\n/********************Moteur de recherche texte/image********************/\n");
	printf("Que voulez-vous rechercher?\n");
	printf("	(1) Image\n");
	printf("	(2) Texte\n");
	printf("Saissisez l'option desirée :");
	scanf("%1s",&choix);
	
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
		return 1;
}

void main(){
	int choix_menu;
	char chemin_rech[50],commande[50], ID_rech[20], commande_ouvrir_img[50];
	FILE * ptr_rech_ID, * ptr_liste_index, * ptr_db;
	pile_img p;
	pile_texte p1;
	init_pile_img(&p);
	type_desc_img daux,drech;
	float distance;
	
	//Indexation images
	
	ptr_db=fopen("base_descripteur_image","r");
	if(ptr_db == NULL)
	{
		printf("Indexation des images en cours...\n");
		system("ls IMG_RGB/*.txt > fic_temp");
		system("ls IMG_NB/*.txt >> fic_temp");
		ptr_liste_index=fopen("fic_temp", "r");
		while(!feof(ptr_liste_index)) {
			fscanf(ptr_liste_index,"%s\n",chemin_rech);
			empiler_img(&p,indexeur_img(chemin_rech, bits_quant));
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
	
	/*init_pile_texte(&p1);
	printf("Indexation des textes en cours...\n");
	system("ls Textes/*.xml > fic_temp");
	ptr_liste_index=fopen("fic_temp", "r");
	empiler_texte(&p1,indexeur_textes("Textes/03-Mimer_un_signal_nerveux_pour.xml"));
	empiler_texte(&p1,indexeur_textes("Textes/05-Le_thÚÔtre_de_texte_confrontÚ.xml"));
	int j=0;
	while(j<5) {
		fscanf(ptr_liste_index,"%s\n",chemin_rech);
		empiler_texte(&p1,indexeur_textes(chemin_rech));
		j++;
	}
	fclose(ptr_liste_index);
	system("rm fic_temp");
	ecrire_db_texte(p1);
	printf("Indexation et génération de base de descripteurs texte finalisée!\n\n");*/
	
	//Recherche
	
	while(1) {
		choix_menu=affichage_menu();
		switch(choix_menu)
		{
			case '1':
			{
				chemin_rech[0]=0;
				printf("Saissisez le chemin : ");
				scanf("%s", chemin_rech);
				strcpy(commande,"more liste_base_image | grep '");
				strcat(commande,chemin_rech);
				strcat(commande,"' > fic_grep");
				system(commande);
				ptr_rech_ID=fopen("fic_grep","r");
				int test_lecture=fscanf(ptr_rech_ID,"%s", ID_rech);
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
									fprintf(ptr_res_cmp,"%s %2.2f\n",chemin_aux,distance);
								}
							}
						}
						fclose(ptr_res_cmp);
						system("sort liste_cmp -n -k 2 -r | head -n 5 | tee liste_res | head -n 1 > fic_a_ouvrir"); //On ne garde que les 5 premiers resultats dans liste_res, et on garde que le premier dans fic_a_ouvrir
						//system("rm liste_cmp");
						system("more liste_res");
						
						FILE * ptr_fic_ouvrir;
						char ID_chemin_img_ouvrir[50],chemin_img_ouvrir[50];
						
						ptr_fic_ouvrir= popen("more fic_a_ouvrir","r");
						fscanf(ptr_fic_ouvrir,"%s %*s",ID_chemin_img_ouvrir);
						fclose(ptr_fic_ouvrir);
						
						getChemin_img(ID_chemin_img_ouvrir,chemin_img_ouvrir);
						
						int i,longeur = strlen(chemin_img_ouvrir);
						for(i = longeur - 1;i > longeur - 5;i--)
							chemin_img_ouvrir[i]=0;
						
						strcpy(commande_ouvrir_img,"eog ");
						strcat(commande_ouvrir_img,chemin_img_ouvrir);
						
						if(drech.nb_comp == 1)
							strcat(commande_ouvrir_img, ".bmp");
						else
							strcat(commande_ouvrir_img, ".jpg");
						system(commande_ouvrir_img);
						//system("rm fic_a_ouvrir liste_res");
					}
				}
				break;
			}
			case '2':
				printf("Non implémenté\n");
				break;
			default:
				printf("Choix invalide!\n");
				break;
		}
	}
}