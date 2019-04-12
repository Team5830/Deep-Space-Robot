/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot.commands.pixy;

import org.usfirst.frc.team5830.robot.Robot;
import org.usfirst.frc.team5830.robot.Constants;

import edu.wpi.first.wpilibj.command.Command;

public class PixyLineRotation extends Command {

  private boolean isItFinished = false;

  public PixyLineRotation() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Math.abs(Robot.pixy1x0 - Robot.pixy1x1) > Constants.pixy2LineRotationError){
     if(Robot.pixy1x0 < Robot.pixy1x1){
        Robot.swerveDrive.drive(0.2, 0, 0.15);
      }
      if(Robot.pixy1x0 > Robot.pixy1x1){
        Robot.swerveDrive.drive(-0.2, 0, -0.15);
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
    isItFinished = false;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    isItFinished = false;
  }
}
