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
import lejos.utility.Delay;

public class testDeplacement {

	public static void main(String[] args) {
		//Les moteurs des roues doivent etre branchés sur le port B et C
		Agent agent = new Agent();

		/*
		System.out.println("J'avance 5 secondes");
		agent.getAvancerOuReculer().avancerPourUnTemps(5);
		
		Delay.msDelay(2000);
		
		System.out.println("Je recule 3 secondes");
		agent.getAvancerOuReculer().reculerPourUnTemps(3);
		
		Delay.msDelay(2000);
		*/
		
		System.out.println("On test de combien de centimètre j'avance pour 30 cm");
		agent.getAvancerOuReculer().avancerSurUneDistance(300);
		
		Delay.msDelay(2000);
		
		System.out.println("On test de combien de centimètre je recule pour 30 cm");
		agent.getAvancerOuReculer().reculerSurUneDistance(300);
		
		/*
			
		System.out.println("Je pivote de 90 degrés dans un sens");
		agent.getTournerOuPivoter().pivoterDunDegreDonne(90);
		
		Delay.msDelay(2000);
		
		System.out.println("Je pivote de 90 degrés dans l'autre sens");
		agent.getTournerOuPivoter().pivoterDunDegreDonne(-90);
		
		/*
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
