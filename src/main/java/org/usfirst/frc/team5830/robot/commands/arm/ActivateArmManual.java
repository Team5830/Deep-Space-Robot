/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot.commands.arm;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Add your docs here.
 */
public class ActivateArmManual extends InstantCommand {
  /**
   * Add your docs here.
   */
  public ActivateArmManual() {
    super();
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.isArmAutomatic = false;
  }

}
