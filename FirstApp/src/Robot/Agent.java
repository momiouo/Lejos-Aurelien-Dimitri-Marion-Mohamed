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

/**
 * 
 * La classe Agent contient tous les objets necessaire au fonctionemment du robot durant le projet (Moteurs, Capteurs, ...).
 * 
 * @author LejosTeam
 *
 */
public class Agent {
	
	/**
	 * Objet de la classe Action.
	 */
	private Action action;
	/**
	 * Objet de la classe Perception.
	 */
	private Perception perceptionAct;
	/**
	 * Objet de la classe Perception.
	 */
	private Perception perceptionPrec;
	/**
	 * Objet de la classe AvancerOuReculer.
	 */
	private AvancerOuReculer avancerOuReculer;
	/**
	 * Objet de la classe TournerOuPivoter.
	 */
	private TournerOuPivoter tournerOuPivoter;
	/**
	 * Objet de la classe Pinces.
	 */
	private Pinces pinces;
	/**
	 * Objet de la classe CapteurUltrasons.
	 */
	private CapteurUltrasons capteurUltrasons;
	/**
	 * Objet de la classe CapteurCouleur.
	 */
	private CapteurCouleur capteurCouleur;
	/**
	 * Objet de la classe CapteurTactile.
	 */
	private CapteurTactile capteurTactile;
	/**
	 * Attribut permettant d'enregistrer la position initiale au lancement du programme.
	 */
	private String positionInitiale;
		
	/**
	 * Constructeur de la classe Agent, il crée tout les objets vus en paramètre avec en paramètre leurs ports correspondants.
	 */
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
	
	
	/**
	 * 
	 * Retourne l'attribut action.
	 * 
	 * @return
	 */
	public Action getAction() {
		return action;
	}
	/**
	 * Met à jour l'attribut action.
	 * 
	 * @param action
	 */
	public void setAction(Action action) {
		this.action = action;
	}
	/**
	 * Retourne l'attribut perceptionAct.
	 * 
	 * @return
	 */
	public Perception getPerceptionAct() {
		return perceptionAct;
	}
	/**
	 * Met à jour l'attribut perceptionAct.
	 * 
	 * @param perceptionAct
	 */
	public void setPerceptionAct(Perception perceptionAct) {
		this.perceptionAct = perceptionAct;
	}
	/**
	 * Retourne l'attribut perceptionPrec.
	 * 
	 * @return
	 */
	public Perception getPerceptionPrec() {
		return perceptionPrec;
	}
	/**
	 * Met à jour l'attribut perceptionPrec.
	 * 
	 * @param perceptionPrec
	 */
	public void setPerceptionPrec(Perception perceptionPrec) {
		this.perceptionPrec = perceptionPrec;
	}
	/**
	 * 
	 * Retourne l'attribut avancerOuReculer.
	 * 
	 * @return
	 */
	public AvancerOuReculer getAvancerOuReculer() {
		return avancerOuReculer;
	}
	/**
	 * 
	 * Met à jour l'attribut avancerOuReculer.
	 * 
	 * @param avancerOuReculer
	 */
	public void setAvancerOuReculer(AvancerOuReculer avancerOuReculer) {
		this.avancerOuReculer = avancerOuReculer;
	}
	/**
	 * Retourne l'attribut tournerOuPivoter.
	 * 
	 * @return
	 */
	public TournerOuPivoter getTournerOuPivoter() {
		return tournerOuPivoter;
	}
	/**
	 * Met à jour l'attribut tournerOuPivoter.
	 * 
	 * @param tournerOuPivoter
	 */
	public void setTournerOuPivoter(TournerOuPivoter tournerOuPivoter) {
		this.tournerOuPivoter = tournerOuPivoter;
	}
	/**
	 * Retourne l'attribut capteurUltrasons.
	 * 
	 * @return
	 */
	public CapteurUltrasons getCapteurUltrasons() {
		return capteurUltrasons;
	}
	/**
	 * Met à jour l'attribut capteurUltrasons.
	 * 
	 * @param capteurUltrasons
	 */
	public void setCapteurUltrasons(CapteurUltrasons capteurUltrasons) {
		this.capteurUltrasons = capteurUltrasons;
	}
	/**
	 * Retourne l'attribut capteurCouleur.
	 * 
	 * @return
	 */
	public CapteurCouleur getCapteurCouleur() {
		return capteurCouleur;
	}
	/**
	 * Met à jour l'attribut capteurCouleur.
	 * 
	 * @param capteurCouleur
	 */
	public void setCapteurCouleur(CapteurCouleur capteurCouleur) {
		this.capteurCouleur = capteurCouleur;
	}
	/**
	 * Retourne l'attribut capteurTactile.
	 * 
	 * @return
	 */
	public CapteurTactile getCapteurTactile() {
		return capteurTactile;
	}
	/**
	 * Met à jour l'attribut capteurTactile.
	 * 
	 * @param capteurTactile
	 */
	public void setCapteurTactile(CapteurTactile capteurTactile) {
		this.capteurTactile = capteurTactile;
	}

	/**
	 * Retourne l'attribut pinces.
	 * 
	 * @return
	 */
	public Pinces getPinces() {
		return pinces;
	}

	/**
	 * Met à jour l'attribut pinces.
	 * 
	 * @param pinces
	 */
	public void setPinces(Pinces pinces) {
		this.pinces = pinces;
	}
	
	/**
	 * Met à jour l'attribut positionInitiale.
	 * Méthode pour indiquer la position de notre robot et celle du robot adverse lors du démarrage.
	 * 
	 * @param positionDuRobot
	 */
	public void setPositionInitiale(String positionDuRobot) {// 
		//de la partie
		this.positionInitiale = positionDuRobot;
	}

	/**
	 * Retourne l'attribut positionInitiale.
	 * 
	 * @return
	 */
	public String getPositionInitiale() {
		return positionInitiale;
	}

}
