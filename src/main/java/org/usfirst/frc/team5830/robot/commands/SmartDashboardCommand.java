package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;
import org.usfirst.frc.team5830.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Hunter P.
 *
 */
public class SmartDashboardCommand extends Command{

    public SmartDashboardCommand() {
    	requires(Robot.lidarSubsystem);
    }
    protected void initialize() {
    	try {
    	Robot.lidarSubsystem.initLIDAR(new DigitalInput(RobotMap.DIO.LIDAR_PORT));
    	}catch(Exception e) {}
    }

    protected void execute() {
    	SmartDashboard.putNumber("LIDAR Distance",Robot.lidarSubsystem.getDistanceIn(true));
    	SmartDashboard.putNumber("Gyro PID Output", Robot.pidOutputAngle);
		SmartDashboard.putNumber("Gyro Angle", Robot.GYROSUBSYSTEM.getGyroClampedNeg180To180());
		SmartDashboard.putNumber("Arm Encoder Distance", RobotMap.armEncoder.getDistance());
		SmartDashboard.putNumber("Manipulator Encoder Distance", RobotMap.manipulatorEncoder.getDistance());
		SmartDashboard.putNumber("Arm Setpoint", Robot.ARM.getSetpoint());
		SmartDashboard.putNumber("Pivot Setpoint", Robot.MANIPULATOR.getSetpoint());		
		//SmartDashboard.putNumber("POV Position", Robot.xbox.getPOV());
    }

    protected boolean isFinished() {
        return false;
    }
}