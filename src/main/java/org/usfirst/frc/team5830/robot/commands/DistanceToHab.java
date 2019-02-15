package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;
import org.usfirst.frc.team5830.robot.subsystems.SonicFrontLeft;
import org.usfirst.frc.team5830.robot.subsystems.SonicFrontRight;

import edu.wpi.first.wpilibj.command.Command;


public class DistanceToHab extends Command {

  private boolean isItFinished = false;

  public DistanceToHab() {
  }

  @Override
  protected void execute() {
    if (Math.abs(SonicFrontLeft.getDistance() - SonicFrontRight.getDistance()) > Robot.ultrasonicError){
      while (SonicFrontLeft.getDistance() < SonicFrontRight.getDistance()){
       Robot.swerveDrive.drive(0.15, 0, -0.15);
      }
    while (SonicFrontLeft.getDistance() > SonicFrontRight.getDistance()){
      Robot.swerveDrive.drive(-0.15, 0, 0.15);
      }
    }

   else {   //needs distance from hab to replace the number 5
     if (SonicFrontLeft.getDistance() < 5){
      Robot.swerveDrive.drive(-0.15, 0, 0);
     }
     while (SonicFrontLeft.getDistance() > 5){
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
