package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;
import org.usfirst.frc.team5830.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Hunter P.
 *
 * HOW STRAFE VS DISTANCE WAS CALCULATED:
 * Placed robot CAD in field CAD where it would be placed for center auto
 * Measured distance from current location to switch. This created a right triangle with the hypotenuse the exact path the robot should follow.
 * The X direction was 46.23". Y direction was 93.72". Hypotenuse was 104.5".
 * Calculated angle from X axis to hypotenuse by using the formula (sin(A))/a = (sin(B))/b where A is the angle in degrees and a is the length of the opposite side.
 * This returned 63.7460315383951. We needed angle from Y axis to hypotenuse, so I subtracted this angle from 90. This yielded 26.2539684616049.
 * Now we have polar coordinates as the robot setpoint. The polar coordinates are (104.5, 26.2539684616049).
 * The X and Y inputs on swerveDrive.drive can be cheated and used as rectangular coordinates to turn the wheels the correct direction.
 * I converted the polar coordinates to rectangular by using the formulas: x = rcos(theta) and y = rsin(theta).
 * This yielded 93.4745808151134 for x and 45.590599266064 for y. However, swerveDrive.drive only accepts valued between -1 and 1, so I clamped it down with a ratio calculation.
 * I wanted the max speed to be 0.3, so I input the values into this formula: (large x)/(large y) = (clamped x)/(calculated y). This made:
 * 93.4745808151134/45.590599266064 = 0.3/y. Now, y = 0.1463197765698, meaning when x speed is 0.3, y is 0.1463197765698.
 * Used these values with x being forward/backward, and y being strafe.
 *
 */
public class DriveStrafeToRSwitch extends Command {
	
	private double driveStraightP = 0.03;
	private double driveStraightI = 0.0;
	private double driveStraightD = 0.0;
	
	private double driveDistance;
	private double gyroLockValue;
	public static boolean isItFinished = false;
	
    public DriveStrafeToRSwitch() {
        requires(Robot.swerveDrive);
        requires(Robot.pidROTATIONCORRECTION);
    	driveDistance = 104;
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
    	if(RobotMap.wheelEncoder1.getDistance() < driveDistance - 10) {
    		Robot.swerveDrive.drive(0.1463197765698, -0.3, Robot.pidOutputAngle);//See top for how numbers were derived
    	} else if(RobotMap.wheelEncoder1.getDistance() < driveDistance) {
    		Robot.swerveDrive.drive(0.0731598882849, -0.15, Robot.pidOutputAngle);
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
