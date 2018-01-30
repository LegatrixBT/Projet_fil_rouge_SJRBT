Indexeur_Image.o : Indexeur_Image.c Indexeur_Image.h
	gcc -c  Indexeur_Image.c

DB_Loader.o : DB_Loader.c DB_Loader.h Indexeur_Image.h Indexeur_Image.c fonctions_descripteur_texte.c fonctions_descripteur_texte.h
	gcc -c DB_Loader.c

Compare_img.o : Compare_img.c Compare_img.h Indexeur_Image.o Indexeur_Image.o
	gcc -c Compare_img.c

fonctions_descripteur_texte.o : fonctions_descripteur_texte.c fonctions_descripteur_texte.h
	gcc -c fonctions_descripteur_texte.c
	
comp_text.o : comp_text.h comp_text.c fonctions_descripteur_texte.h fonctions_descripteur_texte.c
	gcc -c comp_text.c
	
action_config.o : action_config.c action_config.h
	gcc -c action_config.c
	
recherche.out : Logiciel_final.c DB_Loader.o Indexeur_Image.o Compare_img.o fonctions_descripteur_texte.o comp_text.o action_config.o
	gcc Logiciel_final.c -o recherche.out Indexeur_Image.o DB_Loader.o Compare_img.o comp_text.o fonctions_descripteur_texte.o action_config.o -Wall -fstack-protector