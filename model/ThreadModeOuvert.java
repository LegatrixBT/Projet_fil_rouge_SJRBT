package model;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class ThreadModeOuvert extends Thread {
	private boolean condition = true;
	private FonctionsJNI fonctionsJNI = new FonctionsJNI();
	
	//Ensemble des objets pour la surveillance des dossiers
	private WatchService watchServiceImageRGB; //Images RGB
	private Path pathImageRGB;
	private WatchKey keyImageRGB;
	private WatchService watchServiceImageNB; //Images NB
	private Path pathImageNB;
	private WatchKey keyImageNB;
	private WatchService watchServiceTexte; //Textes
	private Path pathTexte;
	private WatchKey keyTexte;
	
	
	public ThreadModeOuvert() {
		try { //initialisation de la surveillance
			watchServiceImageRGB = FileSystems.getDefault().newWatchService();
			pathImageRGB = Paths.get(System.getProperty("user.dir") + "/IMG_RGB");
			pathImageRGB.register(watchServiceImageRGB, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
			watchServiceImageNB = FileSystems.getDefault().newWatchService();
			pathImageNB = Paths.get(System.getProperty("user.dir") + "/IMG_NB");
			pathImageNB.register(watchServiceImageNB, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
			watchServiceTexte = FileSystems.getDefault().newWatchService();
			pathTexte = Paths.get(System.getProperty("user.dir") + "/Textes");
			pathTexte.register(watchServiceTexte, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
		
		}
		catch(IOException e){
			e.printStackTrace();
		}
	
	}
	
	public void arret() {
		condition = false;
	}
	
	public void run() {
		//fonctionsJNI.indexationImage();
		//fonctionsJNI.indexationTexte();
		System.out.println("Lancement du thread\n");
		while(condition) {
			try {
				Thread.sleep(10000); //Le thread dort tous les 10 secondes
				if(((keyImageRGB = watchServiceImageRGB.poll()) != null) || ((keyImageNB = watchServiceImageNB.poll()) != null)) { //Si on detecte une creation, modification ou suppresion dans les dossiers...
					//fonctionsJNI.indexationImage();
					
					if(keyImageRGB != null) {
						for (WatchEvent<?> event : keyImageRGB.pollEvents()) {
							if((event.kind()== StandardWatchEventKinds.ENTRY_CREATE) && (event.context().toString().contains(".txt")))
								fonctionsJNI.ajouterImage("IMG_RGB/" + event.context().toString());
							if((event.kind()== StandardWatchEventKinds.ENTRY_DELETE) && (event.context().toString().contains(".txt")))
								fonctionsJNI.indexationImage();
						}
						keyImageRGB.reset(); //remise en surveillance
					}
					if(keyImageNB != null) {
						for (WatchEvent<?> event : keyImageNB.pollEvents()) {
							if((event.kind()== StandardWatchEventKinds.ENTRY_CREATE) && (event.context().toString().contains(".txt")))
								fonctionsJNI.ajouterImage("IMG_NB/" + event.context().toString());
							if((event.kind()== StandardWatchEventKinds.ENTRY_DELETE) && (event.context().toString().contains(".txt")))
								fonctionsJNI.indexationImage();
						}
						keyImageNB.reset(); //remise en surveillance
					}
				}
				if((keyTexte = watchServiceTexte.poll()) != null) {
					//fonctionsJNI.indexationTexte();
					for (WatchEvent<?> event : keyTexte.pollEvents()) {
						if((event.kind()== StandardWatchEventKinds.ENTRY_CREATE) && (event.context().toString().contains(".xml")))
							fonctionsJNI.ajouterTexte("Textes/" + event.context().toString());
						if((event.kind()== StandardWatchEventKinds.ENTRY_DELETE) && (event.context().toString().contains(".xml")))
							fonctionsJNI.indexationTexte();
					}
					keyTexte.reset();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Arret du thread\n");
	}
}
