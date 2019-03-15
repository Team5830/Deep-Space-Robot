/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot.commands;

/**
 * 
 * @author Arlene A.
 * 
 */


import org.usfirst.frc.team5830.robot.Robot;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GamePieceDrop extends InstantCommand {
  public GamePieceDrop() {
    requires(Robot.VACUUM);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putString("Status", "Drop");
    Robot.VACUUM.stop();
  }


  // Called once after isFinished returns true
  @Override
  protected void end() {
    SmartDashboard.putString("Status", "Idle");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.VACUUM.stop();
  }
}
