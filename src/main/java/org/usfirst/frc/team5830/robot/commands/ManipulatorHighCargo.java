package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Arlene A.
 * 
 */


public class ManipulatorHighCargo extends Command {
  public ManipulatorHighCargo() {
    requires(Robot.MANIPULATOR);
    }

  // Called just before this Command runs the first time
  @Override
  protected void execute() {
    SmartDashboard.putString("Status Maninipulator", "Manipulator with Cargo to Rocket High");
    if(Robot.isArmAutomatic) Robot.MANIPULATOR.setSetpoint(400);
    Robot.MANIPULATOR.enable();
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

  
