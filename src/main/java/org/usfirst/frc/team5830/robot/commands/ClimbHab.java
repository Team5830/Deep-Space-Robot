/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ClimbHab extends InstantCommand {

  public ClimbHab() {
    requires(Robot.swerveDrive);
    requires(Robot.CYLINDERFRONTRIGHT);
    requires(Robot.CYLINDERFRONTLEFT);
    requires(Robot.CYLINDERREAR);
    requires(Robot.CYLINDERSIDE);
  }

  @Override
  protected void execute() {//Assumes robot already aligned to side of hab.

    /*
      HOW THIS CODE WORKS:
      Every time this command is called to start (by a button), it looks at Robot.climbHabStepCount to 
      see how many previous steps have been run. It runs down the list of "cases". Once it find a "case"
      that matches the number, it runs the code in that section. After it runs that code, it adds 1 to
      Robot.climbHabStepCount so next time it goes onto the next step.
    */

    new Thread() {//This is the easiest way to get sections of code to wait without the entire robot becoming unresponsive.
      public void run() {
        switch(Robot.climbHabStepCount){
          case 1: { //Extend side piston
            Robot.CYLINDERSIDE.out();
            SmartDashboard.putString("Status", "Raising Robot from Side");
            SmartDashboard.putString("Climb Next Step", "Drive Sideways");
          } 
          
          case 2: {//Drive Sideways so two wheels are on platform
            Robot.swerveDrive.drive(0.15, 0, 0); //Drives Sideways
            Timer.delay(2);
            Robot.swerveDrive.drive(0, 0, 0);
            SmartDashboard.putString("Status", "Driving Sideways");
            SmartDashboard.putString("Climb Next Step", "Raise Robot from Other Side");
          }

          case 3: {//Retracts piston near hab, extends one away from hab to level robot
            Robot.CYLINDERSIDE.in();
            Robot.CYLINDERFRONTRIGHT.out();
            SmartDashboard.putString("Status", "Raising Robot from Side");
            SmartDashboard.putString("Climb Next Step", "Drive Onto Platform");
          }

          case 4: {//Drives Sideways
            Robot.swerveDrive.drive(0.15, 0, 0);
            Timer.delay(3);
            Robot.swerveDrive.drive(0, 0, 0);
            SmartDashboard.putString("Status", "Driving Onto Platform");
            SmartDashboard.putString("Climb Next Step", "Retract Last Cylinder");
          }

          case 5: {//Retracts Cylinder
            Robot.CYLINDERFRONTRIGHT.in();
            SmartDashboard.putString("Status", "Retracting Last Cylinder");
            SmartDashboard.putString("Climb Next Step", "Drive Fully Onto Platform");
          }

          case 6: {//Drives Sideways
            Robot.swerveDrive.drive(0.15, 0, 0);
            Timer.delay(3);
            Robot.swerveDrive.drive(0, 0, 0);
            SmartDashboard.putString("Status", "Driving Fully Onto Platform");
            SmartDashboard.putString("Climb Next Step", "Drive Towards Hab 3");
          }

          case 7: {//Drives Forwards so bumpers are against hab 3
            Robot.swerveDrive.drive(0, 0.15, 0);
            Timer.delay(2);
            Robot.swerveDrive.drive(0, 0, 0);
            SmartDashboard.putString("Status", "Driving Towards Hab 3");
            SmartDashboard.putString("Climb Next Step", "Raise Robot from Front");
          }

          case 8: { //Raises front of robot
            Robot.CYLINDERFRONTLEFT.out();
            Robot.CYLINDERFRONTRIGHT.out();
            SmartDashboard.putString("Status", "Raising Robot from Front");
            SmartDashboard.putString("Climb Next Step", "Drive Forward");
          }

          case 9: {//Drive forwards so two wheels are on platform
            Robot.swerveDrive.drive(0, 0.15, 0);
            Timer.delay(2);
            Robot.swerveDrive.drive(0, 0, 0);
            SmartDashboard.putString("Status", "Driving Forward");
            SmartDashboard.putString("Climb Next Step", "Raise Robot from Rear");
          }

          case 10: {//Raises rear of robot
            Robot.CYLINDERFRONTLEFT.in();
            Robot.CYLINDERFRONTRIGHT.in();
            Robot.CYLINDERREAR.out();
            SmartDashboard.putString("Status", "Raising Robot from Rear");
            SmartDashboard.putString("Climb Next Step", "Drive Onto Platform");
          }

          case 11: {//Drive forwards so four wheels are on platform
            Robot.swerveDrive.drive(0, 0.15, 0);
            Timer.delay(3);
            Robot.swerveDrive.drive(0, 0, 0);
            SmartDashboard.putString("Status", "Driving Onto Platform");
            SmartDashboard.putString("Climb Next Step", "Retract Last Piston");
          }

          case 12: {//Retracts rear piston, robot is now fully supported by hab 3
            Robot.CYLINDERREAR.in();
            Robot.climbHabStepCount = -4; //Resets counter five less to prevent accidental restart
            SmartDashboard.putString("Status", "Press 5 times to restart climbing");
            SmartDashboard.putString("Climb Next Step", "NONE - End of Routine");
          }
        }
      }
    }.start();
    Robot.climbHabStepCount = Robot.climbHabStepCount + 1; //Adds 1 to the current number so it goes to the next step next time
  }
}
