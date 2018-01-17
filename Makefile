Indexeur_Image.o : Indexeur_Image.c Indexeur_Image.h
	gcc -c  Indexeur_Image.c
	
test_Indexeur.out : test_Indexeur.c Indexeur_Image.c Indexeur_Image.h
	gcc test_Indexeur.c -o test_Indexeur.out Indexeur_Image.o
	
	
DB_Loader.o : DB_Loader.c DB_Loader.h Indexeur_Image.h Indexeur_Image.c fonctions_descripteur_texte.c fonctions_descripteur_texte.h
	gcc -c DB_Loader.c

test_DB.out : test_DB.c Indexeur_Image.o DB_Loader.o
	gcc test_DB.c -o test_DB.out Indexeur_Image.o DB_Loader.o
	
test_final.out : Test_final.c DB_Loader.o Indexeur_Image.o Compare_img.o
	gcc Test_final.c -o test_final.out Indexeur_Image.o DB_Loader.o Compare_img.o

Compare_img.o : Compare_img.c Compare_img.h Indexeur_Image.o Indexeur_Image.o
	gcc -c Compare_img.c
	
test_compare.out : test_compare.c Compare_img.o Indexeur_Image.o
	gcc test_compare.c -o test_compare.out Indexeur_Image.o Compare_img.o

fonctions_descripteur_texte.o : fonctions_descripteur_texte.c fonctions_descripteur_texte.h
	gcc -c fonctions_descripteur_texte.c
	
comp_text.o : comp_text.h comp_text.c fonctions_descripteur_texte.h fonctions_descripteur_texte.c
	gcc -c comp_text.c
	
recherche.out : Logiciel_final.c DB_Loader.o Indexeur_Image.o Compare_img.o fonctions_descripteur_texte.o comp_text.o
	gcc Logiciel_final.c -o recherche.out Indexeur_Image.o DB_Loader.o Compare_img.o comp_text.o fonctions_descripteur_texte.o -Wall -fstack-protector