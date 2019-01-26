/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot;

/**
 * 
 * @author Hunter P.
 *
 */
public class OI {
	/**
	 * IMPORTANT MESSAGE!!!
	 * In order to enable a SmartDashboard chooser for selecting between flightsticks
	 * and Xbox joystick, all OI input initialization has been moved to commands.
	 * 
	 * Changes can be made in these locations:
	 * Button initialization: JoystickMappingInit.java
	 * Button .whenPressed mapping: JoystickMappingInit.java
	 * Button .whileHeld mapping: JoystickMappingPeriodic.java //NOTE: .whileHeld doesn't work with chooser. Instead use if(button.get) command.start; else command.cancel;
	 * D-Pad/POV: JoystickMappingPeriodic.java
	 * Axes: JoystickMappingPeriodic.java
	 */
		
	/*
	 * Xbox Button mappings:
	 * 1=A
	 * 2=B
	 * 3=X
	 * 4=Y
	 * 5=LB
	 * 6=RB
	 * 7=Back
	 * 8=Start
	 * 9=L Stick (Button)
	 * 10=R Stick (Button)
	 * 
	 * Xbox Axis Mappings:
	 * 0=L X 
	 * 1=L Y
	 * 2=LT
	 * 3=RT
	 * 4=R X
	 * 5=R Y
	 */
	
	public OI() {}
}
