/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot.subsystems;

import org.usfirst.frc.team5830.robot.Robot;
import org.usfirst.frc.team5830.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * Add your docs here.
 */
public class PIDDriveRotation extends PIDSubsystem {
  /**
   * Add your docs here.
   */
  public PIDDriveRotation() {
    
    // Intert a subsystem name and PID values here
    super("SubsystemName", 0.01, 0, 0);
    setOutputRange(-0.3, 0.3);
  }

  @Override
  public void initDefaultCommand() {
  }

  @Override
  protected double returnPIDInput() {
    return RobotMap.ahrs.getAngle();
  }

  @Override
  protected void usePIDOutput(double output) {
    Robot.swerveDrive.drive(Robot.driveX, Robot.driveY, output);
  }
}
