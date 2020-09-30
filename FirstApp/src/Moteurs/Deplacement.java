package Moteurs;

import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;

public class Deplacement {
	
    private RegulatedMotor leftMotor;
    private RegulatedMotor rightMotor;
    
    public Deplacement(RegulatedMotor left, RegulatedMotor right) { //Parametre exemple MotorPort:  Motor.B;Motor.C;
    	this.setLeftMotor(left);
    	this.setRightMotor(right);
    }

	public RegulatedMotor getRightMotor() {
		return rightMotor;
	}

	public void setRightMotor(RegulatedMotor rightMotor) {
		this.rightMotor = rightMotor;
	}

	public RegulatedMotor getLeftMotor() {
		return leftMotor;
	}

	public void setLeftMotor(RegulatedMotor leftMotor) {
		this.leftMotor = leftMotor;
	}

	public void avancerSurUneDistance() {
		
	}
	
	public void avencerJusquaUneLigne() {
		
	}
	
	public void avancerPourUnTempsDonne(float seconde) {
		
	}
	
	public void reculerSurUneDistance() {
		
	}
	
	public void reculerPourUnTemps(float seconde) {
		
	}
	
	public void pivoterDunDegreDonne(int degre) {
		
	}
	
	public void pivoterJusquaDetectionDunPalet() {
		
	}
	
	public void pivoterJusquaDetecterUneLigne() {
		
	}
	
	public void tournerSurUnTempsEtUneDirection(float seconde, int degre) {
		
	}

	
}
