import Moteurs.AvancerOuReculer;
import Moteurs.Deplacement;
import Moteurs.TournerOuPivoter;
import Vue.CapteurCouleur;
import Vue.Perception;
import lejos.hardware.motor.Motor;
import lejos.robotics.Color;
import lejos.robotics.RegulatedMotor;

public class testDeplacement {

	public static void main(String[] args) {
		//Les moteurs des roues doivent etre branchés sur le port B et C (A mettre dans la méthode init() classe Action).
		RegulatedMotor leftMotor = Motor.B;
	    RegulatedMotor rightMotor = Motor.C;
	    
	    //Création des objets pour le déplacement
		AvancerOuReculer avanceroureculer = new AvancerOuReculer(leftMotor,rightMotor);
		TournerOuPivoter tourneroupivoter = new TournerOuPivoter(leftMotor,rightMotor);

		System.out.println("J'avance 3 secondes");
		avanceroureculer.avancerPourUnTemps(3);
		
		System.out.println("Je recule 3 secondes");
		avanceroureculer.reculerPourUnTemps(3);
		
		System.out.println("Je pivote de 90 degrés dans un sens");
		tourneroupivoter.pivoterDunDegreDonne(90);
		
		System.out.println("Je pivote de 90 degrés dans l'autre sens");
		tourneroupivoter.pivoterDunDegreDonne(-90);
		
		System.out.println("On test de combien de centimètre j'avance pour 1 révolution");
		avanceroureculer.avancerSurUneDistance(0);
		
		System.out.println("On test de combien de centimètre j'avance pour 1 révolution");
		avanceroureculer.reculerSurUneDistance(0);
		
		System.out.println("Je tourne vers la droite");
		tourneroupivoter.tournerSurUnTempsEtUneDirectionVague(3, 1);//1 > 0 => vers la droite
		
		System.out.println("Je tourne vers la gauche");
		tourneroupivoter.tournerSurUnTempsEtUneDirectionVague(3, -1);//-1 < 0 => vers la gauche
		
		//Creation d'une perception
		Perception perception = new Perception();
		//Création d'un objet CapteurCouleur
		CapteurCouleur capteurCouleur = new CapteurCouleur(perception);
		capteurCouleur.setCouleur();//Mets à jour l'attribut couleur de la classe capteurcouleur et perception. 
		capteurCouleur.setPort(1);//Capteur branché sur le port n°1
		System.out.println("Avancer jusqu'a une ligne");
		avanceroureculer.avancerJusquaUneLigne(capteurCouleur, Color));
		
		System.out.println("Fin du test");
	}

}
