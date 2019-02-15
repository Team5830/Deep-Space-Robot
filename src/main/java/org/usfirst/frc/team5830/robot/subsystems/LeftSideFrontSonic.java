/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team5830.robot.RobotMap;

/**
 * Add your docs here.
 */
public class LeftSideFrontSonic extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

    // TODO - determine how to convert voltage to distance from spec sheet
private static final double VOLTS_TO_DIST = 1.0;

  public static double getVoltage() {
    return RobotMap.leftsideFrontSonic.getVoltage();
  }

  public static double getDistance() {
  return getVoltage() * VOLTS_TO_DIST;
}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
