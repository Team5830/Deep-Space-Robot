/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;
import org.usfirst.frc.team5830.robot.subsystems.MathHelper;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

public class ArmManual extends Command {
  public ArmManual() {
    requires(Robot.ARM);
  }

  double protectedArm = 0;
  double protectedManip = 0;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //Raw throttle input (-1 to 1) needs conversion for arm setpoint. Math adds 1 to the raw joystick to give it 0 to 2.
    //Multiplies that by half of the arm's max height so when the throttle is at full (2) the arm is at max.
    //double valLeft = -(Robot.xbox.getY(Hand.kLeft) + 1) / 2;
    //double valArm = MathHelper.map(valLeft, 0, 1, 0, Robot.armMaxHeight);
    Robot.ARM.setSetpoint((-Robot.rightJoy.getRawAxis(3) + 1) * (Robot.armMaxHeight / 2));

   /* if(protectedArm < Robot.xbox.getY(Hand.kLeft)){
      protectedArm = (protectedArm + 0.1) * (Robot.armMaxHeight / 2);
    } else if(protectedArm > Robot.xbox.getY(Hand.kLeft)) {
      protectedArm = (protectedArm - 0.1)  * (Robot.armMaxHeight / 2);
    }

    if(protectedManip < Robot.xbox.getY(Hand.kLeft)){
      protectedManip = protectedManip + 0.1;
    } else if(protectedManip > Robot.xbox.getY(Hand.kLeft)) {
      protectedManip = protectedManip - 0.1;
    }*/

    //Robot.ARM.setSetpoint(Robot.xbox.getY(Hand.kLeft));
    //Robot.ARM.setSetpoint(protectedArm);
    //Robot.ARM.setSetpoint(setpoint);
    Robot.ARM.enable();

    

    //double valRight = (Robot.xbox.getY(Hand.kRight) + 1) / 2;
    //double valManip = MathHelper.map(valRight, 0, 1, 0, Robot.manipulatorMaxRotation);
    Robot.MANIPULATOR.setSetpoint((Robot.leftJoy.getRawAxis(3) + 1) * (Robot.manipulatorMaxRotation / 2));
    //Robot.MANIPULATOR.setSetpoint(protectedManip);
    Robot.MANIPULATOR.enable();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.isArmAutomatic;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
