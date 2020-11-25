package Moteurs;

import Controleur.Action;
import Robot.Agent;
import Vue.CapteurCouleur;
import Vue.CapteurTactile;
import Vue.CapteurUltrasons;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.Color;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;

public class AvancerOuReculer extends Deplacement {

	public AvancerOuReculer(EV3LargeRegulatedMotor left, EV3LargeRegulatedMotor right) {
		super(left, right);
		right.synchronizeWith(new EV3LargeRegulatedMotor[] {left});		
	}
	
	public void avancerSynchro() {
		this.getLeftMotor().startSynchronization();
		this.getLeftMotor().forward();
		this.getRightMotor().forward();
		this.getLeftMotor().endSynchronization();
	}
	
	public void reculerSynchro() {
		this.getLeftMotor().startSynchronization();
		this.getLeftMotor().backward();
		this.getRightMotor().backward();
		this.getLeftMotor().endSynchronization();
	}
	
	public void sarreterSynchro() {
		this.getRightMotor().stop(true);
		this.getLeftMotor().stop();
	}
	
/*Methode pour avancer jusqu'à une ligne de couleur passé en parametre
 *Utilise le capteur couleur
 * */
	public void avancerJusquaUneLigne(CapteurCouleur capteurCouleur,String couleur) {
		avancerSynchro();
		while(capteurCouleur.getCouleur() != couleur) {
			capteurCouleur.setCouleur();
		}
		sarreterSynchro();
	}
	
/*Methode pour avancer jusqu'à une ligne de couleur passé en parametre, en evitant les obstacles, càd les murs ou le robot adverse
 *Permet de reagir face à un obstacle, si par exemple le robot fait face à un mur ou est bloqué
 *Utilise le capteur couleur et ultrasons
 * */
	public void avancerJusquaUneLigneEtEviterObstacle(CapteurCouleur capteurCouleur,CapteurUltrasons capteurUltrasons,Action action,String couleur) {
		System.out.println("avancerJusquaUneLigneEtEviterObstacle");
		boolean boucle = true;
		while(boucle) {
			if(!capteurUltrasons.murOuRobotDetecte()) {
				capteurCouleur.setCouleur();
				if (capteurCouleur.getCouleur() != couleur) {
					avancerSynchro();
				}else{
					//On a trouvé la bonne couleur on s'arrete
					sarreterSynchro();
					action.deposerLePalet();
					boucle = false;
				}
			}else {//On doit changer de trajectoire
				action.reagirRobotBloque();
			}
		}
	}

/* Avancer sur une distance passée en parametre
 * */
	public void avancerSurUneDistance(float distance) {//Distance en mm
		MovePilot movePilot = new MovePilot(56,56,147,this.getLeftMotor(),this.getRightMotor(),false);
		movePilot.travel(distance);
	}
	
/* Reculer sur une distance passée en parametre
* */
	public void reculerSurUneDistance(float distance) { //distance en mm
		MovePilot movePilot = new MovePilot(56,56,147,this.getLeftMotor(),this.getRightMotor(),true);
		movePilot.travel(distance);
	}

/* Avancer pendant un temps precis
 */
	public void avancerPourUnTemps(float seconde) {
		avancerSynchro();
		Delay.msDelay((long) (seconde*1000));
		sarreterSynchro();
	}
	
	public void avancerPourUnTempsMovePilot(float seconde) {
		MovePilot movePilot = new MovePilot(56,56,147,this.getLeftMotor(),this.getRightMotor(),false);
		movePilot.forward();
		Delay.msDelay((long) (seconde*1000));
		movePilot.stop();
	}
	
/* Reculer pendant un temps precis
 */
	public void reculerPourUnTemps(float seconde) {
		this.reculerSynchro();
		Delay.msDelay((long) (seconde*1000));
		sarreterSynchro();
	}

	/* Avancer tant que la pression du capteur tactile n'est pas activée et que l'on a pas croisé de ligne blanche sinon on re fait une détection des objets autour.
	 * */
	public void avancerTqCapteurPressionPasEnfonce(CapteurTactile capteurTactile, Action action, CapteurCouleur capteurCouleur) {
		System.out.println("avancerTqCapteurPressionPasEnfonce");
		capteurCouleur.setCouleur();
		if(capteurCouleur.getCouleur().equals("blanc")) {//Verification qu'on ne pas pas sur une ligne blanche sinon :
			System.out.println("J'ai detecte du blanc");
			action.detecterAutourDuRobot(false,false);//On a raté le palet, on refait une détéction.
		}else {
			capteurTactile.setPression();
			if(capteurTactile.getPression()) {//Capteur enfoncée :
				action.onAUnPalet();
			}else {
				avancerSynchro();
				while(capteurTactile.getPression() == false && !capteurCouleur.getCouleur().equals("blanc")) {
					capteurCouleur.setCouleur();
					capteurTactile.setPression();
				}
				sarreterSynchro();
				if(capteurTactile.getPression()) {//une pression on arrete le robot :
					action.onAUnPalet();
				}else {
					action.detecterAutourDuRobot(false,false);//On a raté le palet, on refait une détéction.
				}
			}
		}
	}
	
	/* Avancer tant que la pression du capteur tactile n'est pas activée
	 * */
	public void avancerTqCapteurPressionPasEnfonceTest(CapteurTactile capteur) {//Pour le codage en dur -> premieresAction
		avancerSynchro();
		while(capteur.getPression() == false) {
			capteur.setPression();
		}
		sarreterSynchro();
	}
	
}
