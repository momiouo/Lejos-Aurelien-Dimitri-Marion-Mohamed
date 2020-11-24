package Moteurs;

import lejos.robotics.RegulatedMotor;

public class Pinces { 

// Les pinces sont fermé à l'état initial
	
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

/*
 * Ouverture des pinces seulement si pincesOuvertes == false
 */
	public void ouverture() {
		if (!this.pincesOuvertes) {
			this.moteurPinces.rotate(800);//C'etait 1000 avant
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
