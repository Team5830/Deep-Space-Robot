package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Arlene A.
 * 
 */


public class ArmFloor extends Command {
  public ArmFloor() {
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
      Robot.ARM.setSetpoint(300); //TODO Calibrate this number or you will kill the robot.
      Robot.MANIPULATOR.setSetpoint(1200);
      Robot.ARM.enable();
      Robot.MANIPULATOR.enable();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs (Robot.ARM.getSetpoint() - Robot.ARM.getPosition()) < Robot.armError &&
    Math.abs (Robot.MANIPULATOR.getSetpoint() - Robot.MANIPULATOR.getPosition()) < Robot.manipulatorError;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    SmartDashboard.putString("Status", "Driving");
  }
}

  