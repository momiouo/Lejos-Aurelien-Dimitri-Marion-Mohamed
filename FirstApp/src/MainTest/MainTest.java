package MainTest;

import Robot.Agent;
import lejos.hardware.Button;
import lejos.utility.Delay;

public class MainTest {

	public static void main(String[] args) {	
		
		System.out.println("Press enter to run MainTest...");
		Button.ENTER.waitForPressAndRelease();
		
		Agent agent = new Agent();
		
<<<<<<< HEAD
		
		//Bug refermer les pinces
		//agent.getPinces().setPincesOuvertes(true);
		//agent.getPinces().fermeture();
		
		agent.getAction().detecterAutourDuRobot(true,false);//True car start puis false car pas besoin de decalée
		
		//Test 1
		//agent.getTournerOuPivoter().pivoterEtDetecterSurUnDegreDonne(agent, 180);
		
		//Test 2
		//boolean test = agent.getCapteurUltrasons().VerifSiObjetDetecteEstUnPalet(agent.getAvancerOuReculer(),agent.getCapteurTactile(),agent.getCapteurCouleur());
		//System.out.println("true = palet mur sinon => " + test);
		
		Delay.msDelay(20000);
		
			
=======
		//Bug refermer les pinces
		//agent.getPinces().setPincesOuvertes(true);
		//agent.getPinces().fermeture();
		
		agent.getAction().detecterAutourDuRobot(true,false);
		
		System.out.println("Fin du test");
		Delay.msDelay(20000);
>>>>>>> branch 'master' of https://github.com/Momiouo/Lejos.git
	}

}
