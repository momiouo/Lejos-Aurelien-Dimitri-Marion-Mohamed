package Vue;
import Robot.Agent;
import lejos.robotics.Color;

/**
 * La classe Perception permet d'enregistrer les perceptions du robot à différents moments en utilisant les capteurs.
 * 
 * @author LejosTeam
 *
 */
public class Perception {
	/**
	 * Attribut enregistrant s'il y a une pression ou non.
	 */
	protected boolean pressionCapteurTactile;
	/**
	 * Objet de la classe Color.
	 */
	protected Color CapteurCouleur;
	/**
	 * Attribut enregistrant les distances récupérées.
	 */
	protected float distanceCapteurUltrasons;
	/**
	 * Attribut enregistrant le niveau de batterie restant.
	 */
	protected int niveauBatterie;
	/**
	 * Attribut enregistrant si un mur ou un robot est détéctée.
	 */
	protected boolean unRobotMurEstDetecte;
	/**
	 * Attribut enregistrant si une ligne blanche est détéctée.
	 */
	protected boolean LigneBlanche;
	/**
	 * Objet de la classe Agent.
	 */
	private Agent agent;
	
	
	/**
	 * @param agent
	 */
	public Perception(Agent agent) {
		this.agent = agent;
	}
	
	/**
	 * Retourne l'attribut niveauBatterie.
	 * 
	 * @return
	 */
	public int getNiveauBatterie() {
		return this.niveauBatterie;
	}
	/**
	 * Met à jour l'attribut niveauBatterie.
	 * 
	 * @param niveau
	 */
	public void setNiveauBatterie(int niveau) {
		this.niveauBatterie=niveau;
	}
	
	/**
	 * Méthode qui permet de récupérer la distance récupérée par le capteur à ultrasons.
	 * 
	 * @return
	 */
	public float getDistanceCapteurUltrasons(){
		return this.distanceCapteurUltrasons;
	}

	/**
	 * Méthode qui permet de récupérer la couleur récupérée par le capteur couleur.
	 * 
	 * @return
	 */
	public Color getCouleurCapteurCouleur() {
		return this.CapteurCouleur;
	}

	/**
	 * Méthode qui permet de récupérer la pression détéctée par le capteur tactile.
	 * 
	 * @return
	 */
	public boolean getPressionCapteurTactile() {
		return this.pressionCapteurTactile;
	}

	/**
	 * Méthode qui permet d’initialiser les valeurs des attributs : distance, couleur, pression. 
	 * En utilisant les classes correspondant à chaque capteur.
	 */
	public void initCapteurs() {
		agent.getCapteurCouleur().setCouleur();
		agent.getCapteurTactile().setPression();
		agent.getCapteurUltrasons().setDistance();
	}
}
