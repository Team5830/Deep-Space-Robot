package org.usfirst.frc.team5830.robot.subsystems;

import org.usfirst.frc.team5830.robot.Robot;
import org.usfirst.frc.team5830.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * 
 * @author Hunter P.
 *
 */
public class PIDWheelDistance extends PIDSubsystem {

    public PIDWheelDistance() {
    	super("WheelDistancePID", 0.5, 0.0, 0.0);
    	setOutputRange(-0.5, 0.5);
    }

    public void initDefaultCommand() {}

    protected double returnPIDInput() {
        return RobotMap.wheelEncoder1.getDistance();
    }

    protected void usePIDOutput(double output) {
    	Robot.pidOutputWheel = output;
    }
}
