package MainTest;

import Robot.Agent;
import lejos.hardware.Button;
import lejos.utility.Delay;

public class TestFinal1 {
	/*
	 * Premier test final elaboré qui utilisait detecter autour du robot afin de detecter les palets qui l'entourait.
	 * Cependant par manque de précision (le robot n'avance pas droit). il a été préférable d'adopter une autre stratégie
	 * ne se basant pas sur la précision lors de verification si c'est un palet ou non => Cette strategie consistait a avancer lentement
	 * vers le palet afin de voir si la distance devenait plus grande d'un coup et que donc c'était un palet. Malheuresement le robot
	 * n'avancant pas droit cela créer des biais et il croit voir un palet alors que le capteur ultrason est juste passé à gauche ou à droite
	 * du palet...
	 */

	public static void main(String[] args) {

		Agent agent = new Agent();

		System.out.println("Press enter to run MainTest...");
		Button.ENTER.waitForPressAndRelease();

		agent.getAction().detecterAutourDuRobot(true,false);

		System.out.println("Fin du test");
		Delay.msDelay(20000);

	}

}
