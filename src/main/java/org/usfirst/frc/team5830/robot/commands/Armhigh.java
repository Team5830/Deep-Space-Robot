package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Armhigh extends Command {
  public Armhigh() {
    
      requires(Robot.ELEVATOR);
    }

  // Called just before this Command runs the first time
  @Override
  protected void execute() {
    SmartDashboard.putString("Status", "Hatch to Portal");
     Robot.ELEVATOR.setSetpoint(6500);
    Robot.ELEVATOR.enable();
  }

  // Called repeatedly when this Command is scheduled to run
 
  

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(Robot.ELEVATOR.getSetpoint() - (Robot.ELEVATOR).getPosition()) < 450;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    SmartDashboard.putString("Status", "Teleop Driving");
  }

  
}