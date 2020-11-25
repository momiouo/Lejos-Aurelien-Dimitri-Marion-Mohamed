package Robot;
import Controleur.Action;
import Vue.*;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.robotics.RegulatedMotor;
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
	private String positionInitiale;
	
	// Constructeur:
	
	public Agent() {
		System.out.println("Création d'un Agent ... ... ...");
		this.perceptionAct = new Perception(this);
		this.perceptionPrec = new Perception(this);
		this.action = new Action(perceptionAct, perceptionPrec, this);
		//On initialise les differents moteurs et capteurs pour le robot avec leurs ports correspondants
		EV3LargeRegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.C);
		EV3LargeRegulatedMotor motorC = new EV3LargeRegulatedMotor(MotorPort.B);
		this.avancerOuReculer = new AvancerOuReculer(motorB,motorC);
		this.tournerOuPivoter = new TournerOuPivoter(avancerOuReculer.getLeftMotor(),avancerOuReculer.getRightMotor(), action);
		this.pinces = new Pinces(new EV3LargeRegulatedMotor(MotorPort.D));
		this.capteurUltrasons = new CapteurUltrasons(perceptionAct, LocalEV3.get().getPort("S2"));
		this.capteurCouleur = new CapteurCouleur(perceptionAct, LocalEV3.get().getPort("S3"));
		this.capteurTactile = new CapteurTactile(perceptionAct, LocalEV3.get().getPort("S1"));
		System.out.println("Fin de la création de l'Agent.");
	}
	
	//Méthodes:
	
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
	
	public void setPositionInitiale(String positionDuRobot) {//Methode pour indiquer la position de notre robot et celle du robot adverse lors du demarrage 
		//de la partie
		this.positionInitiale = positionDuRobot;
	}

	public String getPositionInitiale() {
		return positionInitiale;
	}

}
