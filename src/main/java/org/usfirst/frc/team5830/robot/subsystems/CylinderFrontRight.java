/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot.subsystems;

import org.usfirst.frc.team5830.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class CylinderFrontRight extends Subsystem {

  @Override
  public void initDefaultCommand() {}

  public void out(){
    RobotMap.frontRightSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void in(){
    RobotMap.frontRightSolenoid.set(DoubleSolenoid.Value.kReverse);
  }
}
