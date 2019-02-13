package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;
import org.usfirst.frc.team5830.robot.subsystems.UltrasonicSubsystem;

import edu.wpi.first.wpilibj.command.Command;


public class DistanceToHab extends Command {

  private boolean isItFinished = false;

  public DistanceToHab() {
  }

  @Override
  protected void execute() {
    if (UltrasonicSubsystem.getDistance() > Robot.ultrasonicError){
      while (UltrasonicSubsystem.getDistance() > Robot.habDistance){
       Robot.swerveDrive.drive(0.15, 0, 0);
      }
    while (UltrasonicSubsystem.getDistance() < Robot.habDistance){
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
