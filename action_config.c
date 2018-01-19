#include "action_config.h"

int lecture_config(type_config * config)
{
	FILE * fichier = NULL;
	char variable[25];
	
	fichier = fopen("config.txt","r");
	
	if(fichier != NULL)
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
						printf("%s\n",variable);
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
			fprintf(stderr,"FORMAT NON RESPECTE\n");
	}
	else
	{
		fprintf(stderr,"ERREUR LECTURE CONFIG\n");
	}
	
}