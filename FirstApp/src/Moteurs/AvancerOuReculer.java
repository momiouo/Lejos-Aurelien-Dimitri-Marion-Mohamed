package Moteurs;

import Controleur.Action;
import Vue.CapteurCouleur;
import Vue.CapteurTactile;
import Vue.CapteurUltrasons;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.Color;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;

public class AvancerOuReculer extends Deplacement {

	public AvancerOuReculer(EV3LargeRegulatedMotor left, EV3LargeRegulatedMotor right) {
		super(left, right);
		right.synchronizeWith(new EV3LargeRegulatedMotor[] {left});		
		//Synchronisation pour que les moteurs des roues gauche et droite tournent en même temps. => https://lejosnews.wordpress.com/2014/10/06/motor-synchronization-problems-part-2/
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
	
	public void avancerJusquaUneLigne(CapteurCouleur capteurCouleur,String couleur) {
		boolean boucle = true;
		while(boucle) {
			//On set la couleur
			capteurCouleur.setCouleur();
			//On avance si c'est pas la bonne couleur
			if (capteurCouleur.getCouleur() != couleur) {
				avancerSynchro();
			}else{
				//On a trouvé la bonne couleur on s'arrete
				sarreterSynchro();
				boucle = false;
			}
		}
	}
	
	public void avancerJusquaUneLigneEtEviterObstacle(CapteurCouleur capteurCouleur,CapteurUltrasons capteurUltrasons,Action action,String couleur) {
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
					action.deposerLePalet();
					boucle = false;
				}
			}else {//On doit changer de trajectoire
				
			}
		}
	}
	
	public void avancerSurUneDistance(float distance) {//Distance en mm
		//56 == diametre en mm de la roue et 147mm == distance entre les deux roues
		MovePilot movePilot = new MovePilot(56,56,147,this.getLeftMotor(),this.getRightMotor(),false);
		movePilot.travel(distance);
	}
	
	public void reculerSurUneDistance(float distance) { //distance en mm
		MovePilot movePilot = new MovePilot(56,56,147,this.getLeftMotor(),this.getRightMotor(),true);
		movePilot.travel(distance);
	}
	
	public void avancerPourUnTemps(float seconde) {
		avancerSynchro();
		Delay.msDelay((long) (seconde*1000));
		sarreterSynchro();
	}
	
	public void avancerTqCapteurPressionPasEnfonce(CapteurTactile capteur) {
		while(capteur.getPression() == false) {
			avancerSynchro();
			capteur.setPression();
		}
		//Fin on a détecter une pression on arrete le robot :
		sarreterSynchro();
	}
	
	public void reculerPourUnTemps(float seconde) {
		this.reculerSynchro();
		Delay.msDelay((long) (seconde*1000));
		sarreterSynchro();
	}
	
}
