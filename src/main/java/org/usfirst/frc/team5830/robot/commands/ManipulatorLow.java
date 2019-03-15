package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Arlene A.
 * 
 */


public class ManipulatorLow extends Command {
  public ManipulatorLow() {
    requires(Robot.MANIPULATOR);
    }

  // Called just before this Command runs the first time
  @Override
  protected void execute() {
    SmartDashboard.putString("Status Manipulator", "Manipulator to Rocket Low");
    if (Robot.isCargo) {
      if(Robot.isArmAutomatic) Robot.MANIPULATOR.setSetpoint(600);
        Robot.MANIPULATOR.enable();}
        else {  //assumes it is hatchpanel 
          if(Robot.isArmAutomatic) Robot.MANIPULATOR.setSetpoint(700);
          Robot.MANIPULATOR.enable();}
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs (Robot.MANIPULATOR.getSetpoint() - Robot.MANIPULATOR.getPosition()) < Robot.manipulatorError;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    SmartDashboard.putString("Status", "Driving");
  }
}

  
