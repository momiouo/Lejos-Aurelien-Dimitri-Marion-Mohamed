package Exemples;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.Font;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

//Doc : http://www.lejos.org/ev3/docs/lejos/hardware/sensor/EV3ColorSensor.html
public class CapteurCouleurExemple {

	//Robot Configuration
		private static EV3ColorSensor color1 = new EV3ColorSensor(SensorPort.S4);
		
		//Configuration
		private static int HALF_SECOND = 500;
		
		public static void main(String[] args) {

			
			//Red Mode (Mesure the intensity of a reflected red light)
			System.out.println("Switching to Red Mode");
			SampleProvider sp = color1.getRedMode();
			
			int sampleSize = sp.sampleSize();
			float[] sample = new float[sampleSize];
/*
	        // Takes some samples and prints them
	        for (int i = 0; i < 10; i++) {
	        	sp.fetchSample(sample, 0);
				System.out.println("N=" + i + " Sample=" +  (int)sample[0]);
	            
	            Delay.msDelay(HALF_SECOND);
	        }
*/
	        //Color ID (Mesure the color id of a surface)
			System.out.println("Switching to Color ID Mode");
			sp = color1.getColorIDMode();
			
			sampleSize = sp.sampleSize();
			sample = new float[sampleSize];

	        // Takes some samples and prints them
	        for (int i = 0; i < 100; i++) {
	        	sp.fetchSample(sample, 0);
				System.out.println("N=" + i + " Sample={}" +  (int)sample[0]);
	            
	            Delay.msDelay(3000);
	        }
/*
	        //Ambient Mode (Mesure the ambiant light level)
			System.out.println("Switching to Ambient Mode");
			sp = color1.getAmbientMode();
			
			sampleSize = sp.sampleSize();
			sample = new float[sampleSize];

	        // Takes some samples and prints them
	        for (int i = 0; i < 10; i++) {
	        	sp.fetchSample(sample, 0);
				System.out.println("N=" + i + " Sample={}" +  (int)sample[0]);

	            Delay.msDelay(HALF_SECOND);
	        }
			
			//RGB Mesure the Red-Green-Blue color
			System.out.println("Switching to RGB Mode");
			sp = color1.getRGBMode();
			
			sampleSize = sp.sampleSize();
			sample = new float[sampleSize];

	        // Takes some samples and prints them
	        for (int i = 0; i < 10; i++) {
	        	sp.fetchSample(sample, 0);
				System.out.println("N=" + i + " Sample={}" + (int)sample[0]);
				System.out.println("N=" + i + " Sample={}" + (int)sample[1]);
				System.out.println("N=" + i + " Sample={}" + (int)sample[2]);

	            Delay.msDelay(HALF_SECOND);
	        }*/
		}
		
}
