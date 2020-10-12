package Moteurs;

import lejos.robotics.RegulatedMotor;

public class Pinces { //A tester plus tard
	
	private RegulatedMotor moteurPinces;
	private boolean pincesOuvertes;
	
	public Pinces(RegulatedMotor moteurPinces) {
		this.moteurPinces = moteurPinces;
		this.pincesOuvertes = false;
	}
	
	public RegulatedMotor getMoteurPinces() {
		return moteurPinces;
	}

	public void setMoteurPinces(RegulatedMotor moteurPinces) {
		this.moteurPinces = moteurPinces;
	}

	public boolean isPincesOuvertes() {
		return pincesOuvertes;
	}

	public void setPincesOuvertes(boolean pincesOuvertes) {
		this.pincesOuvertes = pincesOuvertes;
	}

	public void ouverture() {//Ouverture des pinces seulement si pincesOuvertes == false
		if (!this.pincesOuvertes) {
			this.moteurPinces.rotate(1000);//Soit on fait rotate soit on calcule le temps qu'il faut pour fermer les pinces et ouvrir et on fait avec forward et backward
		}
		this.pincesOuvertes = true;
	}

	public void fermeture() {//Fermeture seulement si pinceOuvertes == true
		if (this.pincesOuvertes) {
			this.moteurPinces.rotate(-1000);
		}
		this.pincesOuvertes = false;
	}
	
	
}
