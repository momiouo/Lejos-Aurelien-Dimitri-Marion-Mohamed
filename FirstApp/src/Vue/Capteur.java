package Vue;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;

public class Capteur implements SensorPort {

	private EV3TouchSensor donneesCapteur;
	private Perception perception;
	private SensorPort port;
	
	public Capteur(Perception perception, SensorPort sensorPort) {
		this.perception = perception;
		this.port = sensorPort;
	}
	
	public Perception getPerception() {
		return this.perception;
	}
	public SensorPort getPort() {
		return port;
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
	public void setPerception(Perception perception) {
		this.perception = perception;
	}
	
}
