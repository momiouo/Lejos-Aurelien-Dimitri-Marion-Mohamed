package Moteurs;

import lejos.robotics.RegulatedMotor;

public class TournerOuPivoter extends Deplacement {

	public TournerOuPivoter(RegulatedMotor left, RegulatedMotor right) {
		super(left, right);
		// TODO Auto-generated constructor stub
	}
	
	public void pivoterDunDegreDonne(int degre) {
		Math.PI*
		this.getLeftMotor().rotate(degre,true);
		this.getRightMotor().rotate(degre,true);
	}
	
	public void pivoterJusquaDetectionDunPalet() {
		//Capteur ultrason
	}
	
	public void tournerJusquaDetecterUneLigne() {
		//Capteur couleur
	}
	
	public void tournerSurUnTempsEtUneDirection(float seconde, int degre) {
		//Faire touner un moteur plus vite que l'autre.
	}

}
