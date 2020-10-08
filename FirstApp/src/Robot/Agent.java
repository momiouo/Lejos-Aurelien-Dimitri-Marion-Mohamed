package Robot;
import Controleur.Action;
import Vue.*;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import Moteurs.*;

public class Agent {
	
	private Action action;
	private Perception perceptionAct;
	private Perception perceptionPrec;
	private AvancerOuReculer avancerOuReculer;
	private TournerOuPivoter tournerOuPivoter;
	private Pinces pinces;
	private CapteurUltrasons capteurUltrasons;
	private CapteurCouleur capteurCouleur;
	private CapteurTactile capteurTactile;
	
	public Agent() {
		this.perceptionAct = new Perception(this);
		this.perceptionPrec = new Perception(this); // Pour la classe Action
		this.action = new Action(perceptionAct, perceptionPrec, this); //Pas sur que ça marche de mettre this alors qu'il est pas encore créer
		//Mettre les bons ports pour les capteurs et les moteurs :
		this.avancerOuReculer = new AvancerOuReculer(Motor.B,Motor.C);
		this.tournerOuPivoter = new TournerOuPivoter(Motor.B,Motor.C);
		this.pinces = new Pinces(Motor.A);
		this.capteurUltrasons = new CapteurUltrasons(perceptionAct, (SensorPort) SensorPort.S1);
		this.capteurCouleur = new CapteurCouleur(perceptionAct, (SensorPort) SensorPort.S2);
		this.capteurTactile = new CapteurTactile(perceptionAct, (SensorPort) SensorPort.S3);
	}
	
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	public Perception getPerceptionAct() {
		return perceptionAct;
	}
	public void setPerceptionAct(Perception perceptionAct) {
		this.perceptionAct = perceptionAct;
	}
	public Perception getPerceptionPrec() {
		return perceptionPrec;
	}
	public void setPerceptionPrec(Perception perceptionPrec) {
		this.perceptionPrec = perceptionPrec;
	}
	public AvancerOuReculer getAvancerOuReculer() {
		return avancerOuReculer;
	}
	public void setAvancerOuReculer(AvancerOuReculer avancerOuReculer) {
		this.avancerOuReculer = avancerOuReculer;
	}
	public TournerOuPivoter getTournerOuPivoter() {
		return tournerOuPivoter;
	}
	public void setTournerOuPivoter(TournerOuPivoter tournerOuPivoter) {
		this.tournerOuPivoter = tournerOuPivoter;
	}
	public CapteurUltrasons getCapteurUltrasons() {
		return capteurUltrasons;
	}
	public void setCapteurUltrasons(CapteurUltrasons capteurUltrasons) {
		this.capteurUltrasons = capteurUltrasons;
	}
	public CapteurCouleur getCapteurCouleur() {
		return capteurCouleur;
	}
	public void setCapteurCouleur(CapteurCouleur capteurCouleur) {
		this.capteurCouleur = capteurCouleur;
	}
	public CapteurTactile getCapteurTactile() {
		return capteurTactile;
	}
	public void setCapteurTactile(CapteurTactile capteurTactile) {
		this.capteurTactile = capteurTactile;
	}

	public Pinces getPinces() {
		return pinces;
	}

	public void setPinces(Pinces pinces) {
		this.pinces = pinces;
	}
	
	
	

}
