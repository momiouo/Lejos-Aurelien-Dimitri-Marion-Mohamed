package MainTest.AncienTests;

import Robot.Agent;
import lejos.hardware.Button;
import lejos.utility.Delay;

public class TestFinal2MeilleurCalc {


	public static void main(String[] args) throws Exception {	
				
		TestFinal2MeilleurCalc test = new TestFinal2MeilleurCalc();
		
		test.start();
		test.mainLoop();
		
		System.out.println("Fin du test");
		Delay.msDelay(20000);
	}

	private Agent agent;
	private int degrestournes;
	private int directionEnBut;
	private boolean bonneDirection;
	
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
		degrestournes = 270;//Par rapport au stade (car de base le robot est à 90 degrés il est perpendiculaire à l'horizontale)
		directionEnBut = 180;//Par rapport au robot
		bonneDirection = false;
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
			calculDegre(45);
			System.out.println("Fin du débloquage");
			Delay.msDelay(5000);
			mainLoop();
			return;
		}else if(agent.getCapteurCouleur().couleurEstBlanche()) {//Couleur blanche
			System.out.println("Couleur blanche détéctée sans palet");
			Delay.msDelay(5000);
			agent.getAvancerOuReculer().reculerPourUnTemps(1.5f);
			agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaDroite(135);
			calculDegre(135);
			System.out.println("Fin du traitement de la couleur blanche");
			Delay.msDelay(5000);
			mainLoop();
			return;
		}else {//Pression tactile
			System.out.println("Pression tactile détéctée : direction == " + directionEnBut);
			Delay.msDelay(5000);
			agent.getPinces().fermeture();
			agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaDroite(directionEnBut);//Direction l'en but adverse
			calculDegre(directionEnBut);
			this.avancerJusquaLenButAdverseEnEvitantLesMurs(false);//Risque de pousser les autres palets
			//On depose le palet :
			agent.getPinces().ouverture();
			agent.getAvancerOuReculer().reculerPourUnTemps(1.5f); 
			agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaDroite(180);
			calculDegre(180);
			System.out.println("Fin du traitement de la pression tactile");
			Delay.msDelay(5000);
			mainLoop();
			return;
		}
	}
	
	public void calculDegre(int degre) throws Exception {
		//Calcul bonne direction :
		if(degrestournes < 0) {
			degrestournes += 360;
		}
		if(degrestournes >= 0 && degrestournes <= 180) {
			bonneDirection = true;
			degrestournes -= degre;
		}else if(degrestournes >= 180 && degrestournes <= 360) {
			bonneDirection = false;
			degrestournes += degre;
		}else {//degre > 360
			degrestournes -= 360;
		}
		//Fin calcul bonne direction 
		
		//Calcul directionEnBut
		directionEnBut = degrestournes-90;
		
		System.out.println("DegreMap " + degrestournes);
		System.out.println("DegreRobot " + directionEnBut);
		Delay.msDelay(5000);

		
	}
	
	public void avancerJusquaLenButAdverseEnEvitantLesMurs(boolean robotperdu) throws Exception {
		System.out.println("avancerJusquaUneLigneBlancheEtEviterObstacle");
		boolean boucle = true;
		while(boucle) {//
			if(agent.getCapteurUltrasons().murOuRobotDetecteAvecDistance(0.150f)) {//Cas d'arret 1
				agent.getAvancerOuReculer().sarreterSynchro();
				robotEstBloque();
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
	public void robotEstBloque() throws Exception {
		System.out.println("Le Robot Est Bloqué on le remet en direction de l'en but");//Normalement il est déja en bonne direction vers l'en but ici c'est le cas ou le robot rencontre un autre robot
		Delay.msDelay(5000);
		agent.getAvancerOuReculer().reculerPourUnTemps(1.5f);
		if(!bonneDirection) {
			agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaDroite(180);
			calculDegre(180);
		}
		if(directionEnBut == 0) {//Il est bloqué il faut le faire pivoter
			agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaDroite(45);
			calculDegre(45);
		}else {
			agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaDroite(directionEnBut);
			calculDegre(directionEnBut);
		}
		avancerJusquaLenButAdverseEnEvitantLesMurs(true);
	}

	
}
