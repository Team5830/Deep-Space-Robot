/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot.subsystems;

import org.usfirst.frc.team5830.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Vacuum extends Subsystem {

  public void suck(){
    RobotMap.vacuum.set(1.0);
  } 

  public void stop(){
    RobotMap.vacuum.set(0);
  }

  @Override
  protected void initDefaultCommand() {

  }

 
}
