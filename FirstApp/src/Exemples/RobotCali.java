package Exemples;

import Moteurs.AvancerOuReculer;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;

public class RobotCali {
	
	@SuppressWarnings("deprecation")
	DifferentialPilot pilot;

    public static void main(String[] args) throws InterruptedException {
    	new RobotCali();
    }
    
    public RobotCali() {
    	pilot = new DifferentialPilot(2.2f,5.78f,Motor.C,Motor.B, true); //parameters in inches
    	pilot.travel(50); // 50 cm
    	//pilot.rotate(360);
    }

}