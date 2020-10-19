package MainTest;

import Robot.Agent;
import lejos.utility.Delay;

public class testCapteurCouleur {

	public static void main(String[] args) {
		Agent agent = new Agent();
		//agent.getCapteurCouleur().calibrer();
		
		
		boolean loop = true;
		int i = 0;
		while(loop) {
			agent.getCapteurCouleur().setCouleur();
			System.out.println("Couleur recup : " + agent.getCapteurCouleur().getCouleur());
			Delay.msDelay(2000);
			i++;
			if (i >= 50) {
				loop = false;
				System.out.print("Fin de la détéction des couleurs");
			}
		}
	}

}
