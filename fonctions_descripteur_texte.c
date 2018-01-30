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
#define TAILLE_DICO 16

////---------------------------- FONCTIONS POUR L ARBRE BINAIRE-----------------------------------////

void addNoeud(noeud **arbre, char* mot)
{
    noeud *noeudAux;
    noeud *arbreAux = *arbre;

    noeud *elem = malloc(sizeof(noeud));
	strcpy(elem->mot,mot);
    elem->gauche = NULL;
    elem->droite = NULL;

    if(arbreAux)
    do
    {
        noeudAux = arbreAux;
		if(strcmp(mot,arbreAux->mot)>0)
        {
            arbreAux = arbreAux->droite;
            if(!arbreAux) 
				noeudAux->droite = elem;
        }
        else
        {
            arbreAux = arbreAux->gauche;
            if(!arbreAux) 
				noeudAux->gauche = elem;
        }
    }
    while(arbreAux);
    else  *arbre = elem;
}

void initDico (noeud **Arbre) { // les mots en commentaire ne sont par traité car
								//on supprime tous ces mots pour alléger le traitement
								// si on ne considere pas le temps d'indexation comme un parametre important
								// on laisse tout ces mots et retire la fonction qui coupe les mots de moins de 2 lettres
	
	*Arbre = NULL;
	addNoeud(Arbre, "dans");
	addNoeud(Arbre, "pour");
	addNoeud(Arbre, "vers");
	addNoeud(Arbre, "avec");
	addNoeud(Arbre, "sans");
	addNoeud(Arbre, "donc");
	addNoeud(Arbre, "dont");
	addNoeud(Arbre, "puis");
	//addNoeud(Arbre, "l");
	//addNoeud(Arbre, "t");
	//addNoeud(Arbre, "d");
	//addNoeud(Arbre, "le");
	//addNoeud(Arbre, "en");
	addNoeud(Arbre, "les");
	//addNoeud(Arbre, "la");
	addNoeud(Arbre, "qui");
	addNoeud(Arbre, "que");
	addNoeud(Arbre, "quoi");
	//addNoeud(Arbre, "a");
	//addNoeud(Arbre, "qu");
	////addNoeud(Arbre, "de");
	//addNoeud(Arbre, "da");
	addNoeud(Arbre, "des");
	//addNoeud(Arbre, "un");
	addNoeud(Arbre, "une");
	//addNoeud(Arbre, "et");
	//addNoeud(Arbre, "du");
	addNoeud(Arbre, "par");
	addNoeud(Arbre, "est");
	addNoeud(Arbre, "ils");
	//addNoeud(Arbre, "il");
	addNoeud(Arbre, "elle");
	addNoeud(Arbre, "elles");
	//addNoeud(Arbre, "s");
	//addNoeud(Arbre, "c");
	//addNoeud(Arbre, "au");
	addNoeud(Arbre, "pas");
	//addNoeud(Arbre, "ce");
	addNoeud(Arbre, "ces");
	//addNoeud(Arbre, "n");
	//addNoeud(Arbre, "ou");
	addNoeud(Arbre, "sur");
	//addNoeud(Arbre, "se");
	addNoeud(Arbre, "nous");
	addNoeud(Arbre, "ici");
	//addNoeud(Arbre, "on");
	addNoeud(Arbre, "lui");
	//addNoeud(Arbre, "j");
	addNoeud(Arbre, "plus");
	//addNoeud(Arbre, "ne");
	//addNoeud(Arbre, "sa");
	addNoeud(Arbre, "apres");
	addNoeud(Arbre, "avoir");
	//addNoeud(Arbre, "me");
	addNoeud(Arbre, "cette");
	addNoeud(Arbre, "ses");
	addNoeud(Arbre, "ont");
	//addNoeud(Arbre, "si");
	addNoeud(Arbre, "avait");
	addNoeud(Arbre, "etait");
	addNoeud(Arbre, "mais");
}

int rechercheNoeud(noeud *arbre, char* mot) // rechercher un mot dans l'arbre
{
    while(arbre)
    {
		if(strcmp(mot,arbre->mot)==0)
			return 1;

		if(strcmp(mot,arbre->mot)>0)
			arbre = arbre->droite;
        else arbre = arbre->gauche;
    }
    return 0;
}

void afficherArbre(noeud *arbre) // affichage de l'arbre binaire
{
    if(!arbre) 
		return;

    if(arbre->gauche)  
		afficherArbre(arbre->gauche);

    printf("mot = %s\n", arbre->mot);

    if(arbre->droite) 
		afficherArbre(arbre->droite);
}

void afficherInvArbre(noeud *arbre) // affichage inv de l'arbre (pour le tests)
{
    if(!arbre) 
		return;

    if(arbre->droite) 
		afficherInvArbre(arbre->droite);

    printf("mot = %s\n", arbre->mot);

    if(arbre->gauche)  
		afficherInvArbre(arbre->gauche);
}

void viderArbre(noeud **arbre) // vider l'arbre 
{
    noeud *arbreAux = *arbre;

    if(!arbre) return;

    if(arbreAux->gauche) 
		viderArbre(&arbreAux->gauche);

    if(arbreAux->droite) 
		viderArbre(&arbreAux->droite);
        
    free(arbreAux);

    *arbre = NULL;
}

////---------------------------- FONCTIONS POUR L INDEXEUR TEXTE-----------------------------------////

void tri_decroissant_desc_t (int tab[], char tabc[][TAILLE_MAX_MOT], int taille){ // tri par insertion decroissant
	
	int i,j;						//indice des deux index utilisé pour le tri par insertion
	int occur;						// auxiliaire pour le stockage des occurence
	char elem [TAILLE_MAX_MOT];;	// auxiliaire pour le stockage des mots 
	
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

void affiche_descripteur (type_desc_texte d){ // permet d'afficher le descripteur genere(mot et occurence)
	
	int i;
	printf("DESCRIPTEUR dans la struct :\n");
	for(i = 0; i < 10; i++) 
	printf("mot : %s occurence :%d \n",d.mot[i],d.occurence[i]);
	printf("\n"); 
	
}
void suppression_text_temp () { // remove des fichiers temporaires 
	
	system("rm text_temp1");
	system("rm text_temp2");
	system("rm text_temp2e");
}


//// ------------------------------------ INDEXEUR TEXTE----------------------------------------////

type_desc_texte indexeur_textes (char chemin[]) {
	
	////------------------------------ DECLARATION DES VARIABLES -------------------------------////
	FILE * ptr_file;			// pointeur sur le fichier traite
	char commande[200]; 		// chaine contenant les commandes unix
	////-----Dictionnaire des mots ignorés---------////
	/*char dico [TAILLE_DICO][20] = { "dans", "pour", "avec", "sans", "sous", "mais",
	"aussi", "plus", "moins", "tous", "dont","sans", "puis", "donc", "ainsi", "deja"};
	*/
	//noeud *Arbre;
	noeud *Arbre; 
	initDico(&Arbre);
	//afficherArbre(Arbre);	// verification du contenu de l'arbre
	
	int nbr_mot_texte=0;		// declaration de la variable qui contiendra le nombre de mots du texte et init a 0;
	char mot[10][50]={'\0'}; 	// declaration de la variable qui contiendra les != mots et init à vide 
	int occurence[10]={0};		// declaration de la variable qui contiendra le nombre d'occurence des != mots et init à vide
	char nb_ID[20];				// declaration de la variable qui contiendra le numero inode du fichier
	
	FILE * pc;					// pointeur pour les popen 
	
	char mot_compare[TAILLE_MAX_MOT];	    	// char contenant le mot en cours de comparaison et envoye au systeme unix
	
	char mot_aux[TAILLE_MAX_MOT]={'\0'}; 
	
	int nb_occurence=0;							// int contenant le nombre d'occurence du mot en cours de traitement
	int i;
	type_desc_texte d;							// structure du descripteur retournee apres traitement
	
	
	//------------ On  récupère l'inode du fichier-------------------// 
	strcpy(commande, "ls -i "); 
	strcat(commande, chemin);
	pc = popen(commande,"r");		//ouverture du pipe pour lancer la commande et récupérer le retour de la commande
		fscanf(pc, "%s",d.nb_ID); 	// Lecture de l'inode (1ere chaine de ls -i)
	pclose(pc);						//fermeture du pipe
	//printf(" Inode du fichier : %s\n",d.nb_ID); 		DEBUG
	
	strcpy(commande, "cat ");          //On crée une commande qui permet de 
	strcat(commande, chemin);		//stocker le .xml dans un fichier temporaire text_temp"
	strcat(commande, " > text_temp1");
	system(commande); // On execute la commande ainsi crée

	strcpy(commande,"grep -iae '^<phrase>' -aie '</phrase>' text_temp1 > text_temp2");
	system(commande);
	strcpy(commande, "sed -rie ':phrase s/<[^>]*>//g; /</ {N; b phrase}' text_temp2"); // on supprime les balises de phrases !
	system(commande);
	
	// traitement de l'encodage (donc des accents)  ! il faut lire LIGNE  PRENDRE l'encodage et le mettre dans la fonction 
	strcpy(commande,"cat text_temp2 | iconv -f  iso-8859-1 -t ascii//TRANSLIT > text_temp1");
	system(commande);
	
	////// PARTIE FILTRAGE DU TEXTE/////
	//remplacer les ponctuations et numériques par des espaces, les sauts de ligne et les doubles espaces, tab ect par un seul espace  ! 
	// attention tous les chiffres sont gardés même si ils sont collés à un mot, les points sont supprimés dans tous les cas ex 1.2 --> 1 2 on stocke le tout ligne par ligne ! 
	strcpy(commande,"< text_temp1 tr 'A-Z' 'a-z' | tr -c a-z0-9 ' ' | tr -s ' ' | tr ' ' '\n' > text_temp2");  // un peu brutal mais efficace 
	system(commande);
	
	//// Réduction des mots de moins de 3 caractères ou moins   !!!!   SOLUTION PROVISOIRE
	/*
	strcpy(commande,"grep -wve '[a-z]' -wve '[a-z][a-z]' -wve '[a-z][a-z][a-z]' text_temp2 > text_temp1");
	system(commande);
	*/
	
	//// Réduction des mots de moins de 2 caractères ou moins   !!!!  PERMET INDEXATION PLUS RAPIDE
	
	strcpy(commande,"grep -wve '[a-z]' -wve '[a-z][a-z]' text_temp2 > text_temp1");
	system(commande);
	
	//// tri par ordre alphanumérique et alphabétique des mots composant le texte;
	strcpy(commande,"sort -bf text_temp1 > text_temp2");		
	system(commande);
	
	ptr_file = fopen("text_temp2", "r"); //Ouverture du fichier temporaire 2 !!!! 
	if(ptr_file != NULL) //Verif qu'on a bien ouvert le fichier
	{

		while (feof(ptr_file)==0) // tant qu'on ne détecte pas la fin du fichier, on effectue le traitement!
		{
			// il faut mieux traiter le 1er mot ayant apparaissant appres 1er changement d'occurence §§§§!!!!
			
			fscanf(ptr_file, "%s", mot_compare);	// on lit la première ligne du fichier 
			nbr_mot_texte++;						// on incrémente le compteur de mot 			
			
			if(strcmp(mot_compare, mot_aux)!=0)		// comparaison des chaines  
			{										// si différentes c'est un nouveau mot, on reset le cpt d'occurence
				/*
				for(i=0; i<TAILLE_DICO; i++) 	// On traite les mots exceptions du dictionnaire
				{
					if (strcmp(mot_compare,dico[i])==0)
						strcpy(mot_compare,mot_aux);
					
				}
				*/
				if(rechercheNoeud(Arbre, mot_compare))
				{					
					//printf("La mot %s existe.\n", mot_compare); Pour le debug affichage du mot envoyé 
					strcpy(mot_compare,mot_aux);
				}
				//else printf("La mot %s n'existe pas.\n", mot_compare);
				
				if(nb_occurence > occurence[TAILLE_DESC_T-1])	// si ce nouveau mot à plus d'occurence que celui qui en avait le moins on déclence le tri 
				{
					occurence[TAILLE_DESC_T-1]= nb_occurence ;
					memcpy(&mot[TAILLE_DESC_T-1],&mot_aux,TAILLE_MAX_MOT);
					//printf(" mot envoyé au tri : %s\n",mot[9]);					 //DEBUG
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
	
		for(i = 0; i < TAILLE_DESC_T; i++){ // Affectation des résultat à la structure 
			
			memcpy(&d.mot[i],&mot[i],TAILLE_MAX_MOT);
			d.occurence[i] = occurence[i];
		}
		d.nbr_mot_texte=nbr_mot_texte-1; 	// on affecte au descripteur le nombre de mot du texte réduit
		//affiche_descripteur(d); 			// on affiche le descripteur
		fclose(ptr_file); 					// fermeture du fichier de travail 
	
	}
	
	suppression_text_temp();
	return d;
	
	
}