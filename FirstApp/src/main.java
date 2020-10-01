import Moteurs.AvancerOuReculer;
import Moteurs.Deplacement;
import Moteurs.TournerOuPivoter;
import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;

public class main {

	public static void main(String[] args) {
		//Les moteurs des roues doivent etre branchés sur le port B et C
		RegulatedMotor leftMotor = Motor.B;
	    RegulatedMotor rightMotor = Motor.C;
		AvancerOuReculer avanceroureculer = new AvancerOuReculer(leftMotor,rightMotor);
		TournerOuPivoter tourneroupivoter = new TournerOuPivoter(leftMotor,rightMotor);
		//Verifier et tester si tout ne se fait pas en meme temps
		avanceroureculer.avancerPourUnTemps(3);//On avance 3 secondes
		tourneroupivoter.pivoterDunDegreDonne(45);//On pivote de 45 degre
		avanceroureculer.reculerPourUnTemps(3);//On recule 3 secondes
	}

}
