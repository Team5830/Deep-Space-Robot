/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;
import org.usfirst.frc.team5830.robot.Constants;
import edu.wpi.first.wpilibj.command.Command;

public class ArmManual extends Command {
  public ArmManual() {
    requires(Robot.ARM);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.armSetpointRaw = (-JoystickMappingInit.rightJoy.getRawAxis(3) + 1) * (Constants.armMaxHeight / 2);
    Robot.ARM.enable();

    Robot.manipulatorSetpointRaw = (JoystickMappingInit.leftJoy.getRawAxis(3) + 1) * (Constants.manipulatorMaxRotation / 2);
    Robot.MANIPULATOR.enable();
  }

  @Override
  protected boolean isFinished() {
    return Robot.isArmAutomatic;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
