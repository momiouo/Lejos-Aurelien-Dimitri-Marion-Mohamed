package Vue;

import lejos.hardware.port.AnalogPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
public class CapteurCouleur extends Capteur {
	private Color couleur;
	private EV3ColorSensor color1 = new EV3ColorSensor(SensorPort.S1);
	
	public CapteurCouleur(Perception perception) {
		super(perception);
		setCouleur();
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
		/*if(couleur == rouge) {
			rouge = true;
		}*/
		return rouge;
	}
	public void setCouleur(Color couleur) {
		//couleurs :
		//rouge = 0
		//bleu = 2
		//jaune = 3
		//vert = 1
		//blanc
		//noir 
		//Le prof à dit qu'il a fourni un code qui permet de calibrer le capteur de couleurs qui renvoie des vecteurs pour 
		//les couleurs et qu'il faut comparer avec le produit scalaire aux vecteurs qu'on trouve et qu'on renvoie celle qui
		//est la plus proche pare que ça dépend beauoup de la luminosité
		SampleProvider sp = color1.getColorIDMode();
		couleur =((EV3ColorSensor) sp).getColorID();
		
		
	}
}
