package Moteurs;

import lejos.robotics.RegulatedMotor;

public class Pinces { //A tester plus tard
	
	private RegulatedMotor moteurPinces;
	private boolean pincesOuvertes;
	
	public Pinces(RegulatedMotor moteurPinces) {
		this.moteurPinces = moteurPinces;
		this.pincesOuvertes = false;
	}
	
	public void InitPositionPinces() {//Pinces en mode ouvert
		//A tester positionnement des pinces
		this.moteurPinces.resetTachoCount();//Reset compte tour
		this.moteurPinces.rotate(0);//mets les pinces à 0 (on imagine c'est fermé)
		this.moteurPinces.rotate(180);//Ouvre les pinces à 180
		pincesOuvertes = true;
		
	}

	public void ouverture() {//Ouverture des pinces seulement si pincesOuvertes == false
		if (!this.pincesOuvertes) {
			this.moteurPinces.rotate(180);
		}
	}

	public void fermeture() {//Fermeture seulement si pinceOuvertes == true
		if (this.pincesOuvertes) {
			this.moteurPinces.rotate(0);
		}
	}
	
	
}
