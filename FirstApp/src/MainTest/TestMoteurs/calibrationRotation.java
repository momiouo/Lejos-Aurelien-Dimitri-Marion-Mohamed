package MainTest.TestMoteurs;

import Robot.Agent;
import lejos.hardware.Button;
import lejos.utility.Delay;

public class calibrationRotation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Agent agent = new Agent();
		
		System.out.println("Press a button to run calibration rotation...");
		Button.waitForAnyPress();
		
		System.out.println("On pivote de 90");
		agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaDroite(90);
		Delay.msDelay(2000);
		
		System.out.println("On pivote de 180");
		agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaDroite(180);
		Delay.msDelay(2000);
		
		System.out.println("On pivote de 180 a gauche");
		agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaGauche(180);
		Delay.msDelay(2000);
		
		
		System.out.println("Fin du test");
		Delay.msDelay(5000);

	}

}
