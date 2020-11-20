package MainTest;

import Robot.Agent;
import lejos.hardware.Button;
import lejos.utility.Delay;

public class TestBourrin {

	public static void main(String[] args) throws Exception {	
				
		TestBourrin test = new TestBourrin();
		test.start();
		//test.fermerLesPinces();//car pince ouvertes au depart sinon a enlever
		test.codeBourrinCarLeRobotAvancePasDroit();
		
		System.out.println("Fin du test");
		Delay.msDelay(20000);
	}

	private Agent agent;
	private int degrestournes;
	
	public void start() {
		agent = new Agent();
		degrestournes = 0;//Faire avec les couleurs peut-etre.
		System.out.println("Press enter to run testBourrin...");
		Button.ENTER.waitForPressAndRelease();
	}
	
	public void codeBourrinCarLeRobotAvancePasDroit() throws Exception {	
		boolean loop = true;
		agent.getPerceptionAct().initCapteurs();//On init la valeur initiale à tous les capteurs
		
		agent.getPinces().ouverture();//Ouvre que si pas déja ouvert
		agent.getAvancerOuReculer().avancerSynchro(); // On avance
		//Boucle : 
		while(loop) {
			//agent.getPerceptionAct().initCapteurs();//On set la valeur des capteurs à chaque boucle
			agent.getCapteurCouleur().setCouleur();
			if(agent.getCapteurCouleur().couleurEstBlanche()) {
				break;
			}
			agent.getCapteurTactile().setPression();
			if(agent.getCapteurTactile().getPression()) {
				break;
			}
			//agent.getCapteurUltrasons().setDistance(); ca le fait deja dans murouRobotDEtecte
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
			agent.getAvancerOuReculer().reculerPourUnTemps(2);
			agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaDroite(45);//A optimiser droite ou gauche dépend de la distance du mur
			degrestournes+=45;
			verifResetDegreCar360();
			System.out.println("Fin du débloquage");
			Delay.msDelay(5000);
			codeBourrinCarLeRobotAvancePasDroit();
			return;
		}else if(agent.getCapteurCouleur().couleurEstBlanche()) {//Couleur blanche
			System.out.println("Couleur blanche détéctée sans palet");
			Delay.msDelay(5000);
			agent.getAvancerOuReculer().reculerPourUnTemps(2);
			agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaDroite(135);
			degrestournes+=135;
			verifResetDegreCar360();
			System.out.println("Fin du traitement de la couleur blanche");
			Delay.msDelay(5000);
			codeBourrinCarLeRobotAvancePasDroit();
			return;
		}else {//Pression tactile
			System.out.println("Pression tactile détéctée");
			Delay.msDelay(5000);
			agent.getPinces().fermeture();
			agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaGauche(degrestournes);//Direction l'en but adverse
			this.avancerJusquaLenButAdverseEnEvitantLesMurs();//Risque de pousser les autres palets
			//On depose le palet :
			agent.getPinces().ouverture();
			agent.getAvancerOuReculer().reculerPourUnTemps(0.8f); 
			agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaDroite(180);
			degrestournes+=180;
			verifResetDegreCar360();
			System.out.println("Fin du traitement de la pression tactile");
			Delay.msDelay(5000);
			codeBourrinCarLeRobotAvancePasDroit();
			return;
		}
	}
	
	public void verifResetDegreCar360() throws Exception {
		if(degrestournes <= 360) {
			return;
		}else if(degrestournes <= 720){
			degrestournes -= 360;
		}else {
			throw new Exception("Le robot a tourné a plus de 720 degre erreur dans le reset 360");
		}
		
	}
	
	public void fermerLesPinces() {
		agent.getPinces().setPincesOuvertes(true);
		agent.getPinces().fermeture();
	}
	
	public void avancerJusquaLenButAdverseEnEvitantLesMurs() {
		System.out.println("avancerJusquaUneLigneBlancheEtEviterObstacle");
		boolean boucle = true;
		while(boucle) {
			if(!agent.getCapteurUltrasons().murOuRobotDetecte()) {//Pas de mur ni de robot + pas de palet (+tard)
				//On set la couleur
				agent.getCapteurCouleur().setCouleur();
				//On avance si c'est pas la bonne couleur
				if (agent.getCapteurCouleur().getCouleur() != "blanc") {
					agent.getAvancerOuReculer().avancerSynchro();
				}else{
					//On a trouvé la bonne couleur on s'arrete
					agent.getAvancerOuReculer().sarreterSynchro();
					boucle = false;
				}
			}else {//A faire : On doit changer de trajectoire
				agent.getAvancerOuReculer().reculerPourUnTemps(2);
				agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaGauche(degrestournes);
				avancerJusquaLenButAdverseEnEvitantLesMurs();
				break;
			}
		}
	}

}
