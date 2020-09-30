import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;

public class CapteurTactile {
	private boolean pression;
	private EV3TouchSensor donneesCapteur;
	
	public CapteurTactile(Perception perception){
		pression = perception.getPressionCapteurTactile();
	}
	
	public boolean getPression() {
		return this.pression;
	}
	
	public void setPression() {
		donneesCapteur = new EV3TouchSensor(SensorPort.S1);
		final SampleProvider sp = donneesCapteur.getTouchMode();
		boolean touchValue = false;
		float [] sample = new float[sp.sampleSize()];
        sp.fetchSample(sample, 0);
        touchValue = (sample[0]!=0);
        this.pression = touchValue;
	}
	
}
