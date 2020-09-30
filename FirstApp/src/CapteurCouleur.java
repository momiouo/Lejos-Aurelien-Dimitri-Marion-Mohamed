import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
public class CapteurCouleur {
	private Color couleur;
	
	public CapteurCouleur(Perception perception) {
		this.couleur = perception.getCouleurCapteurCouleur();
	}
	
	public Color getCouleur() {
		return this.couleur;
	}
	
	public boolean couleurEstBlanche() {
		boolean blanc = false;
		/*if(couleur == blanc) {
			blanc = true;
		}*/
		return blanc;
	}
	public boolean couleurEstRouge() {
		boolean rouge = false;
		/*if(couleur == blanc) {
			blanc = true;
		}*/
		return rouge;
	}
}
