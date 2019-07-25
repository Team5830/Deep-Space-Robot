package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Constants;
import org.usfirst.frc.team5830.robot.Robot;
import org.usfirst.frc.team5830.robot.RobotMap;

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
        
        if(RobotMap.pdp.getTotalCurrent() > Constants.currentLimit){ //Overcurrent trip protection
            Robot.swerveDrive.drive(Robot.driveX/4, Robot.driveY/4, Robot.rotX/4);
        } else {
            Robot.swerveDrive.drive(Robot.driveX, Robot.driveY, Robot.rotX);
        }
    }

    protected boolean isFinished() {
    	return false;
    }
}
