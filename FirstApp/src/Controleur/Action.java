package Controleur;

import Moteurs.AvancerOuReculer;
import Moteurs.Pinces;
import Moteurs.TournerOuPivoter;
import Robot.Agent;
import Vue.CapteurUltrasons;
import Vue.Perception;
import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.robotics.Color;

public class Action {
	
	private Agent agent;
	private Perception perceptionAct;
	private Perception perceptionPrec;
	private int historiqueDegres;
	
	public Action(Perception p1, Perception p2, Agent agent) {
		this.setPerceptionAct(p1);
		this.setPerceptionPrec(p2);
		this.setAgent(agent);
		this.setHistoriqueDegres(0);
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
	public int getHistoriqueDegres() {
		return historiqueDegres;
	}

	public void setHistoriqueDegres(int historiqueDegres) {
		this.historiqueDegres = historiqueDegres;
	}

	public void verifChangementAttributPerception() {// Fonction qui tourne en boucle pour verification
		this.perceptionPrec = this.perceptionAct; // Sauvegarde de l'ancienne perception
		this.perceptionAct.initCapteurs(); // initialisation de la nouvelle perception
		Boolean pressionCourante = false;

		//On verifie si la pression du capteur tactile a changer.
		if(this.perceptionAct.getPressionCapteurTactile() != this.perceptionPrec.getPressionCapteurTactile()) {
			pressionCourante = this.getPerceptionAct().getPressionCapteurTactile();
			if (pressionCourante) {
				agent.getAvancerOuReculer().sarreter();
				agent.getPinces().fermeture();
				agent.getAction().allerVersLenButAdverse();
				
			}
		}
	/*
		//On verifie si la couleur détectée a changer.
		if (this.perceptionAct.getCouleurCapteurCouleur() != this.perceptionPrec.getCouleurCapteurCouleur()) {
			Color couleurCourante = this.getPerceptionAct().getCouleurCapteurCouleur();
			//Lancer des fonctions afin de localiser le robot en fonction des couleurs
			if (couleurCourante.getColor() == Color.WHITE && pressionCourante) {
				deposerLePalet();
			}

			//On verifie s'il y a un obstacle
			if (this.perceptionAct.getDistanceCapteurUltrasons() != this.perceptionPrec.getDistanceCapteurUltrasons()) {
				if (agent.getCapteurUltrasons().VerifSiObjetDetecteEstUnPalet(agent.getAvancerOuReculer()) == true) {
					agent.getPinces().ouverture();
					while (agent.getCapteurTactile().getPression() == false){//attendre qu'on touche
						agent.getAvancerOuReculer().avancer();
					}
					agent.getPinces().fermeture();
					
					//aller a l'en but
					agent.getAction().allerVersLenButAdverse();
					
				}
				else {
					this.robotEstBloque();
				}
			}
		}*/
	}
	
	public void detecterAutourDuRobot() {
		//Fonction qui aligne le robot vers un objet
		agent.getTournerOuPivoter().pivoterEtDetecterSurUnDegreDonne(agent, 180);
		//fonction qui avance le robot pour verif si c'est un palet
		if(agent.getCapteurUltrasons().VerifSiObjetDetecteEstUnPalet(agent.getAvancerOuReculer())) {
			//On a un palet en face de soit, on avance juste pour declenche la pression capteur tactile dans la fonction verifperceptionchangement
			agent.getAvancerOuReculer().avancer();
		}else {
			//C'est un mur ou robot adverse
			detecterAutourDuRobot();
		}
	}
	
	public void init() {//ouvre les pinces du robot, remet à O les tachometres, perceptionprec = perceptionact
		this.perceptionPrec = this.perceptionAct;
		agent.getPinces().ouverture();
		agent.getAvancerOuReculer().resetTachoMetre();
	}
	
	public void premieresActions() {
		boolean loop = true;
		System.out.println("Press enter to run premieresActions...");
		Button.ENTER.waitForPressAndRelease();
		while(loop) {
			agent.getPerceptionAct().initCapteurs();//On mets à jour les valeurs des capteurs
			if (robotEstBloque()){
				System.out.println("Je suis bloqué ... appeler la fonction reagirRobotBloque");
				loop = false;
			}else {
				this.init();//On ouvre les pinces (position initiale)
				agent.getAvancerOuReculer().avancerTqCapteurPressionPasEnfonce(agent.getCapteurTactile());//On avance tq on a pas toucher le palet
				agent.getPinces().fermeture();//On ferme les pinces
				
				agent.getTournerOuPivoter().pivoterDunDegreDonne(-45);//On evite les autres palets
				agent.getAvancerOuReculer().avancerPourUnTemps(1);
				agent.getTournerOuPivoter().pivoterDunDegreDonne(45);//On re s'aligne
				
				agent.getAvancerOuReculer().avancerJusquaUneLigne(agent.getCapteurCouleur(), "blanc");//On avance tant que la couleur n'est pas blanche
				this.deposerLePalet();//On lance les actions pour déposer le palet (codé en dur).
				loop = false;
				System.out.println("Fin de la fonction premieresActions");
			}
		}
		agent.getPinces().fermeture();//On remet les pinces en position initiales
	}
	
	public void allerVersLenButAdverse() {
		//On oriente notre robot vers l'en but adverse en regardant de combien on a deja pivoter de degré notre robot.
		if(historiqueDegres<=180) {
			agent.getTournerOuPivoter().pivoterDunDegreDonne(-historiqueDegres);
		}
		else if(historiqueDegres>180) {
			agent.getTournerOuPivoter().pivoterDunDegreDonne(360-historiqueDegres);
		}
		else if(historiqueDegres<=-180) {
			agent.getTournerOuPivoter().pivoterDunDegreDonne(-360-historiqueDegres);
		}
		//Avancer jusqu'a la ligne blanche tout en evitant les murs, robot et palet
		agent.getAvancerOuReculer().avancerJusquaUneLigneEtEviterObstacle(agent.getCapteurCouleur(),agent.getCapteurUltrasons(),agent.getAction(),"blanc");
	}
	
	public void enregistrerPositionRobot(int degre) {//Fonction appelée par la classe tourner et/ou pivoter voir si on met un attribut agent dans le constructeur de cette classe
		//A chaque rotation on enregistre les degrees
		this.historiqueDegres += degre;
		if(historiqueDegres>=360) {
			historiqueDegres = historiqueDegres -360;
		}
		else if (historiqueDegres <= -360) {
			historiqueDegres = historiqueDegres + 360;
		}
		//A chaque passage sur une ligne de couleur on enregistre la couleur si elle nous intérresse (Notre camp + couleur blanche)
	}
	
	public boolean robotEstBloque() {
		return agent.getCapteurUltrasons().murOuRobotDetecte();
	}
	
	public void reagirRobotBloque() {//ça c'est plus si le robot est coincé vraiment dans un coin
		agent.getAvancerOuReculer().reculerPourUnTemps(2);//On recule
		agent.getTournerOuPivoter().pivoterDunDegreDonne(180);//On fait demi tour
		//Suite : On pivote pour recup des distances et voir quelle est la meilleur direction ou on peut aller.
	}
	
	public void deposerLePalet() {
		//On avance un peu
		agent.getAvancerOuReculer().avancerPourUnTemps((float)0.5);
		//On ouvre les pinces
		agent.getPinces().ouverture();
		//On recule
		agent.getAvancerOuReculer().reculerPourUnTemps(2);
		//on pivote de 50 degrees environ
		agent.getTournerOuPivoter().pivoterDunDegreDonne(50);
		//On se stop si c'est la fonction premieresAction qui a déclenché cette fonction sinon on appel la fonction detecterAutourduRobot pour un autre palet
		
		//Si on veut finir : 
		agent.getPinces().fermeture();
	}

}
