import Controleur.Action;
import Vue.*;
import Moteurs.*;

public class Agent {
	
	private Action action;
	private Perception perceptionAct;
	private Perception perceptionPrec;
	private AvancerOuReculer avancerOuReculer;
	private TournerOuPivoter tournerOuPivoter;
	private CapteurUltrasons capteurUltrasons;
	private CapteurCouleur capteurCouleur;
	private CapteurTactile capteurTactile;
	
	public Agent() {
		this.action = new Action();
		this.perceptionAct = new Perception();
		this.perceptionPrec = new Perception();
		//Mettre les bons ports pour les capteurs et les moteurs :
		this.avancerOuReculer = new AvancerOuReculer();
		this.tournerOuPivoter = new TournerOuPivoter();
		this.capteurUltrasons = new CapteurUltrasons();
		this.capteurCouleur = new CapteurCouleur();
		this.capteurTactile = new CapteurTactile();
	}
	
	
	
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	public Perception getPerceptionAct() {
		return perceptionAct;
	}
	public void setPerceptionAct(Perception perceptionAct) {
		this.perceptionAct = perceptionAct;
	}
	public Perception getPerceptionPrec() {
		return perceptionPrec;
	}
	public void setPerceptionPrec(Perception perceptionPrec) {
		this.perceptionPrec = perceptionPrec;
	}
	public AvancerOuReculer getAvancerOuReculer() {
		return avancerOuReculer;
	}
	public void setAvancerOuReculer(AvancerOuReculer avancerOuReculer) {
		this.avancerOuReculer = avancerOuReculer;
	}
	public TournerOuPivoter getTournerOuPivoter() {
		return tournerOuPivoter;
	}
	public void setTournerOuPivoter(TournerOuPivoter tournerOuPivoter) {
		this.tournerOuPivoter = tournerOuPivoter;
	}
	public CapteurUltrasons getCapteurUltrasons() {
		return capteurUltrasons;
	}
	public void setCapteurUltrasons(CapteurUltrasons capteurUltrasons) {
		this.capteurUltrasons = capteurUltrasons;
	}
	public CapteurCouleur getCapteurCouleur() {
		return capteurCouleur;
	}
	public void setCapteurCouleur(CapteurCouleur capteurCouleur) {
		this.capteurCouleur = capteurCouleur;
	}
	public CapteurTactile getCapteurTactile() {
		return capteurTactile;
	}
	public void setCapteurTactile(CapteurTactile capteurTactile) {
		this.capteurTactile = capteurTactile;
	}
	
	
	

}
