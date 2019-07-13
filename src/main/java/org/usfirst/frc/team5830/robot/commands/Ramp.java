/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Constants;
import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class Ramp extends InstantCommand {

  private long lastTime;
  
  public Ramp() {
  }

  @Override
  protected void execute() {
    if(Robot.armSetpointRaw < Robot.ARM.getSetpoint()){
			Robot.ARM.setSetpoint(Math.min(Robot.armSetpointRaw, Robot.ARM.getSetpoint() + (System.currentTimeMillis() - lastTime) * (Constants.armRampSpeed/1000)));
		} else {
			Robot.ARM.setSetpoint(Math.max(Robot.armSetpointRaw, Robot.ARM.getSetpoint() - (System.currentTimeMillis() - lastTime) * (Constants.armRampSpeed/1000)));
    }

    if(Robot.manipulatorSetpointRaw < Robot.MANIPULATOR.getSetpoint()){
			Robot.MANIPULATOR.setSetpoint(Math.min(Robot.manipulatorSetpointRaw, Robot.MANIPULATOR.getSetpoint() + (System.currentTimeMillis() - lastTime) * (Constants.manipulatorRampSpeed/1000)));
		} else {
			Robot.MANIPULATOR.setSetpoint(Math.max(Robot.manipulatorSetpointRaw, Robot.MANIPULATOR.getSetpoint() - (System.currentTimeMillis() - lastTime) * (Constants.manipulatorRampSpeed/1000)));
    }
		lastTime = System.currentTimeMillis();
  }
}
