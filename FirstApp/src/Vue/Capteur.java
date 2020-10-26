package Vue;

import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;

public abstract class Capteur implements SensorPort {

	private Perception perception;
	private Port port;
	
	public Capteur(Perception perception, Port port) {
		this.perception = perception;
		this.port = port;
	}
	
	public Perception getPerception() {
		return this.perception;
	}
	
	public Port getPort() {
		return port;
	}
	
	public void setPort(Port port) {
		this.port = port;
	}
	
	public void setPerception(Perception perception) {
		this.perception = perception;
	}
	
}
