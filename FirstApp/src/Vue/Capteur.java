package Vue;

import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;

public abstract class Capteur implements SensorPort {

	private Perception perception;
	private Port port;
	
	public Capteur(Perception perception, Port sensorPort) {
		this.perception = perception;
		this.port = sensorPort;
	}
	
	public Perception getPerception() {
		return this.perception;
	}
	public Port getPort() {
		return port;
	}
	public void setPort(int numPort) {
		if (numPort == 1) {
			this.port=(Port) S1;
		}
		else if (numPort == 2) {
			this.port=(Port) S2;
		}
		else if (numPort == 3) {
			this.port=(Port) S3;
		}
		else if (numPort == 4) {
			this.port=(Port) S4;
		}
	}
	public void setPerception(Perception perception) {
		this.perception = perception;
	}
	
}
