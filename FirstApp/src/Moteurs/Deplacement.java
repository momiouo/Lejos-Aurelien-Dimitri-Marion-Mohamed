package Moteurs;

import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Deplacement {
	
    private RegulatedMotor leftMotor;
    private RegulatedMotor rightMotor;
    private int speed;
    private int acceleration;
    
    public Deplacement(RegulatedMotor left, RegulatedMotor right) { //Parametre exemple MotorPort:  Motor.B;Motor.C;
    	this.setLeftMotor(left);
    	this.setRightMotor(right);
    	this.setSpeed(400);
    	this.setAcceleration(800);
    	leftMotor.setSpeed(speed);
        rightMotor.setSpeed(speed);
        leftMotor.setAcceleration(acceleration);
        rightMotor.setAcceleration(acceleration);
    }
    
    public void setAccelerationSpeedMotors() {//Methode pour changer l'acceleration et la vitesse des moteurs
    	//En fonction des attributs speed et acceleration
    	this.rightMotor.setSpeed(speed);
    	this.rightMotor.setAcceleration(acceleration);
    	this.leftMotor.setSpeed(speed);
    	this.leftMotor.setAcceleration(acceleration);
    }
    
    public void resetTachoMetre() {
    	leftMotor.resetTachoCount();
        rightMotor.resetTachoCount();
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
		this.resetTachoMetre();
		//Comment marche le tachometre ?
	}
	
	public void avancerJusquaUneLigne() {
		//Capteur Couleur
	}
	
	public void avancerPourUnTempsDonne(float seconde) {
		this.leftMotor.forward();
		this.rightMotor.forward();
		Delay.msDelay((long) (seconde*1000));
		this.leftMotor.stop();
		this.rightMotor.stop();
	}
	
	public void reculerSurUneDistance() {
		//Tachometre
	}
	
	public void reculerPourUnTemps(float seconde) {
		this.leftMotor.backward();
		this.rightMotor.backward();
		Delay.msDelay((long) (seconde*1000));
		this.leftMotor.stop();
		this.rightMotor.stop();
	}
	
	public void pivoterDunDegreDonne(int degre) {
		this.leftMotor.rotateTo(degre);
		this.rightMotor.rotateTo(degre);
	}
	
	public void pivoterJusquaDetectionDunPalet() {
		//Capteur ultrason
	}
	
	public void pivoterJusquaDetecterUneLigne() {
		//Capteur couleur
	}
	
	public void tournerSurUnTempsEtUneDirection(float seconde, int degre) {
		//Faire touner un moteur plus vite que l'autre.
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(int acceleration) {
		this.acceleration = acceleration;
	}

	
}
