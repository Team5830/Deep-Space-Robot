package org.usfirst.frc.team5830.robot.subsystems;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * 
 * @author Hunter P.
 *
 */
public class AUTO_GYRO_Correction_Swerve extends PIDSubsystem {
	
    public AUTO_GYRO_Correction_Swerve() {
    	super("AUTO_GYRO_Correction_Swerve", 0.1, 0.0, 0.0);
    	setInputRange(-180, 180);
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
