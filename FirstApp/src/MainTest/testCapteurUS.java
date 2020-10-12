package MainTest;

import Robot.Agent;

public class testCapteurUS {
	
	public static void main(String[] args) {
		Agent agent = new Agent();
		agent.getCapteurUltrasons().setDistance();
		System.out.println(agent.getCapteurUltrasons().getDistance());
	}
	
	

}
