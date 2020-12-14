package Vue;

import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;

/**
 * La classe Capteur est une classe abstraite qui permet de gérer le fonctionnement des capteurs.
 * 
 * @author LejosTeam
 *
 */
public abstract class Capteur implements SensorPort {

	/**
	 * Objet de la classe Perception.
	 */
	private Perception perception;
	/**
	 * Objet de la classe Port.
	 */
	private Port port;
	
	/**
	 * @param perception
	 * @param port
	 */
	public Capteur(Perception perception, Port port) {
		this.perception = perception;
		this.port = port;
	}

	/**
	 * Retourne l'attribut perception.
	 * 
	 * @return
	 */
	public Perception getPerception() {
		return this.perception;
	}
	
	/**
	 * Retourne l'attribut port.
	 * 
	 * @return
	 */
	public Port getPort() {
		return port;
	}
	
	/**
	 * Met à jour l'attribut port.
	 * 
	 * @param port
	 */
	public void setPort(Port port) {
		this.port = port;
	}
	
	/**
	 * Met à jour l'attribut perception.
	 * 
	 * @param perception
	 */
	public void setPerception(Perception perception) {
		this.perception = perception;
	}
	
}
