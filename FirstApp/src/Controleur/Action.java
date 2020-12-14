package Controleur;

import Robot.Agent;
import Vue.Perception;
import lejos.hardware.Button;


/**
 * Classe pour gérer toutes les actions requises à la compétition.
 * 
 * @author LejosTeam
 *
 */
public class Action {
	
	/**
	 * Objet de la classe agent.
	 */
	private Agent agent;
	/**
	 * Objet de la classe Perception, qui correspond à la perception la plus récente.
	 */
	private Perception perceptionAct;
	/**
	 * Objet de la classe Perception, qui correspond à l'ancienne perception.
	 */
	private Perception perceptionPrec;
	/**
	 * Valeur pour enregister l'orientation du robot.
	 */
	private int historiqueDegres;
	/**
	 * Valeur pour enregistrer le nombre de palet marqué.
	 */
	private int nbPaletMarque;
	
	/**
	 * @param p1
	 * @param p2
	 * @param agent
	 */
	public Action(Perception p1, Perception p2, Agent agent) {
		this.setPerceptionAct(p1);
		this.setPerceptionPrec(p2);
		this.setAgent(agent);
		this.setHistoriqueDegres(0);
		this.setNbPaletMarque(0);
	}
	

	/**
	 * Retourne l’objet Agent.
	 * 
	 * @return
	 */
	public Agent getAgent() {
		return agent;
	}

	/**
	 * Met à jour l’attribut Agent.
	 * 
	 * @param agent
	 */
	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	/**
	 * Retourne l'attribut perception précédent.
	 * 
	 * @return
	 */
	public Perception getPerceptionPrec() {
		return perceptionPrec;
	}

	/**
	 * 
	 * Met à jour l’attribut perceptionPrec.
	 * 
	 * @param perceptionPrec
	 */
	public void setPerceptionPrec(Perception perceptionPrec) {
		this.perceptionPrec = perceptionPrec;
	}

	/**
	 * Retourne l'attribut perception le plus récent.
	 * 
	 * @return
	 */
	public Perception getPerceptionAct() {
		return perceptionAct;
	}

	/**
	 * 
	 * Met à jour l’attribut perceptionAct.
	 * 
	 * @param perceptionAct
	 */
	public void setPerceptionAct(Perception perceptionAct) {
		this.perceptionAct = perceptionAct;
	}

	/**
	 * Retourne l'attribut historiqueDegres.
	 * 
	 * @return
	 */
	public int getHistoriqueDegres() {
		return historiqueDegres;
	}
	

	/**
	 * Met à jour l’attribut historiqueDegres.
	 * 
	 * @param historiqueDegres
	 */
	public void setHistoriqueDegres(int historiqueDegres) {
		this.historiqueDegres = historiqueDegres;
	}
	

	/**
	 * Retourne l'attribut nbPaletMarque.
	 * 
	 * @return
	 */
	public int getNbPaletMarque() {
		return nbPaletMarque;
	}
	

	/**
	 * Met à jour l'attribut nbPaletMarque.
	 * 
	 * @param nbPaletMarque
	 */
	public void setNbPaletMarque(int nbPaletMarque) {
		this.nbPaletMarque = nbPaletMarque;
	}

	
	/**
	 * Gère le comportement du robot à la recupération d'un palet.
	 * 
	 */
	public void onAUnPalet() {
		System.out.println("onAUnPalet");
		agent.getPinces().fermeture();
		agent.getAction().allerVersLenButAdverse();
	}
	

	/**
	 * 
	 * Permet de faire pivoter le robot doucement afin de récupérer les distances des objets environnants.
	 * 
	 * @param start
	 * @param detectionDecalee
	 */
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
	
	/**
	 * Ouvre les pinces du robot, remet à O les tachometres, perceptionPrec prend la valeur de la perceptionAct.
	 */
	public void init() {
		this.perceptionPrec = this.perceptionAct;
		agent.getPinces().ouverture();
		agent.getAvancerOuReculer().resetTachoMetre();
	}
	

	/**
	 * 
	 * Méthode pour la première action : récuperer le premier palet et le deposer dans l'en-but adverse.
	 * 
	 * @param positionInitiale
	 */
	public void premieresActions(int positionInitiale) {
		boolean loop = true;
		while(loop) {
			agent.getPerceptionAct().initCapteurs();
			if (robotEstBloque()){
				System.out.println("Je suis bloqué ... appeller la fonction reagirRobotBloque");
				agent.getAvancerOuReculer().reculerPourUnTemps(1.5f);
				this.premieresActions(positionInitiale);
				loop = false;
			}else {
				this.init();
				agent.getAvancerOuReculer().avancerTqCapteurPressionPasEnfonceTest(agent.getCapteurTactile());
				agent.getPinces().fermeture();
				
				
				if(positionInitiale == 0) {//A gauche
					//Crochet vers la droite.
					agent.getTournerOuPivoter().pivoterDunDegreDonneEnCrochet(70);
					agent.getAvancerOuReculer().avancerPourUnTemps(1.5f);
					agent.getTournerOuPivoter().pivoterDunDegreDonneEnCrochet(-71);//Correction du léger décalage
				}else {//A droite ou millieu
					//Crochet vers la gauche.
					agent.getTournerOuPivoter().pivoterDunDegreDonneEnCrochet(-70);
					agent.getAvancerOuReculer().avancerPourUnTemps(1.5f);
					agent.getTournerOuPivoter().pivoterDunDegreDonneEnCrochet(71);//Correction du léger décalage
				}
				
				
				agent.getAvancerOuReculer().avancerJusquaUneLigne(agent.getCapteurCouleur(), "blanc");
				agent.getPinces().ouverture();
				agent.getAvancerOuReculer().reculerPourUnTemps(1.2f); 
				if(positionInitiale == 0) {
					agent.getTournerOuPivoter().pivoterDunDegreDonneEnCrochet(180);
				}else {
					agent.getTournerOuPivoter().pivoterDunDegreDonneEnCrochet(-180);
				}
				
				loop = false;
				System.out.println("Fin de la fonction premieresActions");
			}
		}
	}
	

	/**
	 * 
	 * Méthode pour se diriger vers l'en-but adverse.
	 * Cette fonction permet d’une part d’orienter le robot en direction de l’en-but adverse en le faisant pivoter du nombre
	 * de degré pivoté depuis le lancement du programme (de + ou - 360 degrés selon la direction souhaitée).
	 * Et d’une autre part, une fois le robot orienté en direction de l’en-but adverse, le robot avance
 	 * jusqu’à atteindre la ligne blanche
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
	
	/**
	 * Méthode qui permet d'enregistrer de combien le robot pivote.
	 * 
	 * @param degre
	 */
	public void enregistrerPositionRobot(int degre) {
		this.historiqueDegres += degre;
		if(historiqueDegres>=360) {
			historiqueDegres = historiqueDegres -360;
		}
		else if (historiqueDegres <= -360) {
			historiqueDegres = historiqueDegres + 360;
		}
	}


	/**
	 * Verifie si le robot est bloqué en faisant appel à murOuRoboDetecte de la classe CapteurUltrasons.
	 * 
	 * @return
	 */
	public boolean robotEstBloque() {
		return agent.getCapteurUltrasons().murOuRobotDetecte();
	}


	/**
	 * Est actionnée quand le robot est bloqué.
	 * Cette fonction permet de réagir en cas de blocage du robot : si les pinces sont fermées (= le robot tient un palet) alors
	 * on fait reculer le robot pendant 2 secondes puis on le fait aller vers l’en-but adverse.
	 * Si les pinces sont ouvertes (= pas de palet entre les pinces), la fonction fait reculer le robot pendant 2 secondes puis lui
 	 * fait chercher un palet en analysant ce qui l’entoure
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

	/**
	 * Cette fonction permet de déposer le palet : le robot ouvre les pinces pour relâcher 
	 * le palet, recule puis pivote de 180 degrés (demi-tour).
	 */
	public void deposerLePalet() {
		System.out.println("deposerLePalet");
		nbPaletMarque++;
		//agent.getAvancerOuReculer().reculerPourUnTemps(0.4f);en commentaire car il va pas si vite
		agent.getPinces().ouverture();
		agent.getAvancerOuReculer().reculerPourUnTemps(1.2f); 
		agent.getTournerOuPivoter().pivoterDunDegreDonneEnCrochet(180);
	}

}
