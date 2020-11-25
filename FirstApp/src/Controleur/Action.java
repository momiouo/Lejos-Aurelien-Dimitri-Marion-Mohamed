package Controleur;

import Robot.Agent;
import Vue.Perception;
import lejos.hardware.Button;


// Classe pour les actions requises à la compétition

public class Action {
	
	private Agent agent;
	private Perception perceptionAct;
	private Perception perceptionPrec;
	private int historiqueDegres;
	private int nbPaletMarque;
	
	// Constructeur:
	public Action(Perception p1, Perception p2, Agent agent) {
		this.setPerceptionAct(p1);
		this.setPerceptionPrec(p2);
		this.setAgent(agent);
		this.setHistoriqueDegres(0);
		this.setNbPaletMarque(0);
	}
	
	// Methodes :

//Récupère l’objet Agent
	public Agent getAgent() {
		return agent;
	}
//Change ou initialise l’attribut Agent.
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
//Récupère l’objet perception précédent
	public Perception getPerceptionPrec() {
		return perceptionPrec;
	}
//Change ou initialise l’attribut perceptionPrec.
	public void setPerceptionPrec(Perception perceptionPrec) {
		this.perceptionPrec = perceptionPrec;
	}
//Récupère l’objet perception le plus récent
	public Perception getPerceptionAct() {
		return perceptionAct;
	}
//Change ou initialise l’attribut perceptionAct.
	public void setPerceptionAct(Perception perceptionAct) {
		this.perceptionAct = perceptionAct;
	}
//Récupère l’objet historiqueDegres	
	public int getHistoriqueDegres() {
		return historiqueDegres;
	}
	
//Change ou initialise l’attribut historiqueDegres.
	public void setHistoriqueDegres(int historiqueDegres) {
		this.historiqueDegres = historiqueDegres;
	}
	
//Récupère l’objet nbPaletMarque	
	public int getNbPaletMarque() {
		return nbPaletMarque;
	}
	
//Change ou initialise l'attribut nbPaletMarque	
	public void setNbPaletMarque(int nbPaletMarque) {
		this.nbPaletMarque = nbPaletMarque;
	}

//Reaction du robot à la recupération d'un palet
	public void onAUnPalet() {
		System.out.println("onAUnPalet");
		agent.getPinces().fermeture();
		agent.getAction().allerVersLenButAdverse();
	}
	
//Permet de faire pivoter le robot doucement afin de récupérer les distances des objets environnants
	public void detecterAutourDuRobot(boolean start, boolean detectionDecalee) {
		System.out.println("detecterAutourDuRobot");
		int nbligneblancheAfranchir;
		if(start) {
			agent.getPinces().ouverture();
		}if(detectionDecalee) {
			agent.getTournerOuPivoter().pivoterDunDegreDonneEnCrochet(90);
		}
		agent.getTournerOuPivoter().pivoterEtDetecterSurUnDegreDonne(agent, 180);
		if(agent.getCapteurUltrasons().VerifSiObjetDetecteEstUnPalet(agent.getAvancerOuReculer(),agent.getCapteurTactile(),agent.getCapteurCouleur())) {
			System.out.println("On a un palet en face de soit !!!!");
			agent.getAvancerOuReculer().avancerTqCapteurPressionPasEnfonce(agent.getCapteurTactile(),this,agent.getCapteurCouleur());
		}else {
			//C'est un mur ou robot adverse ou un palet derriere ligne blanche on recommence la recherche
			detecterAutourDuRobot(false,true);
		}
	}
//ouvre les pinces du robot, remet à O les tachometres, perceptionprec = perceptionact
	public void init() {
		this.perceptionPrec = this.perceptionAct;
		agent.getPinces().ouverture();
		agent.getAvancerOuReculer().resetTachoMetre();
	}
	
//Methode pour la 1ère action: Recuperer le premier palet et le deposer dans l'en-but adverse
	public void premieresActions() {
		boolean loop = true;
		System.out.println("Press enter to run premieresActions...");
		Button.ENTER.waitForPressAndRelease();
		while(loop) {
			agent.getPerceptionAct().initCapteurs();
			if (robotEstBloque()){
				System.out.println("Je suis bloqué ... appeller la fonction reagirRobotBloque");
				loop = false;
			}else {
				this.init();
				agent.getAvancerOuReculer().avancerTqCapteurPressionPasEnfonceTest(agent.getCapteurTactile());
				agent.getPinces().fermeture();
				
				agent.getTournerOuPivoter().pivoterDunDegreDonneEnCrochet(-45);
				agent.getAvancerOuReculer().avancerPourUnTemps(1);
				agent.getTournerOuPivoter().pivoterDunDegreDonneEnCrochet(45);
				
				agent.getAvancerOuReculer().avancerJusquaUneLigne(agent.getCapteurCouleur(), "blanc");
				this.deposerLePalet(true);
				loop = false;
				System.out.println("Fin de la fonction premieresActions");
			}
		}
	}
	
/*Methode pour aller vers l'enbut adverse
 * Cette fonction permet d’une part d’orienter le robot en direction de l’en-but adverse en le faisant pivoter du nombre
 * de degré pivoté depuis le lancement du programme (de + ou - 360 degrés selon la direction souhaitée)
 * Et d’une autre part, une fois le robot orienté en direction de l’en-but adverse, la fonction permet de faire avancer le robot
 jusqu’à atteindre la ligne blanche
 */
	public void allerVersLenButAdverse() {
		System.out.println("allerVersLenButAdverse");
		//On oriente notre robot vers l'en but adverse en regardant de combien on a deja pivoter de degré notre robot.
		if(historiqueDegres<=180) {
			agent.getTournerOuPivoter().pivoterDunDegreDonneEnCrochet(-historiqueDegres);
		}
		else if(historiqueDegres>180) {
			agent.getTournerOuPivoter().pivoterDunDegreDonneEnCrochet(360-historiqueDegres);
		}
		else if(historiqueDegres<=-180) {
			agent.getTournerOuPivoter().pivoterDunDegreDonneEnCrochet(-360-historiqueDegres);
		}
		agent.getAvancerOuReculer().avancerJusquaUneLigneEtEviterObstacle(agent.getCapteurCouleur(),agent.getCapteurUltrasons(),agent.getAction(),"blanc");
	}
	
//Methode qui permet d'enregistrer de combien le robot pivote.
	public void enregistrerPositionRobot(int degre) {
		this.historiqueDegres += degre;
		if(historiqueDegres>=360) {
			historiqueDegres = historiqueDegres -360;
		}
		else if (historiqueDegres <= -360) {
			historiqueDegres = historiqueDegres + 360;
		}
	}

/*Verifier si le robot est bloqué en faisant appel à murOuRoboDetecte de la classe CapteurUltrasons.
 * 
 */
	public boolean robotEstBloque() {
		return agent.getCapteurUltrasons().murOuRobotDetecte();
	}

/*Reagir quand le robot est bloqué
 * Cette fonction permet de réagir en cas de blocage du robot : si les pinces sont fermées (= le robot tient un palet) alors
 * on fait reculer le robot pendant 2 secondes puis on le fait aller vers l’en-but adverse.
 * Si les pinces sont ouvertes (= pas de palet entre les pinces) la fonction fait reculer le robot pendant 2 secondes puis lui
 fait chercher un palet en analysant ce qui l’entoure
 */

	public void reagirRobotBloque() {
		System.out.println("reagirRobotBloque");
		if(agent.getPinces().isPincesOuvertes()==false) {
				agent.getAvancerOuReculer().reculerPourUnTemps(2);
				allerVersLenButAdverse();
		}
		else {
			agent.getAvancerOuReculer().reculerPourUnTemps(2);
			detecterAutourDuRobot(false, true);
		}
	}

/*Deposer le palet
 * Cette fonction permet de déposer le palet : le robot ouvre les pinces pour relacher 
 * le palet, recule puis pivote de 180 degrés (demi-tour).
 */
	public void deposerLePalet(boolean premieresAction) {
		System.out.println("deposerLePalet");
		nbPaletMarque++;
		//agent.getAvancerOuReculer().reculerPourUnTemps(0.4f);en commentaire car il va pas si vite
		agent.getPinces().ouverture();
		agent.getAvancerOuReculer().reculerPourUnTemps(0.8f); 
		agent.getTournerOuPivoter().pivoterDunDegreDonneEnCrochet(180);
		
		
		//------------------ Les cas d'arrets ------------------------
		
		if(premieresAction) {//Fin test premieresActions
			agent.getPinces().fermeture();
			System.exit(0); //pour le test bourrin
		}else {
			//On continue	
		}
		
	}

}
