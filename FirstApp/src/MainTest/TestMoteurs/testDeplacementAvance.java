package MainTest.TestMoteurs;

import Robot.Agent;
import lejos.hardware.Button;
import lejos.utility.Delay;

public class testDeplacementAvance {

	public static void main(String[] args) {
		
		System.out.println("Press enter to run testDeplacementAvance...");
		Button.ENTER.waitForPressAndRelease();
		
		//Les moteurs des roues doivent etre branchés sur le port B et C
		Agent agent = new Agent();
		
		System.out.println("J'avance jusqu'à une ligne blanche");
		agent.getAvancerOuReculer().avancerJusquaUneLigne(agent.getCapteurCouleur(), "blanc");
		Delay.msDelay(2000);
		
		System.out.println("J'avance jusqu'à pression du capteur tactile");
		agent.getAvancerOuReculer().avancerTqCapteurPressionPasEnfonce(agent.getCapteurTactile(), agent.getAction(), agent.getCapteurCouleur());
		Delay.msDelay(2000);
		
		System.out.println("Pivoter et detection d'un palet");
		agent.getTournerOuPivoter().pivoterEtDetecterSurUnDegreDonne(agent, 180);
		Delay.msDelay(2000);
		
		System.out.println("On enchaine sur verif si objet detecte est un palet");
		boolean test = agent.getCapteurUltrasons().VerifSiObjetDetecteEstUnPalet(agent.getAvancerOuReculer(),agent.getCapteurTactile(),agent.getCapteurCouleur());
		System.out.println("true = palet mur sinon => " + test);
		
		System.out.println("Fin du test");
		Delay.msDelay(5000);
	}

}
