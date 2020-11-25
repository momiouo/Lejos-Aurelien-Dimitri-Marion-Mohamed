package MainTest.TestMoteurs;

import Robot.Agent;
import lejos.utility.Delay;

public class testAvancerJusquaLigneBlanche {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Agent agent = new Agent();
		agent.getAvancerOuReculer().avancerJusquaUneLigne(agent.getCapteurCouleur(), "blanc");
		
	}

}
