/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot;

import org.usfirst.frc.team5830.robot.commands.arm.ActivateArmAutomatic;
import org.usfirst.frc.team5830.robot.commands.arm.ActivateArmManual;
import org.usfirst.frc.team5830.robot.commands.arm.ArmManual;
import org.usfirst.frc.team5830.robot.commands.arm.DefenseMode;

import com.kauailabs.navx.frc.AHRS;

import org.usfirst.frc.team5830.robot.commands.JoystickMappingInit;
import org.usfirst.frc.team5830.robot.commands.JoystickMappingPeriodic;
import org.usfirst.frc.team5830.robot.commands.pistons.PistonFrontHab23;
import org.usfirst.frc.team5830.robot.commands.pixy.PixyLineRotation;
import org.usfirst.frc.team5830.robot.commands.pixy.PixyLineStrafe;
import org.usfirst.frc.team5830.robot.commands.rotate.rot0;
import org.usfirst.frc.team5830.robot.commands.rotate.rot180;
import org.usfirst.frc.team5830.robot.commands.rotate.rot270;
import org.usfirst.frc.team5830.robot.commands.rotate.rot90;
import org.usfirst.frc.team5830.robot.commands.StopAllCommands;
import org.usfirst.frc.team5830.robot.subsystems.Cylinder12SideFirst;
import org.usfirst.frc.team5830.robot.subsystems.Cylinder12SideLast;
import org.usfirst.frc.team5830.robot.subsystems.Cylinder23Rear;
import org.usfirst.frc.team5830.robot.subsystems.CylinderManipulator;
import org.usfirst.frc.team5830.robot.subsystems.Cylinders23FrontLeft;
import org.usfirst.frc.team5830.robot.subsystems.Cylinders23FrontRight;
import org.usfirst.frc.team5830.robot.subsystems.GyroSubsystem;
import org.usfirst.frc.team5830.robot.subsystems.LIDARSubsystem;
import org.usfirst.frc.team5830.robot.subsystems.PIDArm;
import org.usfirst.frc.team5830.robot.subsystems.PIDDriveRotation;
import org.usfirst.frc.team5830.robot.subsystems.PIDManipulator;
import org.usfirst.frc.team5830.robot.subsystems.SwerveDrive;
import org.usfirst.frc.team5830.robot.subsystems.Vacuum;
import org.usfirst.frc.team5830.robot.subsystems.WheelDrive;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * VARIABLES DEFINED HERE under "User-Defined Variables"
 */

public class Robot extends TimedRobot{ 

	/**
	 * System Variables
	 */
	
	//PID
	public static double pidOutputRobot;
	public static double pidOutputAngle;
	public static double pidOutputWheel;
	
	//Joystick inputs
	public static double driveY;
	public static double driveX;
	public static double rotX;
	public static int povPosition;

	//Misc
	public static SendableChooser<Boolean> driveType = new SendableChooser<>();
	public static SendableChooser<Integer> controlType = new SendableChooser<>();
	public static SendableChooser<Integer> cameraChooser = new SendableChooser<>();
	public static boolean isFieldOriented = false;
	public static boolean isPistonFrontLeftExtended = false;
	public static boolean isPistonFrontRightExtended = false;
	public static boolean isPistonRearExtended = false;
	public static boolean isPiston12FirstExtended = false;
	public static boolean isPiston12LastExtended = false;
	public static boolean isPistonManipulatorExtended = false;
	public static boolean isArmAutomatic = true;
	public static boolean armCommandRunning = false;
	public static boolean isVacuumRunning = false;
	public static boolean stopRotate = false;

	public static double manipulatorSetpointRaw = 0;
	public static double armSetpointRaw = 0;

	public static OI m_oi;
	
	//Swerve Drive
	public static WheelDrive backRight = new WheelDrive (0, 1, 5);
	public static WheelDrive backLeft = new WheelDrive (2, 3, 6);
	public static WheelDrive frontRight = new WheelDrive (4, 5, 4);
	public static WheelDrive frontLeft = new WheelDrive (6, 7, 7);
	public static SwerveDrive swerveDrive = new SwerveDrive (backRight, backLeft, frontRight, frontLeft);
	
	//Vision Processing
	public NetworkTableEntry visionX;
	public NetworkTableEntry visionY;
	private NetworkTableInstance inst = NetworkTableInstance.getDefault();
	private NetworkTable pixy = inst.getTable("SmartDashboard");
	private NetworkTableEntry pixy1x0Network = pixy.getEntry("lifeVisionX0");
	private NetworkTableEntry pixy1y0Network = pixy.getEntry("lifeVisionY0");
	private NetworkTableEntry pixy1x1Network = pixy.getEntry("lifeVisionX1");
	private NetworkTableEntry pixy1y1Network = pixy.getEntry("lifeVisionY1");
	//Initiates variables (Hunter doesn't know if he needs this. Silly me!)
	public static double pixy1x0 = 0;
	public static double pixy1y0 = 0;
	public static double pixy1x1 = 0;
	public static double pixy1y1 = 0;

	/**
	 * Subsystems
	 */
	
	//Regular
	public static final Cylinders23FrontLeft CYLINDERS23FrontLeft = new Cylinders23FrontLeft();
	public static final Cylinders23FrontRight CYLINDERS23FrontRight = new Cylinders23FrontRight();
	public static final Cylinder23Rear CYLINDER23REAR = new Cylinder23Rear();
	public static final Cylinder12SideFirst CYLINDER12SIDEFIRST = new Cylinder12SideFirst();
	public static final Cylinder12SideLast CYLINDER12SIDELAST = new Cylinder12SideLast();
	public static final CylinderManipulator CYLINDERMANIPULATOR = new CylinderManipulator();
	public static final Vacuum VACUUM = new Vacuum();


	//LIDAR
	public static final LIDARSubsystem lidarSubsystem = new LIDARSubsystem();
	
	//PID Loops
	public static final GyroSubsystem GYROSUBSYSTEM = new GyroSubsystem();
	public static final PIDArm ARM = new PIDArm();
	public static final PIDManipulator MANIPULATOR = new PIDManipulator();
	public static final PIDDriveRotation PIDDRIVEROTATION = new PIDDriveRotation();

	/**
	 * Commands
	 */
	private static Command armManual = new ArmManual();
	private static Command rot0 = new rot0();
	private static Command rot90 = new rot90();
	private static Command rot180 = new rot180();
	private static Command rot270 = new rot270();
	//public static Command Vacuum = new GamePieceVacuum();
	
	@Override
	public void robotInit() {
		m_oi = new OI();

		/**
		 * SmartDashboard
		 */		

		SmartDashboard.putBoolean("DIDVacOn", false);
		
		//Initiate Gyro reset
		SmartDashboard.putBoolean("Reset Sensors", false);
		
		//Switch between flightsticks and Xbox joystick
		controlType.addOption("DIDBoard Flightsticks", 0);
		controlType.setDefaultOption("NO DIDBoard, Flightsticks & Xbox", 1);
		SmartDashboard.putData("Control Method", controlType);
	
		//Shows current robot command running
		SmartDashboard.putString("Status", "Waiting for Match Start");

		SmartDashboard.putData("Activate Arm Automatic", new ActivateArmAutomatic());
		SmartDashboard.putData("Activate Arm Manual", new ActivateArmManual());
		SmartDashboard.putData("Pixy Rotation", new PixyLineRotation());
		SmartDashboard.putData("Pixy Strafe", new PixyLineStrafe());
		SmartDashboard.putData("Backup Piston", new PistonFrontHab23());
		SmartDashboard.putData("Stop All Commands", new StopAllCommands());
		SmartDashboard.putData("Defense Mode", new DefenseMode());

		//Camera Chooser
		cameraChooser.setDefaultOption("Front Camera", 0);
		cameraChooser.addOption("Rear Camera", 1);
		SmartDashboard.putData("CameraChooser", cameraChooser);

		//Test Mode
		SmartDashboard.putBoolean("Swerve Calibration (Test Mode)", false);

		/**
		 * Sensor Calibration/Setup
		 */

		try {
            RobotMap.ahrs = new AHRS(SerialPort.Port.kUSB1);
            //ahrs = new AHRS(SerialPort.Port.kMXP, SerialDataType.kProcessedData, (byte)50);
            RobotMap.ahrs.enableLogging(true);
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        }
        Timer.delay(1.0);

		//RobotMap.gyro.calibrate();
		RobotMap.armEncoder.setDistancePerPulse(1);
		RobotMap.armEncoder.reset();
		RobotMap.manipulatorEncoder.setDistancePerPulse(1);
		RobotMap.manipulatorEncoder.reset();
	}

	@Override
	public void robotPeriodic() {
	}

	@Override
	public void disabledInit() {
		SmartDashboard.putString("Status", "Waiting for Match Start");
		isFieldOriented = false;
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		
		//If Reset Sensors button is pressed in SmartDashboard, it will calibrate the gyro. The robot MUST NOT BE MOVING. It then resets the button back to false state.
		if (SmartDashboard.getBoolean("Reset Sensors", false)) {
			//RobotMap.gyro.calibrate();
			RobotMap.ahrs.enableBoardlevelYawReset(true);
			RobotMap.ahrs.reset();
			RobotMap.armEncoder.reset();
			RobotMap.manipulatorEncoder.reset();
			SmartDashboard.putBoolean("Reset Sensors", false);
		}
		
	}
	
	@Override
	public void autonomousInit() {
		JoystickMappingInit.run();
		
	}
	
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();

		//Processes axis values
		JoystickMappingPeriodic.run();
	
		//Arm and Manipulator Ramp
		if(Robot.armSetpointRaw < Robot.ARM.getSetpoint()){
			Robot.ARM.setSetpoint(Robot.ARM.getSetpoint() - Constants.armRampSpeed);
		} else if(Robot.armSetpointRaw > Robot.ARM.getSetpoint()){
			Robot.ARM.setSetpoint(Robot.ARM.getSetpoint() + Constants.armRampSpeed);
		}

		if(Robot.manipulatorSetpointRaw < Robot.MANIPULATOR.getSetpoint()){
			Robot.MANIPULATOR.setSetpoint(Robot.MANIPULATOR.getSetpoint() - Constants.manipulatorRampSpeed);
		} else if(Robot.manipulatorSetpointRaw > Robot.MANIPULATOR.getSetpoint()){
			Robot.MANIPULATOR.setSetpoint(Robot.MANIPULATOR.getSetpoint() + Constants.manipulatorRampSpeed);
		}

		/**
		 * Rotation Setpoints
		 */
		switch(JoystickMappingInit.rightJoy.getPOV()){
			case 0:
			Robot.rot0.start();
			break;

			case 90:
			Robot.rot90.start();
			break;

			case 180:
			Robot.rot180.start();
			break;

			case 270:
			Robot.rot270.start();
			break;
		}

		/**
		 * Vision Processing
		 */

		pixy1x0 = pixy1x0Network.getDouble(0);
		pixy1y0 = pixy1y0Network.getDouble(0);
		pixy1x1 = pixy1x1Network.getDouble(0);
		pixy1y1 = pixy1y1Network.getDouble(0);

		//Starts the arm's joystick control if automatic arm is disabled
		if(!isArmAutomatic) armManual.start();
		}

	@Override
	public void teleopInit() {

		SmartDashboard.putString("Status", "Driving");
		
		//Takes ShuffleBoard button layout presets and maps buttons accordingly
		JoystickMappingInit.run();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		//Processes axis values
		JoystickMappingPeriodic.run();
	
		//Arm and Manipulator Ramp
		if(Robot.armSetpointRaw < Robot.ARM.getSetpoint()){
			Robot.ARM.setSetpoint(Robot.ARM.getSetpoint() - Constants.armRampSpeed);
		} else if(Robot.armSetpointRaw > Robot.ARM.getSetpoint()){
			Robot.ARM.setSetpoint(Robot.ARM.getSetpoint() + Constants.armRampSpeed);
		}

		if(Robot.manipulatorSetpointRaw < Robot.MANIPULATOR.getSetpoint()){
			Robot.MANIPULATOR.setSetpoint(Robot.MANIPULATOR.getSetpoint() - Constants.manipulatorRampSpeed);
		} else if(Robot.manipulatorSetpointRaw > Robot.MANIPULATOR.getSetpoint()){
			Robot.MANIPULATOR.setSetpoint(Robot.MANIPULATOR.getSetpoint() + Constants.manipulatorRampSpeed);
		}

		/**
		 * Rotation Setpoints
		 */
		switch(JoystickMappingInit.leftJoy.getPOV()){
			case 0:
			Robot.rot0.start();
			break;

			case 90:
			Robot.rot90.start();
			break;

			case 180:
			Robot.rot180.start();
			break;

			case 270:
			Robot.rot270.start();
			break;
		}

		/**
		 * Vision Processing
		 */

		pixy1x0 = pixy1x0Network.getDouble(0);
		pixy1y0 = pixy1y0Network.getDouble(0);
		pixy1x1 = pixy1x1Network.getDouble(0);
		pixy1y1 = pixy1y1Network.getDouble(0);

		//Starts the arm's joystick control if automatic arm is disabled
		if(!isArmAutomatic) armManual.start();

	}


 
	@Override
	public void testPeriodic() {
		SmartDashboard.putString("Status", "TEST MODE");
		if(SmartDashboard.getBoolean("Swerve Calibration (Test Mode)", false)){
			swerveDrive.drive(0, 0.15, 0);
		}
	}
	
}
