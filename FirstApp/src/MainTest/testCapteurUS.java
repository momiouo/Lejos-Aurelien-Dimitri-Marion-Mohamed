package MainTest;

import Robot.Agent;
import Vue.CapteurUltrasons;
import Vue.Perception;
import lejos.utility.Delay;

public class testCapteurUS {
	
	public static void main(String[] args) {
		Agent agent = new Agent();
		boolean loop = true;
		int i = 0;
		while(loop) {
			agent.getCapteurUltrasons().setDistance();
			System.out.println("Distance recup : " + agent.getCapteurUltrasons().getDistance());
			Delay.msDelay(2000);
			i++;
			if (i >= 50) {
				loop = false;
				System.out.print("Fin de la détéction des distances");
			}
		}
	}
	
	

}
