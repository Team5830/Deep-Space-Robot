/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot.subsystems;

import org.usfirst.frc.team5830.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Arlene A.
 * 
 */


/**
 * Add your docs here.
 */
public class Vacuum extends Subsystem {

  public void suck(){
    RobotMap.vacuum.set(1);
    SmartDashboard.putBoolean("DIDVacOn", true);
  } 

  public void suckSlow(){
    RobotMap.vacuum.set(0.92);
    SmartDashboard.putBoolean("DIDVacOn", true);
  } 

  public void stop(){
    RobotMap.vacuum.set(0);
    SmartDashboard.putBoolean("DIDVacOn", false);
  }

  @Override
  protected void initDefaultCommand() {

  }

 
}
