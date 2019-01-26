package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class DriveEnsureStraight extends InstantCommand {
	
	private boolean isItFinished = false;

    public DriveEnsureStraight() {
        requires(Robot.swerveDrive);
    }
    
    protected void initialize() {
    	isItFinished = false;
    }

    protected void execute() {
    	Robot.swerveDrive.drive(0, -0.05, 0);
    	Timer.delay(0.5);
    	isItFinished = true;
    }
    
    protected boolean isFinished() {
    	return isItFinished;
    }
}
