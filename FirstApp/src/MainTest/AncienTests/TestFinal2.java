package MainTest.AncienTests;

import Robot.Agent;
import lejos.hardware.Button;
import lejos.utility.Delay;

public class TestFinal2 {


	public static void main(String[] args) throws Exception {	
				
		TestFinal2 test = new TestFinal2();
		
		test.start();
		test.mainLoop();
		
		System.out.println("Fin du test");
		Delay.msDelay(20000);
	}

	private Agent agent;
	private int degrestournes;
	
	public void start() {
		agent = new Agent();
		//Debut premiereAction
		int positionInitiale = 1;//Milieu par défaut
		System.out.println("Press left or right to run testPremieresActions...");
		Button.waitForAnyPress();
		if(Button.RIGHT.isDown()) {
			positionInitiale = 2;
			System.out.println("Right");
		}else if(Button.LEFT.isDown()) {
			positionInitiale = 0;
			System.out.println("Left");
		}
		agent.getAction().premieresActions(positionInitiale);
		//Fin premieres Action
		degrestournes = 180;
	}
	
	public void mainLoop() throws Exception {	
		boolean loop = true;
		agent.getPerceptionAct().initCapteurs();//On init la valeur initiale à tous les capteurs
		agent.getPinces().ouverture();//Ouvre que si pas déja ouvert
		agent.getAvancerOuReculer().avancerSynchro(); // On avance
		//Boucle : 
		while(loop) {
			agent.getCapteurCouleur().setCouleur();
			if(agent.getCapteurCouleur().couleurEstBlanche()) {
				break;
			}
			agent.getCapteurTactile().setPression();
			if(agent.getCapteurTactile().getPression()) {
				break;
			}
			if(agent.getCapteurUltrasons().murOuRobotDetecte()) {
				break;
			}
		}
		
		//Stop
		agent.getAvancerOuReculer().sarreterSynchro();
		
		//En fonction des cas :
		if(agent.getAction().robotEstBloque()) {//Robot bloqué
			System.out.println("Le Robot Est Bloqué");
			Delay.msDelay(5000);
			agent.getAvancerOuReculer().reculerPourUnTemps(1.5f);
			agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaDroite(45);//A optimiser droite ou gauche dépend de la distance du mur
			degrestournes+=45;
			verifResetDegreCar360();
			System.out.println("Fin du débloquage");
			Delay.msDelay(5000);
			mainLoop();
			return;
		}else if(agent.getCapteurCouleur().couleurEstBlanche()) {//Couleur blanche
			System.out.println("Couleur blanche détéctée sans palet");
			Delay.msDelay(5000);
			agent.getAvancerOuReculer().reculerPourUnTemps(1.5f);
			agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaDroite(135);
			degrestournes+=135;
			verifResetDegreCar360();
			System.out.println("Fin du traitement de la couleur blanche");
			Delay.msDelay(5000);
			mainLoop();
			return;
		}else {//Pression tactile
			System.out.println("Pression tactile détéctée : direction == " + degrestournes);
			Delay.msDelay(5000);
			agent.getPinces().fermeture();
			agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaGauche(degrestournes);//Direction l'en but adverse
			degrestournes -= degrestournes; //- car vers la gauche
			verifResetDegreCar360();
			this.avancerJusquaLenButAdverseEnEvitantLesMurs(false);//Risque de pousser les autres palets
			//On depose le palet :
			agent.getPinces().ouverture();
			agent.getAvancerOuReculer().reculerPourUnTemps(1.5f); 
			agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaDroite(180);
			degrestournes+=180;
			verifResetDegreCar360();
			System.out.println("Fin du traitement de la pression tactile");
			Delay.msDelay(5000);
			mainLoop();
			return;
		}
	}
	
	public void verifResetDegreCar360() throws Exception {
		if(degrestournes <= 360) {
			return;
		}else if(degrestournes <= 720){
			degrestournes -= 360;
		}else {
			//throw new Exception("Le robot a tourné a plus de 720 degre erreur dans le reset 360");
		}
		
	}
	
	public void avancerJusquaLenButAdverseEnEvitantLesMurs(boolean robotperdu) throws Exception {
		System.out.println("avancerJusquaUneLigneBlancheEtEviterObstacle");
		boolean boucle = true;
		while(boucle) {//
			if(agent.getCapteurUltrasons().murOuRobotDetecteAvecDistance(0.150f)) {//Cas d'arret 1
				agent.getAvancerOuReculer().sarreterSynchro();
				robotEstBloque(robotperdu);
				break;
			}else {
				agent.getCapteurCouleur().setCouleur();
				if (agent.getCapteurCouleur().getCouleur() == "blanc") {//Cas d'arret 2
					agent.getAvancerOuReculer().sarreterSynchro();
					break;
				}
			agent.getAvancerOuReculer().avancerSynchro();
			}
		}
	}
	
//Fonction qui fait reculer le robot si il rencontre un robot adverse ou un mur et s'il est perdu il pivote de 45 deg
	public void robotEstBloque(boolean robotperdu) throws Exception {
		System.out.println("Le Robot Est Bloqué on le remet en direction de l'en but");//Normalement il est déja en bonne direction vers l'en but ici c'est le cas ou le robot rencontre un autre robot
		Delay.msDelay(5000);
		agent.getAvancerOuReculer().reculerPourUnTemps(1.5f);
		if (robotperdu) {
			agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaGauche(45);
		}else {
			agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaGauche(degrestournes);
		}
		degrestournes -= degrestournes; //- car vers la gauche
		verifResetDegreCar360();
		avancerJusquaLenButAdverseEnEvitantLesMurs(true);
	}
	
	//Trop chiant d'utiliser les degres pour se localiser il vaut mieux utiliser les lignes de couleurs a donner en parametre au debut du match
	//Si on fait noir puis vert on sait qu'on est dans la bonne direction par exemple. Mais si on fait noir puis bleu alors pas bon
	
}
