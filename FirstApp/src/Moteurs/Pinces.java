package Moteurs;

import lejos.robotics.RegulatedMotor;

public class Pinces { 

// Les pinces sont fermé à l'état initial
	
	private RegulatedMotor moteurPinces;
	private boolean pincesOuvertes;
	
//Constructeur
	public Pinces(RegulatedMotor moteurPinces) {
		this.moteurPinces = moteurPinces;
		this.pincesOuvertes = false;
	}
	
	
//Methodes :
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

/*
 * Ouverture des pinces seulement si pincesOuvertes == false à 800 (correct pour attraper et bien maintenir un palet)
 */
	public void ouverture() {
		if (!this.pincesOuvertes) {
			this.moteurPinces.rotate(800);
		}
		this.pincesOuvertes = true;
	}
/*
 * Fermeture seulement si pinceOuvertes == true
 */
	public void fermeture() {
		if (this.pincesOuvertes) {
			this.moteurPinces.rotate(-800);
		}
		this.pincesOuvertes = false;
	}
	
	
}
