/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class PistonRear extends InstantCommand {

  private boolean isExtended = false;

  public PistonRear() {
    requires(Robot.CYLINDERREAR);
  }

  @Override
  protected void execute() {
    if(isExtended){
      Robot.CYLINDERREAR.in();
      isExtended = true;
    } else {
      Robot.CYLINDERREAR.out();
      isExtended = false;
    }
  }
}
