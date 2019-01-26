package org.usfirst.frc.team5830.robot.subsystems;

import org.usfirst.frc.team5830.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class ElevatorPID extends PIDSubsystem {

    public ElevatorPID() {
    	super("ElevatorPID", 0.001, 0.0, 0.0);
    	setOutputRange(-0.75, 1);
    }

    public void initDefaultCommand() {}

    protected double returnPIDInput() {
    	return RobotMap.elevatorEncoder.getDistance();
    }

    protected void usePIDOutput(double output) {
    	RobotMap.stageOne.set(output);
    }
}
