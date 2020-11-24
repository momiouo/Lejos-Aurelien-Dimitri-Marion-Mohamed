package Vue;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.AnalogPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.port.UARTPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.MeanFilter;
import lejos.utility.Delay;
public class CapteurCouleur extends Capteur {
	private String couleur;
	private EV3ColorSensor donneesCapteur;
	private static float[] blue = {(float)0.014705882,(float)0.03137254,(float)0.055882353};
	private static float[] red = {(float)0.10980393,(float)0.025490196,(float)0.019607844};
	private static float[] green = {(float)0.04411705,(float)0.087254904,(float)0.0362745};
	private static float[] black = {(float)0.019607844,(float)0.025490196,(float)0.023529412};
	private static float[] blanc = {(float) 0.20588236,(float) 0.18921569,(float) 0.17156863};
	private static float[] jaune = {(float) 0.20294118,(float) 0.16862746,(float) 0.04411765};
	private static float[] gris = {(float) 0.07058824,(float) 0.07647059,(float) 0.073529415};
	
	// Constructeur:
	
	public CapteurCouleur(Perception perception, Port port) {
		super(perception, port);
		donneesCapteur = new EV3ColorSensor(this.getPort());
		//calibrer();
		setCouleur();
	}
	
	// Methodes :
	public String getCouleur() {
		return this.couleur;
	}

/*
 * Methode pour calibrer le capteur couleur avec les couleurs du plateau
 * Calibre toutes les couleurs en une seul fois
 */
	public void calibrer() {		
		Port port = this.getPort();
		SampleProvider average = new MeanFilter(donneesCapteur.getRGBMode(), 1);
		donneesCapteur.setFloodlight(Color.WHITE);
		
		//Attributs couleurs pour recuperer les couleurs du plateau
		blue = new float[average.sampleSize()];
		red = new float[average.sampleSize()];
		green = new float[average.sampleSize()];
		black = new float[average.sampleSize()];
		
		// A faire avec le controleur
		System.out.println("Press enter to calibrate blue...");
		Button.ENTER.waitForPressAndRelease();		
		average.fetchSample(blue, 0);
		
		for (float o : blue) {
		    System.out.print(o + " ");
		}
		System.out.println();
		
		
		
		System.out.println("Press enter to calibrate red...");
		Button.ENTER.waitForPressAndRelease();
		average.fetchSample(red, 0);
		
		for (float o : red) {
		    System.out.print(o + " ");
		}
		System.out.println();
		
		
		System.out.println("Press enter to calibrate green...");
		Button.ENTER.waitForPressAndRelease();
		average.fetchSample(green, 0);
		
		for (float o : green) {
		    System.out.print(o + " ");
		}
		System.out.println();

		System.out.println("Press enter to calibrate black...");
		Button.ENTER.waitForPressAndRelease();
		average.fetchSample(black, 0);
		
		for (float o : black) {
		    System.out.print(o + " ");
		}
		System.out.println();
		
		System.out.println("Press enter to finish...");
		Button.ENTER.waitForPressAndRelease();
	}

/*
 * Verifier si la couleur detecté est blanche
 */
	public boolean couleurEstBlanche() {
		boolean blanc = false;
		if(couleur == "blanc") {
			blanc = true;
		}
		return blanc;
	}
/*
 * Verifier si la couleur detecté est rouge
 */
	public boolean couleurEstRouge() {
		boolean rouge = false;
		if(couleur == "rouge") {
			rouge = true;
		}
		return rouge;
	}
	
	public static double scalaire(float[] v1, float[] v2) {
		return Math.sqrt (Math.pow(v1[0] - v2[0], 2.0) +
				Math.pow(v1[1] - v2[1], 2.0) +
				Math.pow(v1[2] - v2[2], 2.0));
	}

/*
 * Methode qui permet de changer la valeur de l’attribut couleur dans la classe CapteurCouleur et l’objet Perception.
 */
	public void setCouleur() {
		
		SampleProvider average = new MeanFilter(donneesCapteur.getRGBMode(), 1);
		boolean again = true;
		String color = "null";
		while (again) {
			float[] sample = new float[average.sampleSize()];
			
			average.fetchSample(sample, 0);
			double minscal = Double.MAX_VALUE;
			
			double scalaire = scalaire(sample, blue);
			if (scalaire < minscal) {
				minscal = scalaire;
				color = "bleu";
				again = false;
			}
			
			scalaire = scalaire(sample, red);
			if (scalaire < minscal) {
				minscal = scalaire;
				color = "rouge";
				again = false;
			}
			
			scalaire = scalaire(sample, green);
			if (scalaire < minscal) {
				minscal = scalaire;
				color = "vert";
				again = false;
			}
			
			scalaire = 	scalaire(sample, black);
			if (scalaire < minscal) {
				minscal = scalaire;
				color = "noir";
				again = false;
			}
			
			scalaire = 	scalaire(sample, blanc);
			if (scalaire < minscal) {
				minscal = scalaire;
				color = "blanc";
				again = false;
			}
			
			scalaire = 	scalaire(sample, jaune);
			if (scalaire < minscal) {
				minscal = scalaire;
				color = "jaune";
				again = false;
			}
			
			scalaire = 	scalaire(sample, gris);
			if (scalaire < minscal) {
				minscal = scalaire;
				color = "gris";
				again = false;
			}			

			//donneesCapteur.setFloodlight(false);
			
				
		}
		
		this.couleur = color;

	}
		
}
