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
		SmartDashboard.putNumber("Gyro Angle", RobotMap.ahrs.getYaw());
		SmartDashboard.putNumber("Arm Encoder Distance", RobotMap.armEncoder.getDistance());
		SmartDashboard.putNumber("Manipulator Encoder Distance", RobotMap.manipulatorEncoder.getDistance());
		SmartDashboard.putNumber("Arm Setpoint", Robot.ARM.getSetpoint());
		SmartDashboard.putNumber("Pivot Setpoint", Robot.MANIPULATOR.getSetpoint());

		//Gyro
		SmartDashboard.putNumber("IMU_Yaw", RobotMap.ahrs.getYaw());
        SmartDashboard.putNumber("IMU_Pitch", RobotMap.ahrs.getPitch());
        SmartDashboard.putNumber("IMU_Roll", RobotMap.ahrs.getRoll());

		//DIDBoard
		SmartDashboard.putBoolean("DIDArmHasCommand", Robot.armCommandRunning);
		SmartDashboard.putNumber("DIDArmValue", RobotMap.armEncoder.getDistance());
		// SmartDashboard.putNumber("DIDArmPower", RobotMap.pdp.getCurrent(5));
		SmartDashboard.putNumber("DIDWristHasCommand", RobotMap.manipulatorEncoder.getDistance());
		// SmartDashboard.putNumber("DIDWristPower", RobotMap.pdp.getCurrent(7));
		// SmartDashboard.putNumber("DIDWheelFRPower", RobotMap.pdp.getCurrent(14));
		// SmartDashboard.putNumber("DIDWheelFLPower", RobotMap.pdp.getCurrent(1));
		// SmartDashboard.putNumber("DIDWheelBRPower", RobotMap.pdp.getCurrent(12));
		// SmartDashboard.putNumber("DIDWheelBLPower", RobotMap.pdp.getCurrent(3));
		SmartDashboard.putNumber("DIDTotalPower", RobotMap.pdp.getTotalCurrent());
		SmartDashboard.putBoolean("DIDPlugerOut", Robot.isPistonManipulatorExtended);
		SmartDashboard.putBoolean("DID12HabFirstOut", Robot.isPiston12FirstExtended);
		SmartDashboard.putBoolean("DID12HabLastOut", Robot.isPiston12LastExtended);
		if(Robot.isPistonFrontLeftExtended && Robot.isPistonFrontRightExtended){
			SmartDashboard.putBoolean("DID23HabFirstOut", true);
		} else {
			SmartDashboard.putBoolean("DID23HabFirstOut", false);
		}
		SmartDashboard.putBoolean("DID23HabLastOut", Robot.isPistonRearExtended);

		//Cargo Attached?
		if(RobotMap.pdp.getCurrent(4) < 17 && Robot.isVacuumRunning){ //TODO Find correct channel number
			SmartDashboard.putBoolean("Cargo Attached?", true);
		} else {
			SmartDashboard.putBoolean("Cargo Attached?", false);
		}

		//Compressor On?
		if(RobotMap.pdp.getCurrent(8) > 4){ //TODO Find correct channel number
			SmartDashboard.putBoolean("Compressor On?", true);
		} else {
			SmartDashboard.putBoolean("Compressor On?", false);
		}

		//Camera Chooser
		SmartDashboard.putData("CameraChooser", Robot.cameraChooser);
    }

    protected boolean isFinished() {
        return false;
    }
}