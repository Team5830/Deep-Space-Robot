package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArmHighHatchP extends Command {
  public ArmHighHatchP() {
    requires(Robot.ARM);
    }

  // Called just before this Command runs the first time
  @Override
  protected void execute() {
    SmartDashboard.putString("Status", "Hatch to Rocket High");
    Robot.ARM.setSetpoint(7000); //TODO Calibrate this number or you will kill the robot.
    Robot.ARM.enable();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs (Robot.ARM.getSetpoint() - Robot.ARM.getPosition()) < 250;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    SmartDashboard.putString("Status", "Teleop Driving");
  }
}

  
