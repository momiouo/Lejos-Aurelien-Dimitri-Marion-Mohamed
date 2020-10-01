package Moteurs;

import lejos.robotics.RegulatedMotor;

public class TournerOuPivoter extends Deplacement {

	public TournerOuPivoter(RegulatedMotor left, RegulatedMotor right) {
		super(left, right);
		// TODO Auto-generated constructor stub
	}
	
	public void pivoterDunDegreDonne(int degre) {
		this.getLeftMotor().rotateTo(degre);
		this.getRightMotor().rotateTo(degre);
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
