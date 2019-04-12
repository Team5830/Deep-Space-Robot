package org.usfirst.frc.team5830.robot.commands.arm;

import org.usfirst.frc.team5830.robot.Robot;
import org.usfirst.frc.team5830.robot.Constants;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Arlene A.
 * 
 */


public class ArmHatchLoadingStation extends Command {
  public ArmHatchLoadingStation() {
    requires(Robot.ARM);
    requires(Robot.MANIPULATOR);
    }

  // Called just before this Command runs the first time
  @Override
  protected void execute() {
    SmartDashboard.putString("Arm Status", "Arm Low");
    if(Robot.isArmAutomatic) {
      Robot.armSetpointRaw = 0;
      Robot.manipulatorSetpointRaw = 650;
      Robot.ARM.enable();
      Robot.MANIPULATOR.enable();
      Robot.armCommandRunning = true;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs (Robot.ARM.getSetpoint() - Robot.ARM.getPosition()) < Constants.armError &&
    Math.abs (Robot.MANIPULATOR.getSetpoint() - Robot.MANIPULATOR.getPosition()) < Constants.manipulatorError;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    SmartDashboard.putString("Status", "Driving");
    Robot.armCommandRunning = false;
  }
}

  
