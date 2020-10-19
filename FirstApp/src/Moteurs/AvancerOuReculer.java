package Moteurs;

import Vue.CapteurCouleur;
import Vue.CapteurTactile;
import lejos.robotics.Color;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.utility.Delay;

public class AvancerOuReculer extends Deplacement {

	public AvancerOuReculer(RegulatedMotor left, RegulatedMotor right) {
		super(left, right);
		RegulatedMotor[] syncList = {right};
		left.synchronizeWith(syncList);		
	}
	
	public void avancer() {
		this.getLeftMotor().forward();
		this.getRightMotor().forward();
	}
	
	public void sarreter() {
		this.getRightMotor().stop(true);
		this.getLeftMotor().stop(true);
	}
	
	public void avancerJusquaUneLigne(CapteurCouleur capteurCouleur,String couleur) {
		boolean boucle = true;
		while(boucle) {
			//On set la couleur
			capteurCouleur.setCouleur();
			//On avance si c'est pas la bonne couleur
			if (capteurCouleur.getCouleur() != couleur) {
				this.getLeftMotor().forward();
				this.getRightMotor().forward();
			}else{
				//On a trouvé la bonne couleur on s'arrete
				this.getRightMotor().stop(true);
				this.getLeftMotor().stop(true);
				boucle = false;
			}
		}
	}
	
	public void avancerSurUneDistance(int distance) {//Distance en centimètres
		this.resetTachoMetre();
		//Le tachometre enregistre l'angle en degres de combien tourne le moteur dans son axe.
		//Il faut calculer de combien de centimetres on se déplace pour 1 révolution (360 degrees)
		//Methode avec les deux paramètres pour que ça finisse en meme temps
		this.getLeftMotor().rotate(360,true);
		this.getRightMotor().rotate(360,true);
	}
	
	public void avancerPourUnTemps(float seconde) {
		this.getLeftMotor().forward();
		this.getRightMotor().forward();
		Delay.msDelay((long) (seconde*1000));
		this.getRightMotor().stop(true);
		this.getLeftMotor().stop(true);
	}
	
	public void avancerTqCapteurPressionPasEnfonce(CapteurTactile capteur) {
		while(capteur.getPression() == false) {
			this.getLeftMotor().forward();
			this.getRightMotor().forward();
			capteur.setPression();
		}
		//Fin on a détecter une pression on arrete le robot :
		this.getRightMotor().stop(true);
		this.getLeftMotor().stop(true);
	}
	
	public void reculerPourUnTemps(float seconde) {
		this.getLeftMotor().backward();
		this.getRightMotor().backward();
		Delay.msDelay((long) (seconde*1000));
		this.getRightMotor().stop(true);
		this.getLeftMotor().stop(true);
	}
	
	public void reculerSurUneDistance(int distance) {//Distance en centimètres
		this.resetTachoMetre();
		//Le tachometre enregistre l'angle en degres de combien tourne le moteur dans son axe.
		//Il faut calculer de combien de centimetres on se déplace pour 1 révolution (360 degrees)
		//Methode avec les deux paramètres pour que ça finisse en meme temps
		this.getLeftMotor().rotate(-360,true);
		this.getRightMotor().rotate(-360,true);
	}
	


}
