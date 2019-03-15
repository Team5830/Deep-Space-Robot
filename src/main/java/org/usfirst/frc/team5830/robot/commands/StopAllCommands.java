/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Add your docs here.
 */
public class StopAllCommands extends InstantCommand {
  /**
   * Add your docs here.
   */
  public StopAllCommands() {
    super();
    requires(Robot.CYLINDER12SIDEFIRST);
    requires(Robot.CYLINDER12SIDELAST);
    requires(Robot.CYLINDER23REAR);
    requires(Robot.CYLINDERS23Front);
    requires(Robot.CYLINDERMANIPULATOR);
    requires(Robot.GYROSUBSYSTEM);
    requires(Robot.MANIPULATOR);
    requires(Robot.VACUUM);
    requires(Robot.MANIPULATOR2);
    requires(Robot.WHEELDISTANCEPID);
    requires(Robot.auto_LIDAR_Distance_Swerve);
    requires(Robot.ARM);
  }

  @Override
  protected void initialize() {}

}
