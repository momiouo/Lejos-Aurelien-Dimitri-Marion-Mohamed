package MainTest;

import Robot.Agent;
import lejos.hardware.Button;
import lejos.utility.Delay;

public class testPremieresActions {

	public static void main(String[] args) {
		Agent agent = new Agent();
		
		int positionInitiale = 1;//Milieu par défaut
		
		System.out.println("Press left or right to run testPremieresActions...");

		Button.waitForAnyPress();
		
		if(Button.RIGHT.isDown()) {
			positionInitiale = 2;
			System.out.println("Right");
		}else if(Button.LEFT.isDown()) {
			positionInitiale = 0;
			System.out.println("Left");
		}
		
		
		agent.getAction().premieresActions(positionInitiale);
		
		agent.getPinces().fermeture();
		
		System.out.println("Fin du test premieresActions");
		Delay.msDelay(5000);
	}

}
