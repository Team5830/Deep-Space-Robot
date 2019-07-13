package org.usfirst.frc.team5830.robot.subsystems;

import org.usfirst.frc.team5830.robot.Robot;
import org.usfirst.frc.team5830.robot.RobotMap;
import org.usfirst.frc.team5830.robot.commands.DriveTeleop;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Jacob Misirian
 * https://jacobmisirian.gitbooks.io/frc-swerve-drive-programming/content/
 *
 * Max P. implemented
 */

public class SwerveDrive extends Subsystem {
	
	double gyroRad;
	
	public void drive (double x1, double y1, double x2) {
	    double r = Math.sqrt ((L * L) + (W * W));
	    y1 *= -1;
	    x1 *= -1;
	    
	    gyroRad = RobotMap.ahrs.getAngle()*Math.PI/180;
	    
	    if(Robot.isFieldOriented) {
	    	  double temp = y1*Math.cos(gyroRad) + x1*Math.sin(gyroRad-Math.PI);
	 	   	 x1 = -y1*Math.sin(gyroRad-Math.PI) + x1*Math.cos(gyroRad);
	 	   	 y1 = temp;
	    }
	    else {}

	    double a = x1 - x2 * (L / r);
	    double b = x1 + x2 * (L / r);
	    double c = y1 - x2 * (W / r);
	    double d = y1 + x2 * (W / r);

	    double backRightSpeed = Math.sqrt ((a * a) + (d * d));
	    double backLeftSpeed = Math.sqrt ((a * a) + (c * c));
	    double frontRightSpeed = Math.sqrt ((b * b) + (d * d));
	    double frontLeftSpeed = Math.sqrt ((b * b) + (c * c));

	    double backRightAngle = Math.atan2 (a, d) / Math.PI;
	    double backLeftAngle = Math.atan2 (a, c) / Math.PI;
	    double frontRightAngle = Math.atan2 (b, d) / Math.PI;
	    double frontLeftAngle = Math.atan2 (b, c) / Math.PI;
	    backRight.drive (backRightSpeed, backRightAngle);
	    backLeft.drive (backLeftSpeed, backLeftAngle);
	    frontRight.drive (frontRightSpeed, frontRightAngle);
	    frontLeft.drive (frontLeftSpeed, frontLeftAngle);
	
	}


	public final double L = 21;
	public final double W = 24;
	
	private WheelDrive backRight;
	private WheelDrive backLeft;
	private WheelDrive frontRight;
	private WheelDrive frontLeft;

	public SwerveDrive (WheelDrive backRight, WheelDrive backLeft, WheelDrive frontRight, WheelDrive frontLeft) {
	    this.backRight = backRight;
	    this.backLeft = backLeft;
	    this.frontRight = frontRight;
	    this.frontLeft = frontLeft;
	    
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new DriveTeleop());
	}
	

}
