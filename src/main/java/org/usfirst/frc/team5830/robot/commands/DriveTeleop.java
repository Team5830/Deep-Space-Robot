package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Hunter P.
 *
 */
public class DriveTeleop extends Command {

    public DriveTeleop() {
    	requires(Robot.swerveDrive);
    }

    protected void execute() {
    	SmartDashboard.putString("Status", "Driving");
        if(!Robot.driveCommandRunning){
            Robot.swerveDrive.drive(Robot.driveX, Robot.driveY, Robot.rotX);
        }
    	
    	//CALIBRATION ONLY
    	//Robot.swerveDrive.drive(0, Robot.driveY, 0);
    }

    protected boolean isFinished() {
    	return false;
    }
}
