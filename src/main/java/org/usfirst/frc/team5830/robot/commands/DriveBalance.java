package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;
import org.usfirst.frc.team5830.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Hunter P.
 *
 */
public class DriveBalance extends Command {

    public DriveBalance() {
    	requires(Robot.swerveDrive);
    }

    protected void initialize() {
    	SmartDashboard.putString("Status", "BALANCE PROTECTION ENABLED");
    }

    protected void execute() {
    	Robot.swerveDrive.drive(Robot.driveX/2, Robot.driveY/2, Robot.rotX);
    }
    
    protected boolean isFinished() {
        return RobotMap.elevatorEncoder.getDistance() < Robot.balanceProtectionElevatorHeight;
    }
}
