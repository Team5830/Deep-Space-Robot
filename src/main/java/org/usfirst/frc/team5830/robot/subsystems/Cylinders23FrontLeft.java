/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot.subsystems;

import org.usfirst.frc.team5830.robot.Robot;
import org.usfirst.frc.team5830.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Cylinders23FrontLeft extends Subsystem {

  @Override
  public void initDefaultCommand() {}

  public void out(){
    RobotMap.frontLeft23Solenoid.set(DoubleSolenoid.Value.kForward);
    Robot.isPistonFrontLeftExtended = true;
  }

  public void in(){
    RobotMap.frontLeft23Solenoid.set(DoubleSolenoid.Value.kReverse);
    Robot.isPistonFrontLeftExtended = false;
  }
}
