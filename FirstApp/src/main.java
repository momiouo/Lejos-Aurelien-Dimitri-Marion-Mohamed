import Moteurs.Deplacement;
import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RegulatedMotor leftMotor = Motor.B;
	    RegulatedMotor rightMotor = Motor.C;
		Deplacement deplacement = new Deplacement(leftMotor,rightMotor);
		deplacement.avancerPourUnTempsDonne(3);
		deplacement.pivoterDunDegreDonne(45);
		deplacement.reculerPourUnTemps(3);
	}

}
