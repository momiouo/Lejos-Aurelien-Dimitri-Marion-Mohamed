package MainTest;

import Robot.Agent;
import lejos.hardware.Button;
import lejos.utility.Delay;

public class TestBourrinAvecCouleur {

	public static void main(String[] args) throws Exception {
		
		//Pour ce test il faut indiquer la couleur du camp adverse au lancement et etre sur que le vert et le bleu se detecte bien et voir le chevauchement du rouge et du jaune sur le bleu et le vert.

		TestBourrinAvecCouleur test = new TestBourrinAvecCouleur();
		test.start();
		//test.fermerLesPinces();
		test.codeBourrinCarLeRobotAvancePasDroit();
		
		System.out.println("Fin du test");
		Delay.msDelay(20000);
		
	}
	
	private Agent agent;
	private String couleurprec; // blanc<-vert<-Noir-> Bleu -> Blanc
	
	public void start() {
		agent = new Agent();
		couleurprec = "blanc";
		System.out.println("Press enter to run testBourrin...");
		Button.ENTER.waitForPressAndRelease();
	}
	
	public void codeBourrinCarLeRobotAvancePasDroit() throws Exception {	
		boolean loop = true;
		agent.getPerceptionAct().initCapteurs();
		
		agent.getPinces().ouverture();
		agent.getAvancerOuReculer().avancerSynchro();

		while(loop) {
			detectCouleur();
			if(agent.getCapteurCouleur().getCouleur() == "blanc") {
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
		if(agent.getAction().robotEstBloque()) {
			System.out.println("Le Robot Est Bloqué");
			Delay.msDelay(5000);
			agent.getAvancerOuReculer().reculerPourUnTemps(2);
			agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaDroite(45);
			System.out.println("Fin du débloquage");
			Delay.msDelay(5000);
			codeBourrinCarLeRobotAvancePasDroit();
			return;
		}else if(agent.getCapteurCouleur().couleurEstBlanche()) {
			System.out.println("Couleur blanche détéctée sans palet");
			Delay.msDelay(5000);
			agent.getAvancerOuReculer().reculerPourUnTemps(2);
			agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaDroite(45);
			System.out.println("Fin du traitement de la couleur blanche");
			Delay.msDelay(5000);
			codeBourrinCarLeRobotAvancePasDroit();
			return;
		}else {//Pression tactile
			System.out.println("Pression tactile détéctée");
			Delay.msDelay(5000);
			agent.getPinces().fermeture();
			this.avancerJusquaLenButAdverseEnEvitantLesMurs();//Risque de pousser les autres palets
			//On depose le palet :
			agent.getPinces().ouverture();
			agent.getAvancerOuReculer().reculerPourUnTemps(2f); 
			agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaDroite(180); 
			System.out.println("Fin du traitement de la pression tactile");
			Delay.msDelay(5000);
			codeBourrinCarLeRobotAvancePasDroit();
			return;
		}
	}
	
	public void fermerLesPinces() {
		agent.getPinces().setPincesOuvertes(true);
		agent.getPinces().fermeture();
	}
	
	public void avancerJusquaLenButAdverseEnEvitantLesMurs() throws Exception {
		System.out.println("avancerJusquaUneLigneBlancheEtEviterObstacle");
		boolean boucle = true;
		while(boucle) {
			if(agent.getCapteurUltrasons().murOuRobotDetecte()) {//Cas d'arret 1
				agent.getAvancerOuReculer().sarreterSynchro();
				robotEstBloque();
				break;
			}else {
				detectCouleur();
				if (agent.getCapteurCouleur().getCouleur() == "blanc") {//Cas d'arret 2
					agent.getAvancerOuReculer().sarreterSynchro();
					
					if(this.couleurprec == "bleu") {
						System.out.println("On est dans le camp côté bleu");
						Delay.msDelay(3000);
						break;
					}else {
						System.out.println("On est dans le camp côté vert (Pas bon)");
						Delay.msDelay(3000);
						agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaDroite(180);
						agent.getAvancerOuReculer().avancerPourUnTemps(1);
						avancerJusquaLenButAdverseEnEvitantLesMurs();
						break;
					}
					
				}
				agent.getAvancerOuReculer().avancerSynchro();
			}
		}
	}

	public void robotEstBloque() throws Exception {
		System.out.println("Le Robot Est Bloqué avec un palet");
		Delay.msDelay(5000);
		agent.getAvancerOuReculer().reculerPourUnTemps(1.5f);
		agent.getTournerOuPivoter().pivoterAvecDeuxRouesVersLaDroite(45);
		avancerJusquaLenButAdverseEnEvitantLesMurs();
	}
	
	public void detectCouleur() {
		agent.getCapteurCouleur().setCouleur();
		if(agent.getCapteurCouleur().getCouleur() == "noir") {
			couleurprec = "noir";
		}
		if(agent.getCapteurCouleur().getCouleur() == "bleu") {
			System.out.println("Couleur bleu enregistree");
			Delay.msDelay(3000);
			couleurprec = "bleu";
		}
		if(agent.getCapteurCouleur().getCouleur() == "vert") {
			System.out.println("Couleur verte enregistree");
			Delay.msDelay(3000);
			couleurprec = "vert";
		}
	}

}
