package Moteurs;

import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public abstract class Deplacement {
	
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
