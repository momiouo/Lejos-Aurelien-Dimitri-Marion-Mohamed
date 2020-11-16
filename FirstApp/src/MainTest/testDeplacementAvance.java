package MainTest;

import Robot.Agent;
import lejos.hardware.Button;
import lejos.utility.Delay;

public class testDeplacementAvance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Press enter to run testDeplacementAvance...");
		Button.ENTER.waitForPressAndRelease();
		
		//Les moteurs des roues doivent etre branchés sur le port B et C
		Agent agent = new Agent();
		
		//Avancer jusqu'a détéction d'une ligne blanche
		System.out.println("J'avance jusqu'à une ligne blanche");
		agent.getAvancerOuReculer().avancerJusquaUneLigne(agent.getCapteurCouleur(), "blanc");
		Delay.msDelay(2000);
		
		//Avancer jusqu'a ce que le capteur tactile s'enfonce
		agent.getAvancerOuReculer().avancerTqCapteurPressionPasEnfonce(agent.getCapteurTactile(), agent.getAction(), agent.getCapteurCouleur());
		Delay.msDelay(2000);
		
		//Pivoter jusqu'a detection d'un palet 
		agent.getAction().detecterAutourDuRobot(true,false);
		Delay.msDelay(2000);
		
		//fonction verifsiobjetdetecteeestunpalet
		boolean test = agent.getCapteurUltrasons().VerifSiObjetDetecteEstUnPalet(agent.getAvancerOuReculer(),agent.getCapteurTactile(),agent.getCapteurCouleur());
		System.out.println("true = palet mur sinon => " + test);
		
		System.out.println("Fin du test");
		Delay.msDelay(2000);
	}

}
