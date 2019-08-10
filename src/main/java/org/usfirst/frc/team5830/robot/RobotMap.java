/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * 
 * @author Hunter P. and Arlene A.
 *
 */
public class RobotMap {
	
	
	/**
	 * OUTPUTS
	 */
	
	public static Spark arm = new Spark(11);
	public static Spark vacuum = new Spark(10);
	//public static Spark winch = new Spark(10);
	public static Spark manipulator = new Spark(9);
	public static Spark manipulator2 = new Spark(12);

	
	//Pneumatics
	public static Compressor c = new Compressor(0);

	//Front and Rear Solenoids for climbing
	public static DoubleSolenoid frontLeft23Solenoid = new DoubleSolenoid(0, 2, 3);
	public static DoubleSolenoid frontRight23Solenoid = new DoubleSolenoid(1, 2, 3);
	public static DoubleSolenoid side12firstSolenoid = new DoubleSolenoid(0, 4, 5);
	public static DoubleSolenoid side12lastSolenoid = new DoubleSolenoid(0, 0, 1);
	public static DoubleSolenoid rear23Solenoid = new DoubleSolenoid(0, 6, 7);

	//Manipulator Solenoid
	public static DoubleSolenoid manipulatorSolenoid = new DoubleSolenoid(1, 0, 1);
	
	
	/**
	 * INPUTS
	 */	
	
	//PDP
	public static PowerDistributionPanel pdp = new PowerDistributionPanel(10); //find CAN bus ID and place in parenthesis, otherwise it will yield a CAN timeout error
	
	/*
	 * Sensors
	 */

	//Encoders
	public static Encoder armEncoder = new Encoder(5,6);
	public static Encoder manipulatorEncoder = new Encoder(7,8);
	
	//Gyroscope
	//public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	public static AHRS ahrs;// = new AHRS(SerialPort.Port.kUSB1);
	
	//LIDAR
	public static final class DIO{
		public static final int LIDAR_PORT = 0;	
	}
}
