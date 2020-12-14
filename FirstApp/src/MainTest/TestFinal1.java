package MainTest;

import Robot.Agent;
import lejos.hardware.Button;
import lejos.utility.Delay;

/**
 * Premier test final élaboré qui utilise la fonction : "detecterAutourDuRobot" qui a pour but de détecter les palets qui l'entourent.
 * Cependant par manque de précision (le robot n'avance pas droit). il a été préférable d'adopter une nouvelle stratégie
 * ne se basant pas sur la précision car lorsque l'on doit vérifier si c'est un palet ou non => cela consistait à avancer lentement
 * vers le palet afin de voir si la distance devenait plus grande d'un coup et que donc c'était un palet. Malheureusement le robot
 * n'avançant pas droit cela crée des imprécisions et il croit détecter un palet alors que le capteur ultrason à détecter plus à gauche ou plus à droite
 * du palet.
 * 
 * 
 * @author LejosTeam
 *
 */
public class TestFinal1 {
	/*
	 * 
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
