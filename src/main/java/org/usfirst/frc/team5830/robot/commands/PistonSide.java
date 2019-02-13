/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class PistonSide extends InstantCommand {

  public PistonSide() {
    requires(Robot.CYLINDERREAR);
  }

  @Override
  protected void execute() {
<<<<<<< HEAD:src/main/java/org/usfirst/frc/team5830/robot/commands/RaiseFront.java
    if (isExtended){
        Robot.CYLINDERFRONT.in();
        isExtended = true;
      } else {
        Robot.CYLINDERFRONT.out();
        isExtended = false;
      }
      }
=======
    if(Robot.isPistonSideExtended){
      Robot.CYLINDERSIDE.in();
      Robot.isPistonSideExtended = true;
    } else {
      Robot.CYLINDERSIDE.out();
      Robot.isPistonSideExtended = false;
    }
  }
>>>>>>> 9cf925ddf8d37bc8508e46032d8ee4eece795eef:src/main/java/org/usfirst/frc/team5830/robot/commands/PistonSide.java
}
