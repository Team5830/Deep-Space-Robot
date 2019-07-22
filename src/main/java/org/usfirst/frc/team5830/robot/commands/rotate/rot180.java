/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot.commands.rotate;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class rot180 extends Command {
  public rot180() {
    requires(Robot.swerveDrive);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.PIDDRIVEROTATION.setSetpoint(180);
    Robot.PIDDRIVEROTATION.enable();
  }

  @Override
  protected boolean isFinished() {
    return Math.abs(Robot.PIDDRIVEROTATION.getSetpoint() - Robot.PIDDRIVEROTATION.getPosition()) < 5 || Robot.stopRotate;
  }

  @Override
  protected void end() {
    Robot.stopRotate = false;
    Robot.PIDDRIVEROTATION.disable();
  }

  @Override
  protected void interrupted() {
    Robot.stopRotate = false;
    Robot.PIDDRIVEROTATION.disable();
  }
}
