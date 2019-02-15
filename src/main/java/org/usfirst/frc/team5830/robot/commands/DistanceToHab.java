package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;
import org.usfirst.frc.team5830.robot.subsystems.FrontLeftSonic;

import edu.wpi.first.wpilibj.command.Command;


public class DistanceToHab extends Command {

  private boolean isItFinished = false;

  public DistanceToHab() {
  }

  @Override
  protected void execute() {
    if (FrontLeftSonic.getDistance() > Robot.ultrasonicError){
      while (FrontLeftSonic.getDistance() > Robot.habDistance){
       Robot.swerveDrive.drive(0.15, 0, 0);
      }
    while (FrontLeftSonic.getDistance() < Robot.habDistance){
        Robot.swerveDrive.drive(-0.15, 0, 0);
    }
  }
   else {
      Robot.swerveDrive.drive(0, 0, 0);
      isItFinished = true;
    }
  }

  @Override
  protected boolean isFinished() {
    return isItFinished;
  }
}
