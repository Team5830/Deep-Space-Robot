/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot.commands.pixy;

import org.usfirst.frc.team5830.robot.commands.TimerDelay;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PixyAlign extends CommandGroup {
  /**
   * Add your docs here.
   */
  public PixyAlign() {
    addSequential(new PixyLineRotation());
    addSequential(new TimerDelay());
    addSequential(new PixyLineRotation());
    addSequential(new TimerDelay());
    addSequential(new PixyLineStrafe());
    addSequential(new TimerDelay());
    addSequential(new PixyLineStrafe());
    addSequential(new TimerDelay());
    addSequential(new PixyLineRotation());
  }
}
