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
	private Color couleur;
	private EV3ColorSensor donneesCapteur;
	private static float[] blue;
	private static float[] red;
	private static float[] green;
	private static float[] black;

	
	
	public CapteurCouleur(Perception perception, SensorPort sensorPort) {
		super(perception, sensorPort);
		setCouleur();
	}
	
	public Color getCouleur() {
		return this.couleur;
	}
	
	public void calibrer() {		
		Port port = LocalEV3.get().getPort("S4");
		SampleProvider average = new MeanFilter(donneesCapteur.getRGBMode(), 1);
		donneesCapteur.setFloodlight(Color.WHITE);
		
		System.out.println("Press enter to calibrate blue...");
		Button.ENTER.waitForPressAndRelease();		
		average.fetchSample(blue, 0);
		
		
		System.out.println("Press enter to calibrate red...");
		Button.ENTER.waitForPressAndRelease();
		average.fetchSample(red, 0);
		
		System.out.println("Press enter to calibrate green...");
		Button.ENTER.waitForPressAndRelease();
		average.fetchSample(green, 0);

		System.out.println("Press enter to calibrate black...");
		Button.ENTER.waitForPressAndRelease();
		average.fetchSample(black, 0);
		System.out.println("Black calibrated");
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
	
	public static double scalaire(float[] v1, float[] v2) {
		return Math.sqrt (Math.pow(v1[0] - v2[0], 2.0) +
				Math.pow(v1[1] - v2[1], 2.0) +
				Math.pow(v1[2] - v2[2], 2.0));
	}
	
	public void setCouleur() {
		SampleProvider average = new MeanFilter(donneesCapteur.getRGBMode(), 1);
		boolean again = true;
		while (again) {
			float[] sample = new float[average.sampleSize()];
			System.out.println("\nPress enter to detect a color...");
			Button.ENTER.waitForPressAndRelease();
			average.fetchSample(sample, 0);
			double minscal = Double.MAX_VALUE;
			String color = "";
			
			double scalaire = TestColor.scalaire(sample, blue);
			//Button.ENTER.waitForPressAndRelease();
			//System.out.println(scalaire);
			
			if (scalaire < minscal) {
				minscal = scalaire;
				color = "blue";
			}
			
			scalaire = TestColor.scalaire(sample, red);
			//System.out.println(scalaire);
			//Button.ENTER.waitForPressAndRelease();
			if (scalaire < minscal) {
				minscal = scalaire;
				color = "red";
			}
			
			scalaire = TestColor.scalaire(sample, green);
			//System.out.println(scalaire);
			//Button.ENTER.waitForPressAndRelease();
			if (scalaire < minscal) {
				minscal = scalaire;
				color = "green";
			}
			
			scalaire = TestColor.scalaire(sample, black);
			//System.out.println(scalaire);
			//Button.ENTER.waitForPressAndRelease();
			if (scalaire < minscal) {
				minscal = scalaire;
				color = "black";
			}
			
			System.out.println("The color is " + color + " \n");
			System.out.println("Press ENTER to continue \n");
			System.out.println("ESCAPE to exit");
			Button.waitForAnyPress();
			if(Button.ESCAPE.isDown()) {
				donneesCapteur.setFloodlight(false);
				again = false;
			}
		}
		/*
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
		this.getPerception().CapteurCouleur = couleurDetectee;*/
	}
}
