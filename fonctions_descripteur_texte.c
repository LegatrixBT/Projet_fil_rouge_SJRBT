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
#define TAILLE_DICO 5

// Declaration des variables

//type_desc_texte desc;
	


//// FONCTIONS POUR L INDEXEUR TEXTE////

void tri_decroissant_desc_t (int tab[], char tabc[][TAILLE_MAX_MOT], int taille){
	
	int i,j;
	int occur;
	char elem [TAILLE_MAX_MOT];;
	
	for (i = 1; i < taille; i++) 
	{
       occur = tab[i];
	   strcpy(elem,tabc[i]);
	  // printf("mot en cours: %s\n",tabc[i-1]);  DEBUG 
       for (j = i; j > 0 && tab[j-1] < occur; j--)
	   {
           tab[j] = tab[j-1];
		   // de même pour la chaine ! 
		   strcpy(tabc[j],tabc[j-1]);
	   }
       tab[j] = occur;
	   strcpy(tabc[j],elem);
	}
	
    /*
	 // AFFICHAGE DES ELEMENTS POUR DEBUG
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
	char dico [TAILLE_DICO][20] = { "a", "dans", "par", "pour", "avec"};
						
	int nbr_mot_texte=0;		// declaration de la variable qui contiendra le nombre de mots du texte et init a 0;
	char mot[10][50]={'\0'}; 	// declaration de la variable qui contiendra les != mots et init à vide 
	int occurence[10]={0};		// declaration de la variable qui contiendra le nombre d'occurence des != mots et init à vide
	char nb_ID[20];				// declaration de la variable qui contiendra le numero inode du fichier
	
	FILE * pc;					// pointeur pour les popen 
	//char c_nb_occurence[TAILLE_DESC_T]; // char contenant le nombre d'occurence recupere par le popen+commande unix
	char mot_compare[TAILLE_MAX_MOT];				// char contenant le mot en cours de comparaison et envoye au systeme unix
	
	char mot_aux[TAILLE_MAX_MOT]={'\0'}; 
	
	int nb_occurence=0;					// int contenant le nombre d'occurence du mot en cours de traitement
	int ret;

	type_desc_texte d;				// structure du descripteur retournee apres traitement
	
	
	// On  récupère l'inode du fichier 
	strcpy(commande, "ls -i "); 
	strcat(commande, chemin);
	pc = popen(commande,"r");	
		fscanf(pc, "%s",d.nb_ID); // Lecture de l'inode (1ere chaine de ls -i)
	pclose(pc);
	//printf(" Inode du fichier : %s\n",d.nb_ID);
	// PENSER A MAJ LE DERNIER MOT  ET TROUVER BLANCS DANS LISTE !!! 
	
	//printf("parsing en cours \n\r");
	
	strcpy(commande, "cat ");          //On crée une commande qui permet de 
	strcat(commande, chemin);		//stocker le .xml dans un fichier temporaire text_temp"
	strcat(commande, " > text_temp1");
	system(commande); // On execute la commande ainsi crée

	//printf("creation du fichier temporaire1\n");
	strcpy(commande,"grep -iae '^<phrase>' -aie '</phrase>' text_temp1 > text_temp2");
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
			// il faut mieux traiter le 1er mot ayant apparaissant appres 1er changement d'occurence §§§§!!!!
			
			fscanf(ptr_file, "%s", mot_compare);	// on lit la première ligne du fichier 
			nbr_mot_texte++;						// on incrémente le compteur de mot 
			ret = strcmp(mot_compare, mot_aux);		// on compare les deux chaines 
			
			
			if(ret !=0)	// comparaison des chaines  
			{							// si différentes c'est un nouveau mot, on reset le cpt d'occurence
				for(int i=0; i<TAILLE_DICO; i++) // On traite les mots exceptions du dictionnaire
				{
					if (strcmp(mot_compare,dico[i])==0)
						strcpy(mot_compare,mot_aux);
					
				}
				
				if(nb_occurence > occurence[TAILLE_DESC_T-1])	// si ce nouveau mot à plus d'occurence que celui qui en avait le moins on déclence le tri 
				{
					occurence[TAILLE_DESC_T-1]= nb_occurence ;
					//mot[TAILLE_DESC_T-1][50]= mot_compare;
					memcpy(&mot[TAILLE_DESC_T-1],&mot_aux,TAILLE_MAX_MOT);
					//printf(" mot envoyé au tri : %s\n",mot[9]);					 //DEBUG
					//printf(" occurence : %d \n",occurence[9]);
					tri_decroissant_desc_t(occurence,mot,TAILLE_DESC_T);
				}
			
				nb_occurence=1;					// on a au moins une occurence puisque mot en cours ... 
				strcpy(mot_aux,mot_compare);	// on change le mot dans le buffer 
				//printf(" le mot aux est : %s\n",mot_aux);	DEBUG
			}
			else								// sinon on compte le nombre d'occurence du mot !
			{
				nb_occurence++;
				
			}

		}
	
		// Affectation des résultat à la structure 
		for(int i = 0; i < TAILLE_DESC_T; i++)
		{
			memcpy(&d.mot[i],&mot[i],TAILLE_MAX_MOT);
			d.occurence[i] = occurence[i];
		}
		d.nbr_mot_texte=nbr_mot_texte-1;
		affiche_descripteur(d); // on affiche le descripteur
		
		fclose(ptr_file);
	
	}
	system("rm text_temp1");
	system("rm text_temp2");
	system("rm text_temp2e");
	return d;
	// remove des fichiers temporaires 
	
	
	
}
