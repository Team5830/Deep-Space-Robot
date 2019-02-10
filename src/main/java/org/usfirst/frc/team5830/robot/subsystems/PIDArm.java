package org.usfirst.frc.team5830.robot.subsystems;

import org.usfirst.frc.team5830.robot.Robot;
import org.usfirst.frc.team5830.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * 
 * @author Hunter P.
 *
 */
public class PIDArm extends PIDSubsystem {

    public PIDArm() {
    	super("ArmPID", 0.001, 0.0, 0.0);
    	setOutputRange(Robot.maxArmSpeedDown, Robot.maxArmSpeedUp);
    }

    public void initDefaultCommand() {}

    protected double returnPIDInput() {
    	return RobotMap.armEncoder.getDistance();
    }

    protected void usePIDOutput(double output) {
    	RobotMap.stageOne.set(output);
    }
}
