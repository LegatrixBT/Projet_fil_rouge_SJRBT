#include "action_config.h"

void lecture_config(type_config * config)
{
	/* Cette fonction permet de recuperer les informations necessaires a l'execution du programme
	Elle permet de recuperer ces parametres depuis un fichier externe : config.txt
	Pour que cette fonction marche, le .config doit absolument avoir cette forme :
	
	<CONFIG>
	<INDEXEUR_TEXTE> TAILLE_MOT 50 TAILLE_DESC 10 </INDEXEUR_TEXTE>
	<INDEXEUR_IMAGE> BIT_QUANT 5 </INDEXEUR_IMAGE>
	<COMPARATEUR> DEG_RESS_IM 21.10 DEG_RESS_TEXT 1.50 </COMPARATEUR>
	</CONFIG>
	
	Les valeurs entieres apres les noms des variables correspondantes peuvent etre changes depuis
	n'importe quel editeur de texte, et ce afin de modifier le fonctionnement du logiciel.
	
	Le balisage permet une lecture plus facile aussi bien pour la machine que pour l'Homme.
	*/
	
	FILE * fichier = NULL; //va contenir le contenu du fichier config le temps du traitement.
	char variable[25]; //va permettre de stocker la chaine a traiter extraite du fichier config
	
	fichier = fopen("config.txt","r");
	
	if(fichier != NULL) //si le fichier c'est correctement ouvert
	{
		fscanf(fichier,"%s",variable);
		if(strcmp(variable, "<CONFIG>")==0)
		{
			while(strcmp(variable, "</CONFIG>")!=0)
			{
				fscanf(fichier,"%s",variable);
				
				if (strcmp(variable, "<INDEXEUR_TEXTE>")==0)
				{
					while(strcmp(variable, "</INDEXEUR_TEXTE>")!=0)
					{
						fscanf(fichier,"%s",variable);
						if(strcmp(variable, "TAILLE_MOT")==0)
							fscanf(fichier,"%d",&(config -> taille_mot));
						
						if(strcmp(variable, "TAILLE_DESC")==0)
							fscanf(fichier,"%d",&(config -> taille_desc));
						
					}
				}
				
				if (strcmp(variable, "<INDEXEUR_IMAGE>")==0)
				{
					while(strcmp(variable, "</INDEXEUR_IMAGE>")!=0)
					{
						fscanf(fichier,"%s",variable);
						if(strcmp(variable, "BIT_QUANT")==0)
							fscanf(fichier,"%d",&(config -> nb_bit_quanti));
					}
				}
				
				if (strcmp(variable, "<COMPARATEUR>")==0)
				{
					while(strcmp(variable, "</COMPARATEUR>")!=0)
					{
						fscanf(fichier,"%s",variable);
						if(strcmp(variable, "DEG_RESS_IM")==0)
							fscanf(fichier,"%f",&(config -> seuil_comp_im));
						if(strcmp(variable, "DEG_RESS_TEXT")==0)
							fscanf(fichier,"%f",&(config -> seuil_comp_text));
					}
				}
			}
		}
		else
			fprintf(stderr,"FORMAT NON RESPECTE\n"); //format de .config non respecte
	}
	else
	{
		fprintf(stderr,"ERREUR LECTURE CONFIG\n");
	}
	fclose(fichier);
	
}