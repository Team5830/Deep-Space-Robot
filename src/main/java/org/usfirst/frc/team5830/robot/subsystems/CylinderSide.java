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
public class CylinderSide extends Subsystem {

  @Override
  public void initDefaultCommand() {}

  public void out(){
    RobotMap.sideSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void in(){
<<<<<<< HEAD:src/main/java/org/usfirst/frc/team5830/robot/subsystems/CylinderFront.java
    RobotMap.frontSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void stop(){
    RobotMap.frontSolenoid.set(DoubleSolenoid.Value.kReverse);
=======
    RobotMap.sideSolenoid.set(DoubleSolenoid.Value.kReverse);
>>>>>>> 9cf925ddf8d37bc8508e46032d8ee4eece795eef:src/main/java/org/usfirst/frc/team5830/robot/subsystems/CylinderSide.java
  }

}
