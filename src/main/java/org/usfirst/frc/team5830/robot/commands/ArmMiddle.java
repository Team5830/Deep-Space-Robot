package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArmMiddle extends Command {
  public ArmMiddle() {
    requires(Robot.ARM);
    }

  // Called just before this Command runs the first time
  @Override
  protected void execute() {
    SmartDashboard.putString("Arm Status", "Arm Middle");
    if(Robot.isArmAutomatic){
      Robot.armSetpointRaw = 575;
      Robot.manipulatorSetpointRaw = 450;
      Robot.ARM.enable();
      Robot.MANIPULATOR.enable();
      Robot.armCommandRunning = true;
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
    Robot.armCommandRunning = false;
  }
}

  
