import lejos.hardware.sensor.EV3TouchSensor;

public class CapteurTactile {
	private boolean pression;
	
	public CapteurTactile(Perception perception){
		pression = perception.getPressionCapteurTactile();
	}
	
	public boolean getPression() {
		return this.pression;
	}
	
	public void setPression(boolean pression) {
		this.pression = pression;
	}
	
}
