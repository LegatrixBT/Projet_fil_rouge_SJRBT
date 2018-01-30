
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>


#define TAILLE_DESC_T 10 
#define TAILLE_MAX_MOT 50

typedef struct noeud // structure correspondant a l'arbre
{
    char mot [50];
    struct noeud *gauche;
    struct noeud *droite;
} noeud ;


typedef struct descp_texte // structure correspondant au descripteur de texte 
{
	int nbr_mot_texte;
	char mot[10][50];
	int occurence[10];
	char nb_ID[20];
	
}type_desc_texte;

// ------------------ prototypes de l'arbre ------------------//
void addNoeud(noeud **arbre, char* mot);
int rechercheNoeud(noeud *arbre, char* mot);
void afficherArbre(noeud *arbre);
void afficherInvArbre(noeud *arbre);
void viderArbre(noeud **arbre);
void initDico (noeud **Arbre);


// ------------------ prototypes du descripteur ------------------//
void tri_decroissant_desc_t (int tab[], char tabc[][TAILLE_MAX_MOT], int taille);
void affiche_descripteur (type_desc_texte d);
type_desc_texte indexeur_textes (char chemin[]);


