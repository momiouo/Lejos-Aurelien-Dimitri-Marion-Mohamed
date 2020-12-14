package Vue;
import lejos.hardware.port.AnalogPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

/**
 * La classe CapteurTactile gère les interactions avec le capteur tactile.
 * 
 * @author LejosTeam
 *
 */
public class CapteurTactile extends Capteur {
	/**
	 * Attribut qui permet d'enregistrer s'il y a eu pression sur le capteur tactile.
	 */
	private boolean pression;
	/**
	 * Objet de la classe EV3TouchSensor.
	 */
	private EV3TouchSensor donneesCapteur;

	/**
	 * @param perception
	 * @param port
	 */
	public CapteurTactile(Perception perception, Port port){
		super(perception, port);
		donneesCapteur = new EV3TouchSensor((lejos.hardware.port.Port) this.getPort());
		setPression();
	}
	
	
	/**
	 * Retourne l’attribut pression.
	 * 
	 * @return
	 */
	public boolean getPression() {
		return this.pression;
	}


	/**
	 * Met à jour l'attribut pression dans la classe CapteurTactile et l'objet Perception.
	 */
	public void setPression() {
		final SampleProvider sp = donneesCapteur.getTouchMode();
		boolean touchValue = false;
		float [] sample = new float[sp.sampleSize()];
        sp.fetchSample(sample, 0);
        touchValue = (sample[0]!=0);
        this.pression = touchValue;
        super.getPerception().pressionCapteurTactile = touchValue;
	}
	
}
