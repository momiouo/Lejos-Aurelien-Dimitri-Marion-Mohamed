import lejos.robotics.Color;

public class Perception {
	protected boolean pressionCapteurTactile;
	protected Color CapteurCouleur;
	protected long distanceCapteurUltrasons;
	protected int niveauBatterie;
	protected boolean unRobotMurEstDetecte;
	protected boolean LigneBlanche;
	
	
	public Perception() {
	}
	public int getNiveauBatterie() {
		return this.niveauBatterie;
	}
	public void setNiveauBatterie(int niveau) {
		this.niveauBatterie=niveau;
	}
	
	public long getDistanceCapteurUltrasons(){
		return this.distanceCapteurUltrasons;
	}
	public Color getCouleurCapteurCouleur() {
		return this.CapteurCouleur;
	}
	public boolean getPressionCapteurTactile() {
		return this.pressionCapteurTactile;
	}
	public void initCapteurs() {
		
	}
}
