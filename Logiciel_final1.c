#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

#include "DB_Loader.h"
#include "Compare_img.h"

float seuil = 50;

void init_logiciel()
{
	FILE * ptr_liste_img,* ptr_base_img,* ptr_base_texte;
	pile_img p;
	init_pile_img(&p);
	ptr_base_img = fopen("base_descripteur_image", "r"); //On ouvre la base des descripteurs indexés
	if(ptr_base_img == NULL) //Si il n'y a pas eu d'indexation auparavant
	{
		printf("Aucune indexation d'images préalable detectée! Indexation des fichiers en cours...\n");
		system("ls IMG_RGB/*.txt > fic_temp");
		system("ls IMG_NB/*.txt >> fic_temp"); //On stocke tous les .txt des images dans un fichier temporel
		
		char chemin_index[50];
		FILE * ptr_liste_index;
		ptr_liste_index=fopen("fic_temp", "r");
		while(!feof(ptr_liste_index))
		{
			fscanf(ptr_liste_index,"%s\n", chemin_index);
			empiler_img(&p, indexeur_img(chemin_index));
		}
		fclose(ptr_liste_index);
		system("rm fic_temp");
		ecrire_db_img(p);
		printf("Indexation et génération de base de descripteurs finalisée!\n\n");
	}
	else
		fclose(ptr_base_img);
	ptr_liste_img = fopen("liste_base_image", "r"); //On ouvre la liste des images
	if(ptr_liste_img == NULL)
	{
		system("ls -i IMG_RGB/*.txt > liste_base_image");
		system("ls -i IMG_NB/*.txt >> liste_base_image");
		printf("Génération de la liste des images finalisée\n");
	}
	else
		fclose(ptr_liste_img);
}

int getID_img(char chemin[], char ID[])
{
	FILE * ptr_liste_img;
	char chemin_a_id[100];
	int trouve=0;
	strcat(chemin, ".txt");
	ptr_liste_img=fopen("liste_base_image","r");
	if(ptr_liste_img != NULL)
	{
		while(!feof(ptr_liste_img))
		{
			fscanf(ptr_liste_img, "%s %s\n", ID, chemin_a_id);
			if(strcmp(chemin_a_id,chemin) == 0)
			{
				trouve = 1;
				break;
			}
		}
		fclose(ptr_liste_img);
		return(trouve);
	}
	else
	{
		fclose(ptr_liste_img);
		return 0;
	}
}

int getDesc_img(char ID[], type_desc_img * d)
{
	pile_img p;
	p = lire_db_img();
	do
	{
		(*d) = depiler_img(&p);
	}while((strcmp((*d).nb_ID, ID) != 0)&&(pile_est_vide_img(p) == 0));
	
	if(strcmp((*d).nb_ID, ID) == 0)
		return 1;
	else
		return 0;
}

int affichage_menu()
{
	int choix;
	printf("\n/********************Moteur de recherche texte/image********************/\n");
	printf("Que voulez-vous rechercher?\n");
	printf("	(1) Image\n");
	printf("	(2) Texte\n");
	printf("Saissisez l'option desirée :");
	scanf("%d", &choix);
	return(choix);
}

int recherche_image() //retourne 0 si il y a eu erreur, 1 sinon
{
	char chemin_recherche[50], ID_recherche[50]; //chemin_saisi le chemin de l'image saisi par le user
	FILE * ptr_liste_img;					//ID_recherche l'ID de cette image
	type_desc_img d_recherche; //descripteur du fichier saisi par le user
	
	printf("Saissisez l'image à recherche : "); //Saisie du chemin
	scanf("%s", chemin_recherche);
	
	if(getID_img(chemin_recherche, ID_recherche) == 0) //On cherche l'ID du fichier, si on ne le trouve pas, on indexe le fichier.
	{
		printf("Fichier inexistant dans la base de données! Indexation du fichier...\n");
		printf("%s\n", chemin_recherche);
		d_recherche = indexeur_img(chemin_recherche);
	}
	else //Si on trouve l'ID du fichier saisi...
	{
		if(getDesc_img(ID_recherche,&d_recherche)==0) //On cherche le descripteur de l'image, si on ne le trouve pas..
		{
			fprintf(stderr,"ERREUR! Impossible d'obtenir le descripteur!\n");
			return 0;
		}
	}
	
	FILE * ptr_res_compare; //pointeur du fichier dans lequel on stocke le resultat de toutes les comparaison
	
	ptr_res_compare=fopen("fic_res_temp", "w"); //ouverture en ecriture du fichier
	if(ptr_res_compare != NULL) //si pas d'erreur de creation du fichier de resultats ...
	{
		pile_img p;
		float distance_comp; //resultat de la comparaison
		type_desc_img daux; //descripteur auxiliaire qui stockera les descripteurs dépilés de p
		char ID_fic_res[20], chemin_fic_res[20],commande[50] = "eog ";
		
		init_pile_img(&p);
		p=lire_db_img(); // lecture de base_descripteur_image et stockage dans une pile de descripteurs image
		
		while(pile_est_vide_img(p) == 0) //tant que p n'est pas vide...
		{
			daux=depiler_img(&p);
			if((strcmp(d_recherche.nb_ID,daux.nb_ID) != 0)&&(d_recherche.nb_comp == daux.nb_comp)) //Si les descripteurs sont du même fichier (meme ID) et que le nombre de composantes est égale...
			{
				distance_comp = compare_img(d_recherche,daux); //On compare les descripteurs...
				if(distance_comp > seuil)
				{
					ptr_liste_img=fopen("liste_base_image","r"); //On cherche le chemin de l'image correspondant à d_aux
					while(!feof(ptr_liste_img))
					{
						fscanf(ptr_liste_img,"%s %s\n", ID_fic_res, chemin_fic_res);
						if(strcmp(ID_fic_res,daux.nb_ID) == 0) //Si les ID correspondent
						{
							fprintf(ptr_res_compare,"%s %.2f\n", chemin_fic_res, distance_comp); //On ecris le chemin avec le resultat dans fic_res_comp
							break;
						}
					}
					fclose(ptr_liste_img);
					distance_comp=0;
				}
			}
		}
		fclose(ptr_res_compare);
		
		//comparaison finie, fic_res_comp géneré
		system("sort fic_res_temp -n -k 2 -r | head -n 5 | tee liste_res | head -n 1 > fic_a_ouvrir"); //On ne garde que les 5 premiers resultats dans liste_res, et on garde que le premier dans fic_a_ouvrir
		system("rm fic_res_temp"); //on supprimer fic_res_temp
		
		ptr_res_compare = fopen("fic_a_ouvrir", "r"); //On ouvre le fichier contenant le chemin de l'image a ouvrir
		fscanf(ptr_res_compare, "%s", chemin_fic_res);
		int i;
		for(i=0;i<4;i++) //On retire l'extension .txt...
			chemin_fic_res[strlen(chemin_fic_res)-1]=0;
		
		/*strcat(commande, chemin_fic_res);
		if(d_recherche.nb_comp == 3) //Puis on ajoute l'extension .jpg ou .bmp en fonction du nombre de composantes du descripteur
			strcat(commande, ".jpg &");
		else
			strcat(commande, ".bmp &");
		system(commande); //On affiche l'image*/
		system("more liste_res"); //On affiche les resultats
		system("rm liste_res fic_a_ouvrir"); //On supprime les fichiers temporaires
		return 1;
	}
	else
	{
		fprintf(stderr, "ERREUR! Création du fichier de resultats impossible!\n");
		return 0;
	}
	
	
}

void main()
{
	
	int choix_recherche, choix_quitter=0;
	init_logiciel();
	
	do{
		choix_recherche=affichage_menu();
		switch(choix_recherche)
		{
			case 1:
				recherche_image();
				break;
				
			case 2:
				printf("Fonctionnalité non implémentée pour l'instant.\n");
				break;
				
			default:
				printf("Choix invalide!\n");
				break;
		}
		printf("Voulez-vous refaire une recherche?\n	(1) Oui\n	(0) Non\n");
		scanf("%d",&choix_quitter);
	}while(choix_quitter == 0);
}