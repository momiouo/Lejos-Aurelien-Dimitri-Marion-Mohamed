package Vue;
import Moteurs.AvancerOuReculer;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class CapteurUltrasons extends Capteur implements SensorPort {
	private float distance;
	private EV3UltrasonicSensor donneesCapteur;
	
	public CapteurUltrasons(Perception perception, Port port) {
		super(perception,port);
		donneesCapteur = new EV3UltrasonicSensor((lejos.hardware.port.Port) this.getPort());
		setDistance();
	}

	public boolean VerifSiObjetDetecteEstUnPalet(AvancerOuReculer moteurdeplacement) {
		boolean palet = true;
		while (distance > 0.350) {//Avancer jusqu'a la limite
			moteurdeplacement.avancerSynchro();
			this.setDistance();
		}
		moteurdeplacement.sarreterSynchro();//On s'arrete
		moteurdeplacement.avancerPourUnTemps((float) 0.5);//On avance legerement
		this.setDistance();
		if (distance < 0.326) {//Si la distance est inférieur c'est un mur ou un robot
			palet = false;
		}
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
        //System.out.println("Valeur recup par le capteur ultrason : " + distanceValue);
		this.distance = distanceValue; 
		//Modification de l'attribut DistanceCapteurUltrasons de l'objet Perception passé en paramètre du constructeur
		this.getPerception().distanceCapteurUltrasons = distanceValue;
	}
	
	public boolean murOuRobotDetecte() {
		boolean murOuRobot = false;
		this.setDistance();
		if (distance < 0.300) {
			murOuRobot = true;
		}
		return murOuRobot;
	}
}
