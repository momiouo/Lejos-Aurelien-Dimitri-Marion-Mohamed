package Vue;
import Robot.Agent;
import lejos.robotics.Color;

public class Perception {
	protected boolean pressionCapteurTactile;
	protected Color CapteurCouleur;
	protected float distanceCapteurUltrasons;
	protected int niveauBatterie;
	protected boolean unRobotMurEstDetecte;
	protected boolean LigneBlanche;
	private Agent agent;
	
	
	public Perception(Agent agent) {
		this.agent = agent;
	}
	
	public int getNiveauBatterie() {
		return this.niveauBatterie;
	}
	public void setNiveauBatterie(int niveau) {
		this.niveauBatterie=niveau;
	}
	
	public float getDistanceCapteurUltrasons(){
		return this.distanceCapteurUltrasons;
	}
	public Color getCouleurCapteurCouleur() {
		return this.CapteurCouleur;
	}
	public boolean getPressionCapteurTactile() {
		return this.pressionCapteurTactile;
	}
	public void initCapteurs() {//Recupère les résultat des capteurs et set la valeur des attributs correspondants.
		agent.getCapteurCouleur().setCouleur();
		agent.getCapteurTactile().setPression();
		agent.getCapteurUltrasons().setDistance();
	}
}
