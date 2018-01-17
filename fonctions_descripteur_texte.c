#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

#ifndef FONCTION_DESCRIPTEUR_TEXTE_H
	#define FONCTION_DESCRIPTEUR_TEXTE_H
	#include "fonctions_descripteur_texte.h"
#endif

// espace define 

#define TAILLE_DESC_T 10 
#define TAILLE_MAX_MOT 50

// Declaration des variables

//type_desc_texte desc;
	


//// FONCTIONS POUR L INDEXEUR TEXTE////

void tri_decroissant_desc_t (int tab[], char tabc[][TAILLE_MAX_MOT], int taille){
	
	int i,j;
	
	for (i = 1; i < taille; i++) 
	{
       int occur = tab[i];
	   char elem[TAILLE_MAX_MOT];
	   strcpy(elem,tabc[i]);
	  // printf("mot en cours: %s\n",tabc[i-1]);  DEBUG 
       for (j = i; j > 0 && tab[j-1] < occur; j--)
	   {
           tab[j] = tab[j-1];
	   }
       tab[j] = occur;
	   strcpy(tabc[j],elem);
	}
	/*  // AFFICHAGE DES ELEMENTS POUR DEBUG
	printf("Apres le tri inverse :\n");
	
	for(int i = 0; i < 10; i++) 
		printf("mot : %s occurence :%d \n",tabc[i],tab[i]);
    printf("\n"); 
	*/
}

void affiche_descripteur (type_desc_texte d){ // permet d'afficher le descripteur genere
	
	printf("DESCRIPTEUR dans la struct :\n");
	for(int i = 0; i < 10; i++) 
	printf("mot : %s occurence :%d \n",d.mot[i],d.occurence[i]);
	printf("\n"); 
	
}


type_desc_texte indexeur_textes (char chemin[]) {
	
	
	
	FILE * ptr_file;			// pointeur sur le fichier traite
	char commande[200]; 		// chaine contenant les commandes unix

	int nbr_mot_texte=0;		// declaration de la variable qui contiendra le nombre de mots du texte et init a 0;
	char mot[10][50]={'\0'}; 	// declaration de la variable qui contiendra les != mots et init à vide 
	int occurence[10]={0};		// declaration de la variable qui contiendra le nombre d'occurence des != mots et init à vide
	char nb_ID[20];				// declaration de la variable qui contiendra le numero inode du fichier
	
	FILE * pc;					// pointeur pour les popen 
	//char c_nb_occurence[TAILLE_DESC_T]; // char contenant le nombre d'occurence recupere par le popen+commande unix
	char mot_compare[50];				// char contenant le mot en cours de comparaison et envoye au systeme unix
	
	char mot_aux[TAILLE_DESC_T]={'\0'}; 
	
	int nb_occurence;					// int contenant le nombre d'occurence du mot en cours de traitement

	type_desc_texte d;				// structure du descripteur retournee apres traitement
	
	
	// On  récupère l'inode du fichier 
	strcpy(commande, "ls -i "); 
	strcat(commande, chemin);
	pc = popen(commande,"r");	
		//fgets(d.nb_ID, 20, pc);	
	fscanf(pc,"%s %*s\n",d.nb_ID);	
	pclose(pc);
	//printf(" Inode du fichier : %s\n",d.nb_ID);
	// PENSER A MAJ LE DERNIER MOT  ET TROUVER BLANCS DANS LISTE !!! 
	
	//printf("parsing en cours \n\r");
	
	strcpy(commande, "cat ");          //On crée une commande qui permet de 
	strcat(commande, chemin);		//stocker le .xml dans un fichier temporaire text_temp"
	strcat(commande, " > text_temp1");
	system(commande); // On execute la commande ainsi crée

	//printf("creation du fichier temporaire1\n");
	strcpy(commande,"grep -iae '^<phrase>' -ie '</phrase>' text_temp1 > text_temp2");
	system(commande);
	strcpy(commande, "sed -rie ':phrase s/<[^>]*>//g; /</ {N; b phrase}' text_temp2"); // on supprime les balises de phrases !
	system(commande);
	
	// rtaitement de l'encodage (donc des accents)  ! il faut lire LIGNE  PRENDRE l'encodage et le mettre dans la fonction 
	strcpy(commande,"cat text_temp2 | iconv -f  iso-8859-1 -t ascii//TRANSLIT > text_temp1");
	system(commande);
	
	////// PARTIE FILTRAGE DU TEXTE/////
	//remplacer les ponctuations et numériques par des espaces, les sauts de ligne et les doubles espaces, tab ect par un seul espace  ! 
	// attention tous les chiffres sont gardés même si ils sont collés à un mot, les points sont supprimés dans tous les cas ex 1.2 --> 1 2 on stocke le tout ligne par ligne ! 
	strcpy(commande,"< text_temp1 tr 'A-Z' 'a-z' | tr -c a-z0-9 ' ' | tr -s ' ' | tr ' ' '\n' > text_temp2");  // un peu brutal mais efficace 
	system(commande);
	

	//// Réduction des mots de moins de 3 caractères ou moins   !!!! 
	
	strcpy(commande,"grep -wve '[a-z]' -wve '[a-z][a-z]' -wve '[a-z][a-z][a-z]' text_temp2 > text_temp1");
	system(commande);
		
	strcpy(commande,"sort -bf text_temp1 > text_temp2");
	system(commande);
	
	ptr_file = fopen("text_temp2", "r"); //Ouverture du fichier temporaire 4 !!!! 
	if(ptr_file != NULL) //Verif qu'on a bien ouvert le fichier
	{

		while (feof(ptr_file)==0) // tant qu'on ne détecte pas la fin du fichier, on effectue le traitement!
		{
		
			fscanf(ptr_file, "%s", mot_compare);	// on lit la première ligne du fichier 
			nbr_mot_texte++;			// on incrémente le compteur de mot 
			
			int ret;
			ret = strcmp(mot_compare, mot_aux);
			if(ret !=0) // comparaison des chaines  
			{							// si différentes c'est un nouveau mot, on reset le cpt d'occurence
			
				if(nb_occurence > occurence[TAILLE_DESC_T-1])	// si ce nouveau mot à plus d'occurence que celui qui en avait le moins on déclence le tri 
				{
					occurence[TAILLE_DESC_T-1]= nb_occurence;
					//mot[TAILLE_DESC_T-1][50]= mot_compare;
					memcpy(&mot[TAILLE_DESC_T-1],&mot_aux,TAILLE_MAX_MOT);
					//printf(" mot envoyé au tri : %s\n",mot[9]);					 //DEBUG
					
					tri_decroissant_desc_t(occurence,mot,TAILLE_DESC_T);
				}
				
				nb_occurence=0;
				//mot_aux = mot_compare;		// on change le mot dans le buffer 
				strcpy(mot_aux,mot_compare);
				//printf(" le mot aux est : %s\n",mot_aux);
			}
			else						// sinon on compte le nombre d'occurence du mot !
			{
				nb_occurence++;
				
			}

			
			//// FAIRE  UN SCANF DU MOT
			// MOT [indice] -- > cpt dans compteur jusqu'a nouveau mot ( et mot actuel en buffer
			// if nouveau mot on reset le buffer et if occurence > occurence[TAILLE_DESC_T-1) lancer le memcpy + tri !
			
			/*
			//printf("mot compare : %s \n", mot_compare); 						// DEBUG
			strcpy(commande,"grep -c '");
			strcat(commande, mot_compare);
			strcat(commande,"$' text_temp1");
			//printf(" commande --> %s\n",commande);
			
			pc = popen(commande,"r");	
				fgets(c_nb_occurence, 8, pc);	
			pclose(pc);
			
			//// On supprime les lignes concernées pour ne pas les re traiter
			strcpy(commande,"sed -i '/");
			strcat(commande,mot_compare);
			strcat(commande,"$/d' text_temp1");
			system(commande);
			
			nbr_mot_texte++;
			
			//printf("Le mot %s apparait : %s",mot_compare,c_nb_occurence); //DEBUG
			nb_occurence = atoi(c_nb_occurence);
			
			
			if(nb_occurence > occurence[TAILLE_DESC_T-1])
			{
				occurence[TAILLE_DESC_T-1]= nb_occurence;
				//mot[TAILLE_DESC_T-1][50]= mot_compare;
				memcpy(&mot[TAILLE_DESC_T-1],&mot_compare,TAILLE_MAX_MOT);
				//printf(" mot envoyé au tri : %s\n",mot[9]);					 //DEBUG
				
				tri_decroissant_desc_t(occurence,mot,TAILLE_DESC_T);
			}
			*/
		}
		
		
		// Affectation des résultat à la structure 
		for(int i = 0; i < TAILLE_DESC_T; i++)
		{
		d.nbr_mot_texte=nbr_mot_texte-1;
		memcpy(&d.mot[i],&mot[i],TAILLE_MAX_MOT);
		d.occurence[i] = occurence[i];
		}
		//affiche_descripteur(d); // on affiche le descripteur
		
		fclose(ptr_file);
	
	}
	system("rm text_temp2 text_temp1");
	return d;
	// remove des deux fichiers temporaires
	
}
