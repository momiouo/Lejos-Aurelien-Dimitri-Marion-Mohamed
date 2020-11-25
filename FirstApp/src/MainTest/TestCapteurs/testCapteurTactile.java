package MainTest.TestCapteurs;

import Robot.Agent;
import lejos.utility.Delay;

public class testCapteurTactile {

	public static void main(String[] args) {
		Agent agent = new Agent();
		boolean loop = true;
		int i = 0;
		while(loop) {
			agent.getCapteurTactile().setPression();
			System.out.println("Pression recup : " + agent.getCapteurTactile().getPression());
			Delay.msDelay(2000);
			i++;
			if (i >= 50) {
				loop = false;
				System.out.print("Fin de la détéction des pressions");
			}
		}
	}

}
