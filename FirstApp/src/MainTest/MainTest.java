package MainTest;

import Robot.Agent;
import lejos.hardware.Button;
import lejos.utility.Delay;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Agent agent = new Agent();

		System.out.println("Press enter to run MainTest...");
		Button.ENTER.waitForPressAndRelease();

		//Bug refermer les pinces
		//agent.getPinces().setPincesOuvertes(true);
		//agent.getPinces().fermeture();

		//agent.getTournerOuPivoter().pivoterDunDegreDonneEnCrochet(180);

		//agent.getTournerOuPivoter().pivoterEtDetecterSurUnDegreDonne(agent, 180);

		agent.getAction().detecterAutourDuRobot(true,false);

		System.out.println("Fin du test");
		Delay.msDelay(20000);

	}

}
