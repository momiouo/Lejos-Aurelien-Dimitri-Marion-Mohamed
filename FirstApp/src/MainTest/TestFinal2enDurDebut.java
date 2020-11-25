package MainTest;

import Robot.Agent;
import lejos.hardware.Button;
import lejos.utility.Delay;

public class TestFinal2enDurDebut {
	

	public static void main(String[] args) throws Exception {	
				
		TestFinal2enDurDebut test = new TestFinal2enDurDebut();
		
		test.start();
		test.mainLoop();
		
		System.out.println("Fin du test");
		Delay.msDelay(20000);
	}

	private Agent agent;
	private int degrestournes;
	private boolean bonneDirection;
	
	public void start() {
		agent = new Agent();
		
		//Debut premiereAction
				int positionInitiale = 1;//Milieu par défaut
				System.out.println("Press left or right to run testFinalAvecPremieresActions...");
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

		//System.out.println("Press Enter to run testFinalSansPremieresActions...");
		//Button.waitForAnyPress();
	
				//A changer en fonction de si on mets premieresAction ou pas
		degrestournes = 270;//Par rapport au stade (car de base le robot est à 90 degrés il est perpendiculaire à l'horizontale)
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
			//Delay.msDelay(5000);
			agent.getAvancerOuReculer().reculerPourUnTemps(1.5f);
			agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaDroite(45);//A optimiser droite ou gauche dépend de la distance du mur
			calculDegre(45);
			System.out.println("Fin du débloquage");
			//Delay.msDelay(5000);
			mainLoop();
			return;
		}else if(agent.getCapteurCouleur().couleurEstBlanche()) {//Couleur blanche
			System.out.println("Couleur blanche détéctée sans palet");
			//Delay.msDelay(5000);
			agent.getAvancerOuReculer().reculerPourUnTemps(1.5f);
			agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaDroite(135);
			calculDegre(135);
			System.out.println("Fin du traitement de la couleur blanche");
			//Delay.msDelay(5000);
			mainLoop();
			return;
		}else {//Pression tactile
			System.out.println("Pression tactile détéctée : direction == " + bonneDirection);
			//Delay.msDelay(5000);
			agent.getPinces().fermeture();
			if(!bonneDirection) {
				agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaDroite(180);
				calculDegre(180);
			}
			this.avancerJusquaLenButAdverseEnEvitantLesMurs();
			//On depose le palet :
			agent.getPinces().ouverture();
			agent.getAvancerOuReculer().reculerPourUnTemps(1.5f); 
			agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaDroite(180);
			calculDegre(180);
			System.out.println("Fin du traitement de la pression tactile");
			//Delay.msDelay(5000);
			mainLoop();
			return;
		}
	}
	
	public void calculDegre(int degre) throws Exception {
		//Calcul bonne direction :
		
		degrestournes += degre;
		
		if(degrestournes > 360) {
			degrestournes -= 360;
		}
		
		if(degrestournes >= 0 && degrestournes <= 180) {
			bonneDirection = true;
		}else if(degrestournes >= 180 && degrestournes <= 360) {
			bonneDirection = false;
		}
		
		//Fin calcul bonne direction 
		System.out.println("bonnedirection : " + bonneDirection);
		System.out.println("degre : " + degrestournes);
		//Delay.msDelay(5000);
	}
	
	public void avancerJusquaLenButAdverseEnEvitantLesMurs() throws Exception {
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
		//Delay.msDelay(5000);
		agent.getAvancerOuReculer().reculerPourUnTemps(1.5f);
		if(!bonneDirection) {
			agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaDroite(180);
			calculDegre(180);
		}else{
			agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaDroite(45);
			calculDegre(45);
		}
		avancerJusquaLenButAdverseEnEvitantLesMurs();
	}

}
