package Controleur;

import Vue.Perception;

public class Action {
	
	private Perception perceptionAct;
	private Perception perceptionPrec;
	
	public Action() {
		
	}
	
	public Action(Perception p1, Perception p2) {
		this.setPerceptionAct(p1);
		this.setPerceptionPrec(p2);
	}
	
	
	public Perception getPerceptionPrec() {
		return perceptionPrec;
	}
	public void setPerceptionPrec(Perception perceptionPrec) {
		this.perceptionPrec = perceptionPrec;
	}
	public Perception getPerceptionAct() {
		return perceptionAct;
	}
	public void setPerceptionAct(Perception perceptionAct) {
		this.perceptionAct = perceptionAct;
	}
	
	public void verifChangementAttributPerception() {
		
	}
	
	public void detecterAutourDuRobot() {
		
	}

}
