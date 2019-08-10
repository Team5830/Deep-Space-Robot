package org.usfirst.frc.team5830.robot;

//Houses commonly changed constants

public class Constants {

	/*
		JOYSTICK INPUT PARAMETERS
	*/
    //Xbox controller stick deadzone size. 1 is entire range, 0 is disabled, closer to zero means less deadzone
	public static final double xboxStickDeadzone = 0.1;
	//Xbox controller trigger deadzone size. 1 is entire range, 0 is disabled, closer to zero means less deadzone
	public static final double xboxTriggerDeadzone = 0.2;

	/*
		ARM SETTINGS
	*/
	//Maximum arm speed up
	public static final double maxArmSpeedUp = 1; //Between 0 and 1. NEGATIVE NUMBERS WILL NOT WORK!
	//Maximum arm speed down
	public static final double maxArmSpeedDown = -.5; //Between -1 and 0. POSITIVE NUMBERS WILL NOT WORK!
	//Arm Error
	public static final int armError = 100;
	//Highest arm position
	public static final int armMaxHeight = 900;
	//Arm setpoint ramp speed
	public static final float armRampSpeed = 10; //How much to step setpoint up/down each tick

	/*
		MANIPULATOR SETTINGS
	*/
	//Maximum manipulator speed up
	public static final double maxManipulatorSpeedUp = .4; //Between 0 and 1. NEGATIVE NUMBERS WILL NOT WORK!
	//Maximum manipulator speed up
	public static final double maxManipulatorSpeedDown = -0.7; //Between -1 and 0. POSITIVE NUMBERS WILL NOT WORK!
	//Maximum Manipulator Rotation
	public static final int manipulatorMaxRotation = 1200;
	//Manipulator Error
	public static final int manipulatorError = 100;
	//Manipulator setpoint ramp speed
	public static final float manipulatorRampSpeed = 10; //How much to step setpoint up/down each tick

	/*
		DRIVETRAIN SETTINGS
	*/
	//Time alotted to allow the PIDDriveRotation to self-correct before giving rotation control back to the driver
	public static final int pidRotCorrectionTime = 500; //Milliseconds

	/*
		SENSOR PARAMETERS
	*/
	//Pixy 2 line margin of error
	public static final double pixy2LineRotationError = 1; //Error in pixels allowed when aligning
	//Pixy 2 line margin of error
	public static final double pixy2LineStrafeError = 1; //Error in pixels allowed when aligning
	//Margin of error allowed when using LIDAR for finding distance
	public static final int lidarError = 3; //Inches
	//Distance robot needs to be from HAB to climb. 
	public static final double habDistance = 4; //inches
	//Margin of error allowed when using Ultrasonic for finding distance
	public static final int ultrasonicError = 1; //Inches

}