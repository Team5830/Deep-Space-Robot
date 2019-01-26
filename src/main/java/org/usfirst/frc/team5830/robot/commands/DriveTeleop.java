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
    	SmartDashboard.putString("Status", "Operator-Controlled Driving");
    	Robot.swerveDrive.drive(Robot.driveX/1.5, Robot.driveY/1.5, Robot.rotX);
    	
    	//CALIBRATION ONLY
    	//sRobot.swerveDrive.drive(0, Robot.driveY, 0);
    }

    protected boolean isFinished() {
    	return false;
    }
}
