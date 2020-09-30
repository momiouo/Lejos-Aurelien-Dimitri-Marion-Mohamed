package Vue;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class CapteurUltrasons implements SensorPort {
	private long distance;
	private EV3UltrasonicSensor donneesCapteur;
	private SensorPort port;
	
	public CapteurUltrasons(Perception perception) {
		this.distance = perception.getDistanceCapteurUltrasons();
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
	}
	
	public SensorPort getPort() {
		return this.port;
	}
	
	public void setPort(int numPort) {
		if (numPort == 1) {
			this.port=(SensorPort) S1;
		}
		else if (numPort == 2) {
			this.port=(SensorPort) S2;
		}
		else if (numPort == 3) {
			this.port=(SensorPort) S3;
		}
		else if (numPort == 4) {
			this.port=(SensorPort) S4;
		}
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
