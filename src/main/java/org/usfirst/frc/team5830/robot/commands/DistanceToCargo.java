/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DistanceToCargo extends Command {
  public DistanceToCargo() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.auto_LIDAR_Distance_Swerve.setSetpoint(24);
    Robot.auto_LIDAR_Distance_Swerve.enable();
    Robot.swerveDrive.drive(0, Robot.pidOutputRobot, 0);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (Math.abs(Robot.auto_LIDAR_Distance_Swerve.getSetpoint() - Robot.auto_LIDAR_Distance_Swerve.getPosition()) < 3);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {  Robot.swerveDrive.drive(0, 0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {  Robot.swerveDrive.drive(0, 0, 0);
  }
}
