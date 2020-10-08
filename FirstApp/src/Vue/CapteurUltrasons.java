package Vue;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class CapteurUltrasons extends Capteur implements SensorPort {
	private long distance;
	private EV3UltrasonicSensor donneesCapteur;
	
	public CapteurUltrasons(Perception perception, SensorPort sensorPort) {
		super(perception,sensorPort);
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
	
	public long getDistance() {
		return this.distance;
	}
	
	public void setDistance() {
		donneesCapteur = new EV3UltrasonicSensor((lejos.hardware.port.Port) this.getPort());
		final SampleProvider sp = donneesCapteur.getDistanceMode();
		int distanceValue = 0;
		float [] sample = new float[sp.sampleSize()];
		sp.fetchSample(sample, 0);
        distanceValue = (int)sample[0];
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
