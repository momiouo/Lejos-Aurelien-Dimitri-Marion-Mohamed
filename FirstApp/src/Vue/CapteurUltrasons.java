package Vue;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class CapteurUltrasons extends Capteur implements SensorPort {
	private float distance;
	private EV3UltrasonicSensor donneesCapteur;
	
	public CapteurUltrasons(Perception perception, Port sensorPort) {
		super(perception,sensorPort);
		donneesCapteur = new EV3UltrasonicSensor((lejos.hardware.port.Port) this.getPort());
		setDistance();
	}

	public boolean VerifSiObjetDetecteEstUnPalet() {
		boolean palet = true;
		//avancer
		/*if (distance < valeurOuOnVoitPlusLePalet) {
			palet = false;
		}*/
		return palet;
	}
	
	public float getDistance() {
		return this.distance;
	}
	
	public void setDistance() {
		final SampleProvider sp = donneesCapteur.getDistanceMode();
		float distanceValue = 0;
		float [] sample = new float[sp.sampleSize()];
		sp.fetchSample(sample, 0);
        distanceValue = sample[0];//0.326
        System.out.println("Valeur recup par le capteur ultrason : " + distanceValue);
		this.distance = distanceValue; 
		//Modification de l'attribut DistanceCapteurUltrasons de l'objet Perception passé en paramètre du constructeur
		this.getPerception().distanceCapteurUltrasons = distanceValue;
	}
	
	public boolean murOuRobotDetecte() {
		boolean murOuRobot = false;
		//avancer
		/*if (distance < valeurOuOnVoitPlusLePalet) {
			murOuRobot = true;
		}*/
		return murOuRobot;
	}
}
