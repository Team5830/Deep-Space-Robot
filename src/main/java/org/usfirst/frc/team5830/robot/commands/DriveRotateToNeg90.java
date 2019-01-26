package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Hunter P.
 *
 */
public class DriveRotateToNeg90 extends Command {

	private Command localDriveRotationSetpoint = new DriveRotationSetpoint(-90);
	
    public DriveRotateToNeg90() {
        requires(Robot.swerveDrive);
    }
    
    protected void execute() {
    	localDriveRotationSetpoint.start();
    }

    protected boolean isFinished() {
        return localDriveRotationSetpoint.isCompleted();
    }
}
