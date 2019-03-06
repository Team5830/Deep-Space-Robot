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
    //TODO Turn ON Mid DIDBoard LED
    //TODO Turn OFF Default, Floor, Low, High DIDBoard LEDs
    if (Robot.isCargo) {
      if(Robot.isArmAutomatic)Robot.ARM.setSetpoint(450);
    Robot.MANIPULATOR.setSetpoint(600);
    Robot.ARM.enable();
    Robot.MANIPULATOR.enable();}
    else {  //assumes it is hatchpanel 
      if(Robot.isArmAutomatic) Robot.ARM.setSetpoint(500);
      Robot.MANIPULATOR.setSetpoint(600);
      Robot.ARM.enable();
      Robot.MANIPULATOR.enable();}

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
    SmartDashboard.putString("Status", "Teleop Driving");
  }
}

  
