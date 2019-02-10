/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PixyLineStrafe extends Command {

  //This is the center pixel of the tracking area (X direction)
  private double trackingCenterPoint = 318; //TODO Verify correct
  private boolean isItFinished = false;

  public PixyLineStrafe() {
    requires(Robot.swerveDrive);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    isItFinished  = false;
    if(Math.abs(Robot.pixy1x0 - trackingCenterPoint) > Robot.pixy2LineStrafeError){ //If not centered
      while(Robot.pixy1x0 < trackingCenterPoint){ //To the left of center
        Robot.swerveDrive.drive(0.15, 0, 0);
      }
      while(Robot.pixy1x0 > trackingCenterPoint){ //To the right of center
        Robot.swerveDrive.drive(-0.15, 0, 0);
      }
    } else {
      Robot.swerveDrive.drive(0, 0, 0);
      isItFinished = true;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isItFinished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
