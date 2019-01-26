package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Hunter P.
 *
 */
public class DriveRotateTo90 extends Command {

	private Command localDriveRotationSetpoint = new DriveRotationSetpoint(90);
	
    public DriveRotateTo90() {
        requires(Robot.swerveDrive);
    }
    
    protected void execute() {
    	SmartDashboard.putString("Troubleshoot - String", "DriveRotateTo90 was called!");
    	localDriveRotationSetpoint.start();
    }

    protected boolean isFinished() {
        return localDriveRotationSetpoint.isCompleted();
    }
}
