package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;
import org.usfirst.frc.team5830.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Hunter P.
 *
 * For instructions on how to calculate strafe/drive numbers, see header in DriveStrafeToRSwitch.
 * 
 * Author notes:
 * x=62.31
 * y=92.55
 * hyp=111.57
 * 
 * rectx=92.55
 * recty=62.3086061471447
 * 
 * clamprectx=0.3
 * clamprecty=0.2019727914008
 */
public class DriveStrafeToLSwitch extends Command {
	
	private double driveStraightP = 0.03;
	private double driveStraightI = 0.0;
	private double driveStraightD = 0.0;
	
	private double driveDistance;
	private double gyroLockValue;
	public static boolean isItFinished = false;
	
    public DriveStrafeToLSwitch() {
        requires(Robot.swerveDrive);
        requires(Robot.pidROTATIONCORRECTION);
    	driveDistance = 112;
    }

    protected void initialize() {
    	isItFinished = false;
    	//Sets the current gyroscope value to the locking point, so the robot will drive straight from its starting position
    	gyroLockValue = Robot.GYROSUBSYSTEM.getGyroClampedNeg180To180();
    	
    	//Sets the driveStraight PID loop to the proper gains for straight protection, sets setpoint to current angle, 
    	Robot.pidROTATIONCORRECTION.getPIDController().setP(driveStraightP);
    	Robot.pidROTATIONCORRECTION.getPIDController().setI(driveStraightI);
    	Robot.pidROTATIONCORRECTION.getPIDController().setD(driveStraightD);
    	Robot.pidROTATIONCORRECTION.setOutputRange(-0.05, 0.05);
    	Robot.pidROTATIONCORRECTION.setSetpoint(gyroLockValue);
    	Robot.pidROTATIONCORRECTION.enable();
    	
    	///Resets wheel distance encoder to 0
    	RobotMap.wheelEncoder1.reset();
    }

    protected void execute() {
    	if(RobotMap.wheelEncoder1.getDistance() < driveDistance - 20) {
    		Robot.swerveDrive.drive(-0.2019727914008, -0.3, Robot.pidOutputAngle);//See header in DriveStrafeToRSwitch for how numbers were derived
    	} else if(RobotMap.wheelEncoder1.getDistance() < driveDistance) {
    		Robot.swerveDrive.drive(-0.1009863957004, -0.15, Robot.pidOutputAngle);
    	} else { 
    		Robot.swerveDrive.drive(0, 0, 0);
    		isItFinished = true;
    	}
    }

    protected boolean isFinished() {
        return isItFinished;
    }

    protected void end() {
    	Robot.pidROTATIONCORRECTION.disable();
    }

    protected void interrupted() {
    	Robot.pidROTATIONCORRECTION.disable();
    	Robot.swerveDrive.drive(0, 0, 0);
    	isItFinished = false;
    }
}
