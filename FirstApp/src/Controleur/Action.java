package Controleur;

import Moteurs.AvancerOuReculer;
import Moteurs.Pinces;
import Moteurs.TournerOuPivoter;
import Robot.Agent;
import Vue.CapteurUltrasons;
import Vue.Perception;
import lejos.hardware.motor.Motor;
import lejos.robotics.Color;

public class Action {
	
	private Agent agent;
	private Perception perceptionAct;
	private Perception perceptionPrec;
	
	public Action(Perception p1, Perception p2, Agent agent) {
		this.setPerceptionAct(p1);
		this.setPerceptionPrec(p2);
		this.setAgent(agent);
	}
	
	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
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
	
	public void verifChangementAttributPerception() {// Methode complexe a faire plus tard
		/*
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
		}*/
		
	}
	
	public void detecterAutourDuRobot(CapteurUltrasons capteurUltrasons, TournerOuPivoter tournerOuPivoter) {
		tournerOuPivoter.pivoterJusquaDetectionDunPalet(capteurUltrasons);
	}
	
	public void init() {//ouvre les pinces du robot, remet à O les tachometres, perceptionprec = perceptionact
		this.perceptionPrec = this.perceptionAct;
		agent.getPinces().ouverture();
		agent.getAvancerOuReculer().resetTachoMetre();
	}
	
	public void premieresActions() {
		boolean loop = true;
		boolean start = true;
		boolean paletattrape = false;
		while(loop) {
			agent.getPerceptionAct().initCapteurs();//On mets toujours à jour les valeurs des capteurs
			if(start) {
				this.init();//On ouvre les pinces (position initiale)
				agent.getAvancerOuReculer().avancerPourUnTemps(3);//On avance
				start = false;
			}
			if (robotEstBloque()){
				System.out.println("Je suis bloqué ...");
				loop = false;
			}
			if(!paletattrape && agent.getPerceptionAct().getPressionCapteurTactile() == true) {//On a attrapé le palet
				agent.getPinces().fermeture();//On ferme les pinces
				paletattrape = true;
				agent.getTournerOuPivoter().tournerSurUnTempsEtUneDirectionVague(3, 1);//On evite le second palet
				agent.getTournerOuPivoter().pivoterDunDegreDonne(30);//On s'aligne
			}
			if(agent.getPerceptionAct().getCouleurCapteurCouleur().getColor() == Color.WHITE) {//On a atteint la ligne
				this.deposerLePalet();
				loop = false;
				System.out.println("Fin de la fonction premieresActions");
			}else if(paletattrape) {
				agent.getAvancerOuReculer().avancerPourUnTemps(3);//On avance tant que la couleur n'est pas white
			}
		}
	}
	
	public void allerVersLenButAdverse() {
		//On oriente notre robot vers l'en but adverse en regardant de combien on a deja pivoter de degré notre robot.
		
	}
	
	public void enregistrerPositionRobot() {
		//A chaque rotation on enregistre les degrees
		//A chaque passage sur une ligne de couleur on enregistre la couleur si elle nous intérresse (Notre camp + couleur blanche)
	}
	
	public boolean robotEstBloque() {
		//Return vrai si le capteur de distance donne une petite valeur
		return false;
	}
	
	public void reagirRobotBloque() {
		//On recule
		//On pivote pour recup des distances et voir dans quelle direction on peut aller
	}
	
	public void deposerLePalet() {
		//On avance un peu
		agent.getAvancerOuReculer().avancerPourUnTemps(2);
		//On ouvre les pinces
		agent.getPinces().ouverture();
		//On recule
		agent.getAvancerOuReculer().reculerPourUnTemps(2);
		//on pivote de 50 degrees environ
		agent.getTournerOuPivoter().pivoterDunDegreDonne(50);
		//On se stop si c'est la fonction premieresAction qui a déclenché cette fonction sinon on appel la fonction detecterAutourduRobot pour un autre palet
	}




}
