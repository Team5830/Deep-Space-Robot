/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Victor;

/**
 * 
 * @author Hunter P.
 *
 */
public class RobotMap {
	
	
	/**
	 * OUTPUTS
	 */
	
	public static Spark stageOne = new Spark(8);
	public static Victor powerCube = new Victor(9);
	public static Spark winch = new Spark(10);
	
	
	/**
	 * INPUTS
	 */	
	
	//PDP
	public static PowerDistributionPanel pdp = new PowerDistributionPanel(0); //TODO find CAN bus ID and place in parenthesis, otherwise it will yield a CAN timeout error
	
	/*
	 * Sensors
	 */

	//Encoders
	public static Encoder elevatorEncoder = new Encoder(5,6,true);
	public static Encoder winchEncoder = new Encoder(7,8);
	public static Encoder wheelEncoder1 = new Encoder(1,2);
	
	//Gyroscope
	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	
	//LIDAR
	public static final class DIO{
		public static final int LIDAR_PORT = 0;	

		
	}
}
