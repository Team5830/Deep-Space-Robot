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

/**
 * 
 * WAIT! This code is
 *   ____    _____   ____    ____    _____    ____      _      _____   _____   ____  
 	|  _ \  | ____| |  _ \  |  _ \  | ____|  / ___|    / \    |_   _| | ____| |  _ \ 
 	| | | | |  _|   | |_) | | |_) | |  _|   | |       / _ \     | |   |  _|   | | | |
 	| |_| | | |___  |  __/  |  _ <  | |___  | |___   / ___ \    | |   | |___  | |_| |
 	|____/  |_____| |_|     |_| \_\ |_____|  \____| /_/   \_\   |_|   |_____| |____/                                                                                 
 * 
 * and will be deleted when replacement code is completed.
 */
public class DriveMotionProfiling extends Command {
	
	public static double driveMotionProfilingDistanceTolerance = 2;
	
	private int loopCount = 0;
	public static double driveAngle;
	public static double driveDistance;

	//Negative angle numbers allowed. Negative distance numbers NOT ALLOWED. Instead, have the robot rotate away then move forward.
	
	//I (Hunter P.) would have LOVED to make the variables set directly when command is called, but CommandGroups don't support
	//two of the same command but with different variables. Stupid. Now they have to be set separately in another command.
    public DriveMotionProfiling() {
    	super ("DriveMotionProfiling");
    	
    	//requires(Robot.WHEELDISTANCEPID);
    	requires(Robot.swerveDrive);
    	//localAngle = angle;
    	//driveMotionProfilingDistance = distance;
    }

    protected void initialize() {
    	SmartDashboard.putString("Status", "Automatically Driving");
    }

    protected void execute() {
    	SmartDashboard.putString("Auto Rotation Angle", "All the way!");
    	
    	//If the robot isn't at the angle it's supposed to be at, it will wait until the gyro PID finishes correcting it.
    	if(Math.abs(Robot.pidROTATIONCORRECTION.getSetpoint() - Robot.pidROTATIONCORRECTION.getPosition()) > 5) {
    		SmartDashboard.putString("Autonomous Status", "I got to MotionProfiling Stage 1");
    		
    		Robot.pidROTATIONCORRECTION.getPIDController().setP(0.1);
    		Robot.pidROTATIONCORRECTION.getPIDController().setI(0.0);
    		Robot.pidROTATIONCORRECTION.getPIDController().setD(0.0);
    		//Gets the angle requested by the command start variable and sets the PID loop to it, then enables it
        	Robot.pidROTATIONCORRECTION.setSetpoint(driveAngle);
        	Robot.pidROTATIONCORRECTION.enable();
        	Robot.swerveDrive.drive(0, 0, Robot.pidOutputAngle);
        	SmartDashboard.putNumber("AutoGyroNumber", Math.abs(Robot.pidROTATIONCORRECTION.getSetpoint() - Robot.pidROTATIONCORRECTION.getPosition()));
    	} else {	
        	SmartDashboard.putString("Autonomous Status", "I got to MotionProfiling Stage 2");
        	//Counts the amount of loops this has gone through
        	loopCount = Math.abs(loopCount + 1);//make sure this doesn't crash the code with an infinite loop
        	
    		//Only resets the encoder if this is the first time running the loop
    		//Cannot place in initialize() because it needs to reset once only after gyro PID has completed its rotation
        	if (loopCount <= 1) RobotMap.wheelEncoder1.reset();
    		
        	SmartDashboard.putNumber("LocalDistance", driveDistance);
        	if(RobotMap.wheelEncoder1.getDistance() < driveDistance) {
        		Robot.swerveDrive.drive(0, 0.5, 0);
        		SmartDashboard.putString("Autonomous Status", "I got to Driving");
        	} else { 
        		Robot.swerveDrive.drive(0, 0, 0);
        		SmartDashboard.putString("Autonomous Status", "I got to Stop Driving");
        	}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//Only is finished when both distance and angle setpoints have been met
        return Math.abs(RobotMap.wheelEncoder1.getDistance() - driveDistance) < driveMotionProfilingDistanceTolerance && Math.abs(Robot.pidROTATIONCORRECTION.getSetpoint() - Robot.pidROTATIONCORRECTION.getPosition()) < 5 && Math.abs(Robot.pidROTATIONCORRECTION.getSetpoint() - Robot.pidROTATIONCORRECTION.getPosition()) > (0 - 3);
    	//return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Resets loop counter to 0
    	loopCount = 0;
    	
    	SmartDashboard.putString("Autonomous Status", "I got to End MotionProfiling");
    	
    	//Disables all associated PID loops
    	Robot.pidROTATIONCORRECTION.disable();
    	Robot.swerveDrive.drive(0, 0, 0);
    	//Robot.WHEELDISTANCEPID.disable();
    }
}
