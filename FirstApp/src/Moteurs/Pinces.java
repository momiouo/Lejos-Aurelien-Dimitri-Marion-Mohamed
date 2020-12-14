package Moteurs;

import lejos.robotics.RegulatedMotor;

/**
 * La classe Pinces gère l'ouverture et la fermeture de la pince.
 * 
 * @author LejosTeam
 *
 */
public class Pinces { 

	
	/**
	 * Moteur qui est relié à la pince.
	 */
	private RegulatedMotor moteurPinces;
	/**
	 * Valeur pour savoir si la pince est déjà en position ouverte.
	 */
	private boolean pincesOuvertes;
	
	/**
	 * @param moteurPinces
	 */
	public Pinces(RegulatedMotor moteurPinces) {
		this.moteurPinces = moteurPinces;
		this.pincesOuvertes = false;
	}
	
	
	/**
	 * Retourne l'attribut moteurPinces.
	 * 
	 * @return L'objet RegulatedMotor, qui correspond au moteur relié à la pince.
	 */
	public RegulatedMotor getMoteurPinces() {
		return moteurPinces;
	}

	/**
	 * 
	 * Met à jour l'attribut moteurPinces.
	 * 
	 * @param moteurPinces
	 */
	public void setMoteurPinces(RegulatedMotor moteurPinces) {
		this.moteurPinces = moteurPinces;
	}

	/**
	 * 
	 * Retourne l'attribut pincesOuvertes.
	 * 
	 * @return
	 */
	public boolean isPincesOuvertes() {
		return pincesOuvertes;
	}

	/**
	 * 
	 * Met à jour l'attribut pincesOuvertes.
	 * 
	 * @param pincesOuvertes
	 */
	public void setPincesOuvertes(boolean pincesOuvertes) {
		this.pincesOuvertes = pincesOuvertes;
	}


	/**
	 * Ouverture de la pince seulement si pincesOuvertes == false, On rotate le moteur à 800, qui est une bonne valeur pour attraper et bien maintenir un palet.
	 */
	public void ouverture() {
		if (!this.pincesOuvertes) {
			this.moteurPinces.rotate(800);
		}
		this.pincesOuvertes = true;
	}

	/**
	 * Fermeture de la pince seulement si pinceOuvertes == true
	 */
	public void fermeture() {
		if (this.pincesOuvertes) {
			this.moteurPinces.rotate(-800);
		}
		this.pincesOuvertes = false;
	}
	
	
}
