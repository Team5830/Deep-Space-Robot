package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Arlene A.
 * 
 */


public class ManipulatorMiddle extends Command {
  public ManipulatorMiddle() {
    requires(Robot.MANIPULATOR);
    }

  // Called just before this Command runs the first time
  @Override
  protected void execute() {
    SmartDashboard.putString("Status", "Manipuplator to Rocket Middle");
    if (Robot.isCargo) {
        Robot.MANIPULATOR.setSetpoint(600); //TODO Calibrate this number or you will kill the robot.
        Robot.MANIPULATOR.enable();}
        else {  //assumes it is hatchpanel 
          Robot.MANIPULATOR.setSetpoint(700); //TODO Calibrate this number or you will kill the robot.
          Robot.MANIPULATOR.enable();}
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs (Robot.MANIPULATOR.getSetpoint() - Robot.MANIPULATOR.getPosition()) < 250;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    SmartDashboard.putString("Status", "Teleop Driving");
  }
}

  
