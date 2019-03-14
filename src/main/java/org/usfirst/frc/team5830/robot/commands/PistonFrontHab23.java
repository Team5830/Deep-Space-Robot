/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PistonFrontHab23 extends InstantCommand {

  public PistonFrontHab23() {
    requires(Robot.CYLINDERS23Front);
  }

  @Override
  protected void execute() {
    if (Robot.isPistonFrontExtended){
      SmartDashboard.putString("Status2","True");
      Robot.CYLINDERS23Front.in();
      Robot.isPistonFrontExtended = false;
    } else {
      SmartDashboard.putString("Status2","False");
      Robot.CYLINDERS23Front.out();
      Robot.isPistonFrontExtended = true;
      SmartDashboard.putBoolean("Backup Piston Extended?", Robot.isPistonFrontExtended);
    }
  }
}
