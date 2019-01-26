package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Hunter P.
 * 
 */

public class DriveRotationSetpoint extends Command {
	
	private double driveRotationP = 0.013;
	private double driveRotationI = 0.0;
	private double driveRotationD = 0.02;
	
	private double driveSetpointAngle;

	//Negative angle numbers allowed. Negative distance numbers NOT ALLOWED. Instead, have the robot rotate away then move forward.
	
	//I (Hunter P.) would have LOVED to make the variables set directly when command is called, but CommandGroups don't support
	//two of the same command but with different variables. Stupid. Now they have to be set separately in another command.
    public DriveRotationSetpoint(double angle) {
    	super ("DriveMotionProfiling");
    	requires(Robot.swerveDrive);
    	driveSetpointAngle = angle;
    }

    protected void initialize() {
    	SmartDashboard.putString("Status", "Automatically Driving");
    	Robot.pidROTATIONCORRECTION.getPIDController().setP(driveRotationP);
    	Robot.pidROTATIONCORRECTION.getPIDController().setI(driveRotationI);
    	Robot.pidROTATIONCORRECTION.getPIDController().setD(driveRotationD);
    	Robot.pidROTATIONCORRECTION.setOutputRange(-0.3, 0.3);
    	Robot.pidROTATIONCORRECTION.setSetpoint(driveSetpointAngle);
    	Robot.pidROTATIONCORRECTION.enable();
    }

    protected void execute() {
        	Robot.swerveDrive.drive(Robot.driveX, Robot.driveY, Robot.pidOutputAngle);
    }

    protected boolean isFinished() {
    	//Only is finished when angle setpoint met
        return Math.abs(Robot.pidROTATIONCORRECTION.getSetpoint() - Robot.pidROTATIONCORRECTION.getPosition()) < 5 && Math.abs(Robot.pidROTATIONCORRECTION.getSetpoint() - Robot.pidROTATIONCORRECTION.getPosition()) > (0 - 5);
    }

    protected void end() {    	
    	//Disables all associated PID loops
    	Robot.pidROTATIONCORRECTION.disable();
    	Robot.swerveDrive.drive(0, 0, 0);
    }
}
