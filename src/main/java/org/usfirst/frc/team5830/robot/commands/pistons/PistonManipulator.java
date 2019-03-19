/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot.commands.pistons;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class PistonManipulator extends InstantCommand {

  public PistonManipulator() {
    requires(Robot.CYLINDERMANIPULATOR);
  }

  @Override
  protected void execute() {
    if(Robot.isPistonManipulatorExtended){
      Robot.CYLINDERMANIPULATOR.in();
      Robot.isPistonManipulatorExtended = false;
    } else {
      Robot.CYLINDERMANIPULATOR.out();
      Robot.isPistonManipulatorExtended = true;
    }
  }
}
