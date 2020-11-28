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
		
		System.out.println("Quart de tour sans palet");
		agent.getTournerOuPivoter().pivoterAvecDeuxRoues(45);;
		Delay.msDelay(2000);
		
		System.out.println("Quart de tour sans palet");
		agent.getTournerOuPivoter().pivoterAvecDeuxRoues(45);;
		Delay.msDelay(2000);
		
		System.out.println("On pivote de 180");
		agent.getTournerOuPivoter().pivoterAvecDeuxRoues(180);
		Delay.msDelay(2000);
		
		System.out.println("On pivote de 180 a gauche");
		agent.getTournerOuPivoter().pivoterAvecDeuxRoues(-180);
		Delay.msDelay(2000);
		
		System.out.println("Mettre un palet");
		Delay.msDelay(10000);
		
		System.out.println("Quart de tour avec palet");
		agent.getTournerOuPivoter().pivoterAvecDeuxRouesAvecPalet(45);
		Delay.msDelay(2000);
		
		System.out.println("Quart de tour avec palet");
		agent.getTournerOuPivoter().pivoterAvecDeuxRouesAvecPalet(45);
		Delay.msDelay(2000);
		
		System.out.println("On pivote de 180 avec palet");
		agent.getTournerOuPivoter().pivoterAvecDeuxRouesAvecPalet(180);
		Delay.msDelay(2000);
		
		System.out.println("On pivote de 180 avec palet");
		agent.getTournerOuPivoter().pivoterAvecDeuxRouesAvecPalet(-180);
		Delay.msDelay(2000);
		
		System.out.println("enlever le palet");
		Delay.msDelay(10000);
		
		System.out.println("On pivote de 135 sans palet");
		agent.getTournerOuPivoter().pivoterAvecDeuxRoues(135);
		Delay.msDelay(2000);
		
		System.out.println("Fin du test");
		//Delay.msDelay(5000);

	}

}
