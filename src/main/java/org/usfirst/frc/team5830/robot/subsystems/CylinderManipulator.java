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
public class CylinderManipulator extends Subsystem {

  @Override
  public void initDefaultCommand() {}

  public void out(){
    RobotMap.manipulatorSolenoid.set(DoubleSolenoid.Value.kForward);
    Robot.isPistonManipulatorExtended = true;
    //TODO Turn ON DIDBoard LED
  }

  public void in(){
    RobotMap.manipulatorSolenoid.set(DoubleSolenoid.Value.kReverse);
    Robot.isPistonManipulatorExtended = false;
    //TODO Turn OFF DIDBoard LED
  }
}
