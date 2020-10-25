package Moteurs;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public abstract class Deplacement {
	
    private EV3LargeRegulatedMotor leftMotor;
    private EV3LargeRegulatedMotor rightMotor;
    private int speed;
    private int acceleration;
    
    public Deplacement(EV3LargeRegulatedMotor left, EV3LargeRegulatedMotor right) { //Parametre exemple MotorPort:  Motor.B;Motor.C;
    	this.setLeftMotor(left);
    	this.setRightMotor(right);
    	this.setSpeed(1000);//Car probleme de moteur
    	this.setAcceleration(500);
    	this.updateAccelerationSpeedMotors();
    }
    
	public EV3LargeRegulatedMotor getRightMotor() {
		return rightMotor;
	}

	public void setRightMotor(EV3LargeRegulatedMotor rightMotor) {
		this.rightMotor = rightMotor;
	}

	public EV3LargeRegulatedMotor getLeftMotor() {
		return leftMotor;
	}

	public void setLeftMotor(EV3LargeRegulatedMotor leftMotor) {
		this.leftMotor = leftMotor;
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
    
    public void updateAccelerationSpeedMotors() {//Methode pour update l'acceleration et la vitesse des moteurs
    	//En fonction des attributs speed et acceleration deja set
    	this.rightMotor.setSpeed(speed);
    	this.rightMotor.setAcceleration(acceleration);
    	this.leftMotor.setSpeed(speed);
    	this.leftMotor.setAcceleration(acceleration);
    }
    
    public void resetTachoMetre() {
    	leftMotor.resetTachoCount();
        rightMotor.resetTachoCount();
    }



	
}
