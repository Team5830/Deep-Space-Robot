/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class PistonFront extends InstantCommand {

  public PistonFront() {
    requires(Robot.CYLINDERFRONTLEFT);
    requires(Robot.CYLINDERFRONTRIGHT);
  }

  @Override
  protected void execute() {
    if (Robot.isPistonFrontExtended){
      Robot.CYLINDERFRONTLEFT.in();
      Robot.CYLINDERFRONTRIGHT.in();
      Robot.isPistonFrontExtended = true;
    } else {
      Robot.CYLINDERFRONTLEFT.out();
      Robot.CYLINDERFRONTRIGHT.out();
      Robot.isPistonFrontExtended = false;
    }
  }
}