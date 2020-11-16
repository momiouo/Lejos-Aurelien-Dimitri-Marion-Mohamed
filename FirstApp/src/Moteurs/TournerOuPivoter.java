package Moteurs;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import Controleur.Action;
import Robot.Agent;
import Vue.CapteurCouleur;
import Vue.CapteurUltrasons;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.Color;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;

public class TournerOuPivoter extends Deplacement {

	private Action action;

	public TournerOuPivoter(EV3LargeRegulatedMotor left, EV3LargeRegulatedMotor right, Action action) {
		super(left, right);
		this.action = action;
	}
	
	public void pivoterAvecDeuxRouesVersLaGauche(int degre) {
		MovePilot movePilot = new MovePilot(56,56,117,this.getLeftMotor(),this.getRightMotor(),false);
		movePilot.rotate(degre);
		action.enregistrerPositionRobot(-degre);
	}
	
	public void pivoterAvecDeuxRouesVersLaDroite(int degre) {
		MovePilot movePilot = new MovePilot(56,56,117,this.getLeftMotor(),this.getRightMotor(),true);
		movePilot.rotate(degre);
		action.enregistrerPositionRobot(degre);
	}

	public void pivoterDunDegreDonneEnCrochet(int degre) {//valeur positive == vers la droite
		//System.out.println("pivoterDunDegreDonneEnCrochet");
		this.getLeftMotor().rotate((int) (degre*4.5),true);
		this.getLeftMotor().waitComplete();
		action.enregistrerPositionRobot(degre);
	}
	
	//Fonction qui pivote le robot à 360 et detecte tout les objets autour puis aligne le robot vers l'objet le plus proche
	public void pivoterEtDetecterSurUnDegreDonne(Agent agent, int degre) {		
		System.out.println("pivoterEtDetecterSurUnDegreDonne");
		int miniRotate = 0;
		float distancecourante = 0;
		int degrecourant = 0;
		ArrayList<Float> lesdistances = new ArrayList();
		ArrayList<Integer> lespositions = new ArrayList();
		int i = 1;
		
		while (i <= degre) {
			pivoterDunDegreDonneEnCrochet(10);//On pivote de 10 degrés à chaque fois
			//On recupère une distance
			agent.getCapteurUltrasons().setDistance();
			distancecourante = agent.getCapteurUltrasons().getDistance();
			//On la sauvegarde avec une position correspondante
			lesdistances.add(distancecourante);
			lespositions.add(i+1);
			i+=10;
		}
		
		//On récupère la position de la plus petite valeur (objet le plus proche)
		int minIndice = lesdistances.indexOf(Collections.min(lesdistances));//Recupère l'index de la plus petite distance
		//Si la distance est trop petite pour etre un palet on cherche une autre valeur
		while(lesdistances.get(minIndice) < 0.320) {
			lesdistances.remove(minIndice);
			minIndice = lesdistances.indexOf(Collections.min(lesdistances));
		}
		int degremin = lespositions.get(minIndice);//Recupere la position en degre de l'objet le plus proche
	
		//---------------------------
		System.out.println("Objet le plus proche" + degremin);	
		//----------------------------
		
		pivoterDunDegreDonneEnCrochet(-degre+(degremin+10));//+10 pour bien s'aligner en face du palet (marge d'erreur)
		
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
