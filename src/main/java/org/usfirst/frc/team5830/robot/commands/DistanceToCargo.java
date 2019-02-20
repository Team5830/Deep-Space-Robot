package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Arlene A.
 * 
 */


public class DistanceToCargo extends Command {
  public DistanceToCargo() {
    requires(Robot.swerveDrive);
  }

  @Override
  protected void execute() {
    Robot.auto_LIDAR_Distance_Swerve.setSetpoint(24); //TODO Calibrate
    Robot.auto_LIDAR_Distance_Swerve.enable();
    Robot.swerveDrive.drive(0, Robot.pidOutputRobot, 0);
  }

  @Override
  protected boolean isFinished() {
    return (Math.abs(Robot.auto_LIDAR_Distance_Swerve.getSetpoint() - Robot.auto_LIDAR_Distance_Swerve.getPosition()) < Robot.lidarError);
  }
}
