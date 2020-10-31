package Vue;
import Moteurs.AvancerOuReculer;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class CapteurUltrasons extends Capteur implements SensorPort {
	private float distance;
	private EV3UltrasonicSensor donneesCapteur;
	
	public CapteurUltrasons(Perception perception, Port port) {
		super(perception,port);
		donneesCapteur = new EV3UltrasonicSensor((lejos.hardware.port.Port) this.getPort());
		setDistance();
	}

	public boolean VerifSiObjetDetecteEstUnPalet(AvancerOuReculer moteurdeplacement, CapteurTactile capteurTactile, CapteurCouleur capteurCouleur) {
		System.out.println("VerifSiObjetDetecteEstUnPalet");
		boolean palet = true;
		this.setDistance();
		if (distance < 0.326) {//Si la distance est inférieur c'est un mur ou un robot.
			System.out.println("Distance directe < 0.326");
			palet = false;
		}else {
			System.out.println("Distance > 0.326");
			capteurTactile.setPression();
			capteurCouleur.setCouleur();
			while (distance > 0.600 && !capteurTactile.getPression() && !capteurCouleur.getCouleur().equals("blanc")) {
				System.out.println("Distance > 0.600");
				moteurdeplacement.avancerSynchro();
				this.setDistance();
				capteurTactile.setPression();
				capteurCouleur.setCouleur();
			}
			moteurdeplacement.sarreterSynchro();//On s'arrete
			
			if(capteurTactile.getPression()) {//On a attrapé un palet sans s'en rendre compte
				return palet;
			}
			
			if(capteurCouleur.getCouleur().equals("blanc")) {//On a pas le droit de franchir une ligne blanche
				return false;
			}
			
			this.setDistance();//Car entre temps le robot a avancé
			float distanceAparcourir = (float)((distance-0.326)*1000);//m en mm
			System.out.println("distaparcourir : " + distanceAparcourir);
			moteurdeplacement.avancerSurUneDistance(distanceAparcourir+100);//On avance un peu plus pour arriver en dessous de 0.326
			Delay.msDelay(1000);
			this.setDistance();
			System.out.println("La distance est de " + this.getDistance());
			if (distance < 0.326) {
				palet = false;
			}

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
		System.out.println("murOuRobotDetecte");
		boolean murOuRobot = false;
		this.setDistance();
		if (distance < 0.300) {
			murOuRobot = true;
		}
		return murOuRobot;
	}
}
