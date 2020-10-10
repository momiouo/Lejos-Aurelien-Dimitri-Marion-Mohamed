package Vue;

import lejos.hardware.port.AnalogPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.port.UARTPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
public class CapteurCouleur extends Capteur {
	private Color couleur;
	private EV3ColorSensor donneesCapteur;
	
	public CapteurCouleur(Perception perception, SensorPort sensorPort) {
		super(perception, sensorPort);
		setCouleur();
	}
	
	public Color getCouleur() {
		return this.couleur;
	}
	
	public boolean couleurEstBlanche() {
		boolean blanc = false;
		if(couleur.getColor() == Color.WHITE) {
			blanc = true;
		}
		return blanc;
	}
	public boolean couleurEstRouge() {
		boolean rouge = false;
		if(couleur.getColor() == Color.RED) {
			rouge = true;
		}
		return rouge;
	}
	public void setCouleur() {
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
		
		//En attendant de récupérer des valeurs precise on utilise RGBMode pour le test :
		donneesCapteur = new EV3ColorSensor((UARTPort) this.getPort());
		SampleProvider sp = donneesCapteur.getRGBMode();
		int sampleSize = sp.sampleSize();
		float[] sample = new float[sampleSize];
		sp.fetchSample(sample, 0);
		int colorNumberValue1 = (int)sample[0];
		int colorNumberValue2 = (int)sample[1];
		int colorNumberValue3 = (int)sample[2];
		Color couleurDetectee = new Color(colorNumberValue1, colorNumberValue2, colorNumberValue3);
		this.couleur = couleurDetectee;
		this.getPerception().CapteurCouleur = couleurDetectee;
	}
}
