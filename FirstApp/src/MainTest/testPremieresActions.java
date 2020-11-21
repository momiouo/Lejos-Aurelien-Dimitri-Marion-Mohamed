package MainTest;

import Robot.Agent;

public class testPremieresActions {

	public static void main(String[] args) {
		Agent agent = new Agent();
		//Bug refermer les pinces
				//agent.getPinces().setPincesOuvertes(true);
				//agent.getPinces().fermeture();
		
		agent.getAction().premieresActions();
	}

}
