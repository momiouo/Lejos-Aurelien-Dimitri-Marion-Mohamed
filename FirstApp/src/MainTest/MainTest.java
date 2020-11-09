package MainTest;

import Robot.Agent;
import lejos.hardware.Button;
import lejos.utility.Delay;

public class MainTest {

	public static void main(String[] args) {	
		
		System.out.println("Press enter to run MainTest...");
		Button.ENTER.waitForPressAndRelease();
		
		Agent agent = new Agent();
		
		//Bug refermer les pinces
		//agent.getPinces().setPincesOuvertes(true);
		//agent.getPinces().fermeture();
		
		agent.getAction().detecterAutourDuRobot(true,false);
		
		System.out.println("Fin du test");
		Delay.msDelay(20000);
	}

}
