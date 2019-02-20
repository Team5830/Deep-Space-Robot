/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class PistonSideHab12First extends InstantCommand {

  public PistonSideHab12First() {
    requires(Robot.CYLINDER12SIDEFIRST);
  }

  @Override
  protected void execute() {
    if(Robot.isPistonSideExtended){
      Robot.CYLINDER12SIDEFIRST.in();
      Robot.isPistonSideExtended = false;
    } else {
      Robot.CYLINDER12SIDEFIRST.out();
      Robot.isPistonSideExtended = true;
    }
  }
}
