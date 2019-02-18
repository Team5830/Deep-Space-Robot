/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * 
 * @author Hunter P.
 *
 */
public class RobotMap {
	
	
	/**
	 * OUTPUTS
	 */
	
	public static Spark arm = new Spark(8);
	public static Spark vacuum = new Spark(9);
	public static Spark winch = new Spark(10);
	public static Spark manipulator = new Spark(11);
	public static Spark manipulator2 = new Spark(12);

	
	//Pneumatics
	public static Compressor c = new Compressor(0);
	
	
	/**
	 * INPUTS
	 */	
	
	//PDP
	public static PowerDistributionPanel pdp = new PowerDistributionPanel(0); //TODO find CAN bus ID and place in parenthesis, otherwise it will yield a CAN timeout error
	
	/*
	 * Sensors
	 */
	//Pneumatics

	
	 //Front and Rear Solenoids for climbing
	public static DoubleSolenoid frontLeftSolenoid = new DoubleSolenoid(0, 0, 1);
	public static DoubleSolenoid frontRightSolenoid = new DoubleSolenoid(0, 2, 3);
	public static DoubleSolenoid rearSolenoid = new DoubleSolenoid(0, 4, 5);
	public static DoubleSolenoid sideSolenoid = new DoubleSolenoid(0, 6, 7);

	//Manipulator Solenoid
	public static DoubleSolenoid manipulatorSolenoid = new DoubleSolenoid(1, 0, 1);

	//Encoders
	public static Encoder armEncoder = new Encoder(3,4,true);
	//public static Encoder winchEncoder = new Encoder(7,8);
	public static Encoder wheelEncoder1 = new Encoder(1,2);
	public static Encoder manipulatorEncoder = new Encoder(7,8,true);
	
	//Gyroscope
	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	
	//Ultrasonic
	public static final AnalogInput frontLeftSonic = new AnalogInput(4);
	public static final AnalogInput frontRightSonic = new AnalogInput(5);
	public static final AnalogInput leftsideFrontSonic = new AnalogInput(6);
	public static final AnalogInput leftsideRearSonic = new AnalogInput(7);
	
	//LIDAR
	public static final class DIO{
		public static final int LIDAR_PORT = 0;	

		}
}
