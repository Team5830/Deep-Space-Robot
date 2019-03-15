package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Arlene A.
 * 
 */


public class ArmLoadingStation extends Command {
  public ArmLoadingStation() {
    requires(Robot.ARM);
    requires(Robot.MANIPULATOR);
    }

  // Called just before this Command runs the first time
  @Override
  protected void execute() {
    SmartDashboard.putString("Arm Status", "Arm Low");
    //TODO Turn ON Low DIDBoard LED
    //TODO Turn OFF Default, Floor, Mid, High DIDBoard LEDs
    if(Robot.isArmAutomatic) {
      Robot.ARM.setSetpoint(600); //TODO Calibrate this number or you will kill the robot.
      Robot.MANIPULATOR.setSetpoint(800);
      Robot.ARM.enable();
      Robot.MANIPULATOR.enable();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs (Robot.ARM.getSetpoint() - Robot.ARM.getPosition()) < 250 &&
    Math.abs (Robot.MANIPULATOR.getSetpoint() - Robot.MANIPULATOR.getPosition()) < 250;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    SmartDashboard.putString("Status", "Driving");
  }
}

  
