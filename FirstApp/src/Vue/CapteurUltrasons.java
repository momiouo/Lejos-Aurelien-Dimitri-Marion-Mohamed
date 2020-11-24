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
	
	// Constructeur:
	
	public CapteurUltrasons(Perception perception, Port port) {
		super(perception,port);
		donneesCapteur = new EV3UltrasonicSensor((lejos.hardware.port.Port) this.getPort());
		setDistance();
	}
	
	// Methodes :
	
/*
 * Methode qui permet de verifier si l'objet detecter est un palet
 * On a pas le droit de franchir la ligne blanche afin de ne pas recuperer les palais deja deposer ou dans l'enbut adverse
 * Prends en compte si un palet est attrapé pendant le deplacement
 */
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
/*
 * Methode qui permet de récupérer la distance entre le robot (capteur) et un éventuel objet (mur, palet, robot adverse)
 */
	public float getDistance() {
		return this.distance;
	}
	
/*
 * Methode qui permet de changer la valeur de l’attribut distance dans la classe CapteurUltrason et l’objet Perception.
 */
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
	
/*
 * Verifier si l'objet detecter est un mur ou un robot
 */
	public boolean murOuRobotDetecte() {
		//System.out.println("murOuRobotDetecte");
		boolean murOuRobot = false;
		this.setDistance();
		if (distance < 0.250) {
			murOuRobot = true;
		}
		return murOuRobot;
	}
	
/*
 * Verifier si l'objet detecter sur une distance est un mur ou un robot
 */
	public boolean murOuRobotDetecteAvecDistance(float distanceparam) {
		//System.out.println("murOuRobotDetecte");
		boolean murOuRobot = false;
		this.setDistance();
		if (distance < distanceparam) {
			murOuRobot = true;
		}
		return murOuRobot;
	}
}
