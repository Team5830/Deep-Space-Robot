package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;
import org.usfirst.frc.team5830.robot.subsystems.SonicLeftSideFront;
import org.usfirst.frc.team5830.robot.subsystems.SonicLeftSideRear;

import edu.wpi.first.wpilibj.command.Command;


public class DistanceToHab2 extends Command {

  private boolean isItFinished = false;

  public DistanceToHab2() {
  }

  @Override
  protected void execute() {
    if (Math.abs(SonicLeftSideFront.getDistance() - SonicLeftSideRear.getDistance()) > Robot.ultrasonicError){
      while (SonicLeftSideFront.getDistance() < SonicLeftSideRear.getDistance()){
       Robot.swerveDrive.drive(0.15, 0, -0.15);
      }
    while (SonicLeftSideFront.getDistance() > SonicLeftSideRear.getDistance()){
      Robot.swerveDrive.drive(-0.15, 0, 0.15);
      }
    }

   else {   //needs distance from hab to replace the number 5
     if (SonicLeftSideFront.getDistance() < 5){
      Robot.swerveDrive.drive(-0.15, 0, 0);
     }
     while (SonicLeftSideFront.getDistance() > 5){
      Robot.swerveDrive.drive(0.15, 0, 0);
     }
    }
     Robot.swerveDrive.drive(0, 0, 0);
      isItFinished = true;
    }

  @Override
  protected boolean isFinished() {
    return isItFinished;
  }
}
