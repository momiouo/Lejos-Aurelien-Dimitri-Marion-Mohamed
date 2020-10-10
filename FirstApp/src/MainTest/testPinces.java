package MainTest;

import Moteurs.Pinces;
import lejos.hardware.motor.Motor;

public class testPinces {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Test des pinces sur le port A");
		Pinces pinces = new Pinces(Motor.A);
		
		System.out.println("On mets les pinces en position initiales");
		pinces.initPositionPinces();
		
		System.out.println("On ouvre les pinces");
		pinces.ouverture();
		
		System.out.println("On ferme les pinces");
		pinces.fermeture();
		
		System.out.println("Fin du test des pinces");
	}

}
