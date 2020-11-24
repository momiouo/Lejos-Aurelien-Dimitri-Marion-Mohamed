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
		//Synchronisation pour que les moteurs des roues gauche et droite tournent en même temps. 
	}
	
	// => https://lejosnews.wordpress.com/2014/10/06/motor-synchronization-problems-part-2/
	
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
		boolean boucle = true;
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
			if(!capteurUltrasons.murOuRobotDetecte()) {//Pas de mur ni de robot + pas de palet (+tard)
				//On set la couleur
				capteurCouleur.setCouleur();
				//On avance si c'est pas la bonne couleur
				if (capteurCouleur.getCouleur() != couleur) {
					avancerSynchro();
				}else{
					//On a trouvé la bonne couleur on s'arrete
					sarreterSynchro();
					action.deposerLePalet(false);
					boucle = false;
				}
			}else {//A faire : On doit changer de trajectoire
				action.reagirRobotBloque();
			}
		}
	}

/* Avancer sur une distance passé en parametre
 * */
	public void avancerSurUneDistance(float distance) {//Distance en mm
		MovePilot movePilot = new MovePilot(56,56,147,this.getLeftMotor(),this.getRightMotor(),false);
		movePilot.travel(distance);
	}
	
	/*public void avancerSurUneDistanceStopLigneBlanche(float distance,CapteurCouleur capteurCouleur) {//Distance en mm
		this.resetTachoMetre();
		int tachoACalculer = 75/(Math.PI*56);
		while(!capteurCouleur.getCouleur().equals("blanc") && this.getLeftMotor().getTachoCount()<distance) {
			//Utiliser le tachomètre pour faire un forward jusqu'a un bon nombre de revolution pour avoir la bonne distance
			this.getLeftMotor().forward();
			this.getRightMotor().forward();
		}
	}*/
	
/* Reculer sur une distance passé en parametre
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


	public void avancerTqCapteurPressionPasEnfonce(CapteurTactile capteurTactile, Action action, CapteurCouleur capteurCouleur) {
		System.out.println("avancerTqCapteurPressionPasEnfonce");
		capteurCouleur.setCouleur();
		if(capteurCouleur.getCouleur().equals("blanc")) {
			System.out.println("J'ai detecte du blanc");
			action.detecterAutourDuRobot(false,false);
		}else {
			capteurTactile.setPression();
			if(capteurTactile.getPression()) {
				action.onAUnPalet();
			}else {
				avancerSynchro();
				while(capteurTactile.getPression() == false && !capteurCouleur.getCouleur().equals("blanc")) {
					capteurCouleur.setCouleur();
					capteurTactile.setPression();
				}
				sarreterSynchro();
				//une pression on arrete le robot :
				if(capteurTactile.getPression()) {
					action.onAUnPalet();
				}else {
					action.detecterAutourDuRobot(false,false);
				}
			}
		}
	}
	
	public void avancerTqCapteurPressionPasEnfonceTest(CapteurTactile capteur) {//Pour le codage en moyen dur premieresAction
		avancerSynchro();
		while(capteur.getPression() == false) {
			capteur.setPression();
		}
		//Fin on a détecter une pression on arrete le robot :
		sarreterSynchro();
	}
	
}
