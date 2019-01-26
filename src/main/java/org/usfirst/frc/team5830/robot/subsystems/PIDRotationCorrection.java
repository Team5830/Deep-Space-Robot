package org.usfirst.frc.team5830.robot.subsystems;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * 
 * @author Hunter P.
 *
 */
public class PIDRotationCorrection extends PIDSubsystem {

    public PIDRotationCorrection() {
    	super("AUTO_GYRO_Correction_Swerve", 0.013, 0.0, 0.02);
    	setInputRange(-180, 180);
    	setOutputRange(-0.3, 0.3);
    	getPIDController().setContinuous();
    }

    public void initDefaultCommand() {}

    protected double returnPIDInput() {
    	//Uses clamped gyro values to stop robot from spinning around
        return Robot.GYROSUBSYSTEM.getGyroClampedNeg180To180();
		
    }

    protected void usePIDOutput(double output) {
    	Robot.pidOutputAngle = output;
    }
}
