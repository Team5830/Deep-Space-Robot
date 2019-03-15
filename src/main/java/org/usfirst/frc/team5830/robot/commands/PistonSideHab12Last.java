/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class PistonSideHab12Last extends InstantCommand {

  public PistonSideHab12Last() {
    requires(Robot.CYLINDER12SIDELAST);
  }

  @Override
  protected void execute() {
    if(Robot.isPiston12LastExtended){
      Robot.CYLINDER12SIDELAST.in();
    } else {
      Robot.CYLINDER12SIDELAST.out();
    }
  }
}
