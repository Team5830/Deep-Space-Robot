package org.usfirst.frc.team5830.robot.subsystems;

import org.usfirst.frc.team5830.robot.Constants;
import org.usfirst.frc.team5830.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * 
 * @author Hunter P.
 *
 */
public class PIDManipulator extends PIDSubsystem {

    public PIDManipulator() {
    	super("ManipulatorPID", 0.002, 0.0, 0.0);
    	setOutputRange(Constants.maxManipulatorSpeedDown, Constants.maxManipulatorSpeedUp);
    }

    public void initDefaultCommand() {}

    protected double returnPIDInput() {
    	return RobotMap.manipulatorEncoder.getDistance();
    }

    protected void usePIDOutput(double output) {
    	RobotMap.manipulator.set(output);
    }
}
