package Vue;
import Moteurs.AvancerOuReculer;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

/**
 * La classe CapteurUltrasons gère les interactions avec le capteur à ultrasons.
 * 
 * @author LejosTeam
 *
 */
public class CapteurUltrasons extends Capteur implements SensorPort {
	/**
	 * Attribut qui enregistre la dernière distance récupérée par le capteur.
	 */
	private float distance;
	/**
	 * Objet de la classe EV3UltrasonicSensor.
	 */
	private EV3UltrasonicSensor donneesCapteur;
	
	
	/**
	 * @param perception
	 * @param port
	 */
	public CapteurUltrasons(Perception perception, Port port) {
		super(perception,port);
		donneesCapteur = new EV3UltrasonicSensor((lejos.hardware.port.Port) this.getPort());
		setDistance();
	}
	
	
/*
 * 
 */
	/**
	 * Methode qui permet de verifier si l'objet detecté est un palet.
	 * On doît éviter de franchir la ligne blanche pour ne pas récuperer les palets dans les en-buts.
 	 * Prend en compte si un palet est attrapé pendant le deplacement
  	 *	 
	 * @param moteurdeplacement
	 * @param capteurTactile
	 * @param capteurCouleur
	 * @return
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

	/**
	 * Méthode qui permet de récupérer la distance entre le robot (capteur) et un éventuel objet (mur, palet, robot adverse).
	 * 
	 * @return
	 */
	public float getDistance() {
		return this.distance;
	}
	

	/**
	 * Méthode qui permet de changer la valeur de l’attribut distance dans la classe CapteurUltrason et l’objet Perception.
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
	
	/**
	 * Verifie si l'objet detecté est un mur ou un robot.
	 * @return
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

	/**
	 * Vérifie si un objet a été detecté dans une distance donnée.
	 * 
	 * @param distanceparam
	 * @return
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
