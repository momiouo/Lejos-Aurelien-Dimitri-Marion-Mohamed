package MainTest;
import Moteurs.AvancerOuReculer;
import Moteurs.Deplacement;
import Moteurs.TournerOuPivoter;
import Robot.Agent;
import Vue.CapteurCouleur;
import Vue.CapteurUltrasons;
import Vue.Perception;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.robotics.Color;
import lejos.robotics.RegulatedMotor;

public class testDeplacement {

	public static void main(String[] args) {
		//Les moteurs des roues doivent etre branchés sur le port B et C
		Agent agent = new Agent();
	    
	    //Création des objets pour le déplacement
		//AvancerOuReculer avanceroureculer = agent.getAvancerOuReculer();
		TournerOuPivoter tourneroupivoter = agent.getTournerOuPivoter();
		CapteurUltrasons capteurUltrasons = new CapteurUltrasons(agent.getPerceptionAct(), (SensorPort) SensorPort.S1);
/*
		System.out.println("J'avance 3 secondes");
		avanceroureculer.avancerPourUnTemps(3);
		
		System.out.println("Je recule 3 secondes");
		avanceroureculer.reculerPourUnTemps(3);
		
		*/
		System.out.println("Je pivote de 90 degrés dans un sens");
		tourneroupivoter.pivoterDunDegreDonne(90);
		
		System.out.println("Je pivote de 90 degrés dans l'autre sens");
		tourneroupivoter.pivoterDunDegreDonne(-90);
		
		/*
		System.out.println("On test de combien de centimètre j'avance pour 1 révolution");
		avanceroureculer.avancerSurUneDistance(0);
		
		System.out.println("On test de combien de centimètre j'avance pour 1 révolution");
		avanceroureculer.reculerSurUneDistance(0);
		
		System.out.println("Je tourne vers la droite");
		tourneroupivoter.tournerSurUnTempsEtUneDirectionVague(3, 1);//1 > 0 => vers la droite
		
		System.out.println("Je tourne vers la gauche");
		tourneroupivoter.tournerSurUnTempsEtUneDirectionVague(3, -1);//-1 < 0 => vers la gauche
		
		//Continuer a tester toutes les methodes de deplacement ...
		/*
		Agent agent = new Agent();
		Color blanc = new Color(0,0,0);
		System.out.println("J'avance jusqu'à une ligne blanche");
		agent.getAvancerOuReculer().avancerJusquaUneLigne(agent.getCapteurCouleur(), blanc);//Verifier le constucteur agent utilise les bons ports
		*/
		//Pivoter jusqu'a detection d'un palet faire la fonction verifsiobjetdetecteeestunpalet
		
		
		System.out.println("Fin du test");
	}

}
