package Moteurs;

import Controleur.Action;
import Robot.Agent;
import Vue.CapteurCouleur;
import Vue.CapteurUltrasons;
import lejos.robotics.Color;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class TournerOuPivoter extends Deplacement {

	private Action action;

	public TournerOuPivoter(RegulatedMotor left, RegulatedMotor right) {
		super(left, right);
	}

	public void pivoterDunDegreDonne(int degre) {//valeur positive == vers la droite
		this.getLeftMotor().resetTachoCount();
		//this.getLeftMotor().rotateTo(0);
		this.getLeftMotor().rotate((int) (degre*4.5));
		action.enregistrerPositionRobot(degre);
		//this.getRightMotor().rotate(degre,true);
	}

	public void pivoterJusquaDetectionDunPalet(Agent agent) {
		//Capteur ultrasons
		boolean boucle = true;
		while(boucle) {
			//On set la couleur
			agent.getCapteurUltrasons().setDistance();
			//On tourne tq ce n'est pas un palet
			if (agent.getCapteurUltrasons().VerifSiObjetDetecteEstUnPalet(agent.getAvancerOuReculer()) == false) {
				this.getLeftMotor().forward();
			}
			
			else {
				//On a trouvé un palet on arrete de tourner
				this.getLeftMotor().stop();
				boucle = false;
				//On lance ensuite une procédure un peu comme premieres action mais qui devra ammener le robot dans la bonne direction grace à enregistrerpositionrobot
			}
		}
	}

	public void tournerJusquaDetecterUneLigne(CapteurCouleur capteurCouleur) {
		//Vraiment utile ?
	}

	public void tournerSurUnTempsEtUneDirectionVague(float seconde, int degre) {
		//Faire touner un moteur plus vite que l'autre.
		this.getLeftMotor().forward();
		this.getRightMotor().forward();
		Delay.msDelay((long) (seconde*1000));
		//Pour l'instant on tourne légérement à droite si degrés > 0
		if (degre > 0) {
			this.getRightMotor().stop();
			this.getLeftMotor().stop();
		}
		else {
			this.getRightMotor().stop();
			this.getLeftMotor().stop();
		}
	}

}
