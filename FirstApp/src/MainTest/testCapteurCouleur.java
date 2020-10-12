package MainTest;

import Robot.Agent;

public class testCapteurCouleur {

	public static void main(String[] args) {
		Agent agent = new Agent();
		agent.getCapteurCouleur().calibrer();
		agent.getCapteurCouleur().setCouleur();
		System.out.println(agent.getCapteurCouleur().getCouleur());
	}

}
