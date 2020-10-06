package Controleur;

import Moteurs.AvancerOuReculer;
import Moteurs.Pinces;
import Moteurs.TournerOuPivoter;
import Vue.CapteurUltrasons;
import Vue.Perception;
import lejos.hardware.motor.Motor;
import lejos.robotics.Color;

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
		//On verifie si la pression du capteur tactile a changer.
		if(this.perceptionAct.getPressionCapteurTactile() != this.perceptionPrec.getPressionCapteurTactile()) {
			Boolean pressionCourante = this.getPerceptionAct().getPressionCapteurTactile();
			if (pressionCourante) {//Il y a une pression
				Pinces pinces = new Pinces(Motor.A);
				pinces.fermeture();
			}else {//On a plus de pression
				
			}
		}
		//On verifie si la couleur détectée a changer.
		if (this.perceptionAct.getCouleurCapteurCouleur() != this.perceptionPrec.getCouleurCapteurCouleur()) {
			Color couleurCourante = this.getPerceptionAct().getCouleurCapteurCouleur();
			//Lancer des fonctions afin de localiser le robot en fonction des couleurs
			if (couleurCourante.getColor() == Color.WHITE) {
				deposerLePalet();
			}
		}
		
	}
	
	public void detecterAutourDuRobot(CapteurUltrasons capteurUltrasons) {
		TournerOuPivoter tournerOuPivoter = new TournerOuPivoter(Motor.B,Motor.C);
		tournerOuPivoter.pivoterJusquaDetectionDunPalet(capteurUltrasons);
	}
	
	public void init() {//Initialise les ports pour les differents capteurs et moteurs (peut etre une classe agent qui créer toutes les classes avec leurs ports 
		//Respectif) et ouvre les pinces du robot ainsi que remet à O les tachometres
		
	}
	
	public void premieresActions(AvancerOuReculer avancerOuReculer,TournerOuPivoter tounerOuPivoter,Pinces pinces) {
		boolean loop = true;
		while(loop) {
			if (RobotEstBloque()){
				loop = false;
			}
			this.verifChangementAttributPerception();//On verifie si le capteur tact est pressé on ferme les pinces et si la ligne blanche est traversé
			//On depose le palet si la ligne est la ligne blanche adverse !
			avancerOuReculer.avancerPourUnTemps(3);
		}
		
	}
	
	public void allerVersLenButAdverse() {
		//On oriente notre robot vers l'en but adverse en regardant de combien on a deja pivoter de degré notre robot.
		
	}
	
	public void enregistrerPositionRobot() {
		//A chaque rotation on enregistre les degrees
		//A chaque passage sur une ligne de couleur on enregistre la couleur si elle nous intérresse (Notre camp + couleur blanche)
	}
	
	public void reagirRobotBloque() {
		//On recule
		//On pivote pour recup des distances et voir dans quelle direction on peut aller
	}
	
	public void deposerLePalet(AvancerOuReculer avancerOuReculer,TournerOuPivoter tounerOuPivoter,Pinces pinces) {
		//On avance un peu
		avancerOuReculer.avancerPourUnTemps(2);
		//On ouvre les pinces
		pinces.ouverture();
		//On recule
		avancerOuReculer.reculerPourUnTemps(2);
		//on pivote de 50 degrees environ
		tounerOuPivoter.pivoterDunDegreDonne(50);
		//On se stop si c'est la fonction premieresAction qui a déclenché cette fonction sinon on appel la fonction detecterAutourduRobot pour un autre palet
	}

}
