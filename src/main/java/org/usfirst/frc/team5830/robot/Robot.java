/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot;

import org.usfirst.frc.team5830.robot.commands.DriveStraight;
import org.usfirst.frc.team5830.robot.commands.JoystickMappingInit;
import org.usfirst.frc.team5830.robot.commands.JoystickMappingPeriodic;
import org.usfirst.frc.team5830.robot.commands.VacuumGamePiece;
import org.usfirst.frc.team5830.robot.subsystems.CylinderFrontLeft;
import org.usfirst.frc.team5830.robot.subsystems.CylinderFrontRight;
import org.usfirst.frc.team5830.robot.subsystems.CylinderRear;
import org.usfirst.frc.team5830.robot.subsystems.CylinderSide;
import org.usfirst.frc.team5830.robot.subsystems.CylinderManipulator;
import org.usfirst.frc.team5830.robot.subsystems.SonicFrontLeft;
import org.usfirst.frc.team5830.robot.subsystems.SonicFrontRight;
import org.usfirst.frc.team5830.robot.subsystems.GyroSubsystem;
import org.usfirst.frc.team5830.robot.subsystems.LIDARSubsystem;
import org.usfirst.frc.team5830.robot.subsystems.SonicLeftSideFront;
import org.usfirst.frc.team5830.robot.subsystems.SonicLeftSideRear;
import org.usfirst.frc.team5830.robot.subsystems.PIDArm;
import org.usfirst.frc.team5830.robot.subsystems.PIDLIDARDistance;
import org.usfirst.frc.team5830.robot.subsystems.PIDManipulator;
import org.usfirst.frc.team5830.robot.subsystems.PIDRotationCorrection;
import org.usfirst.frc.team5830.robot.subsystems.PIDWheelDistance;
import org.usfirst.frc.team5830.robot.subsystems.SwerveDrive;
import org.usfirst.frc.team5830.robot.subsystems.Vacuum;
import org.usfirst.frc.team5830.robot.subsystems.WheelDrive;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * VARIABLES DEFINED HERE under "User-Defined Variables"
 */

public class Robot extends TimedRobot{ 
	
	/**
	 * User-Defined Variables
	 */
	
	//Xbox controller stick deadzone size. 1 is entire range, 0 is disabled, closer to zero means less deadzone
	public static final double xboxStickDeadzone = 0.1;
	//Xbox controller trigger deadzone size. 1 is entire range, 0 is disabled, closer to zero means less deadzone
	public static final double xboxTriggerDeadzone = 0.2;
	//Distance from LIDAR cube has to be to switch intake from sucking to spitting
	public static final double cubeDistance = 9.5; //Inches
	//Maximum arm speed up
	public static final double maxArmSpeedUp = 1; //Between 0 and 1. NEGATIVE NUMBERS WILL NOT WORK!
	//Maximum arm speed up
	public static final double maxArmSpeedDown = -0.75; //Between -1 and 0. POSITIVE NUMBERS WILL NOT WORK!
	//Maximum manipulator speed up
	public static final double maxManipulatorSpeedUp = 1; //Between 0 and 1. NEGATIVE NUMBERS WILL NOT WORK!
	//Maximum manipulator speed up
	public static final double maxManipulatorSpeedDown = -0.75; //Between -1 and 0. POSITIVE NUMBERS WILL NOT WORK!
	//Pixy 2 line margin of error
	public static final double pixy2LineRotationError = 40; //Error in pixels allowed when aligning
	//Pixy 2 line margin of error
	public static final double pixy2LineStrafeError = 40; //Error in pixels allowed when aligning
	//Margin of error allowed when using LIDAR for finding distance
	public static final int lidarError = 3; //Inches
	//Distance robot needs to be from HAB to climb. 
	public static final double habDistance = 4; //inches
	//Margin of error allowed when using Ultrasonic for finding distance
	public static final int ultrasonicError = 1; //Inches

	/**
	 * System-Defined Variables
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
	public static Joystick leftJoy;
	public static Joystick rightJoy;
	public static Joystick xbox;
	
	public static Button testPixyAlign;
	public static Button raiseFront;
	public static Button raiseRear;
	public static Button habClimb;
	//Testing
	public static Button testPistonFrontLeft;
	public static Button testPistonFrontRight;
	public static Button testPistonFront;
	public static Button testPistonSide;
	public static Button testPistonRear;
	public static Button testClimbHab;
	public static Button testArmMiddle;
	public static Button testManipulatorMiddleHatchP;
	public static Button testVacuumGamePiece;
	public static Button testDropGamePiece;
	
	//Misc
	public static SendableChooser<Boolean> driveType = new SendableChooser<>();
	public static SendableChooser<Integer> controlType = new SendableChooser<>();
	public static boolean isFieldOriented = false;
	public static int climbHabStepCount = 1;
	public static boolean isPistonFrontExtended = false;
	public static boolean isPistonFrontLeftExtended = false;
	public static boolean isPistonFrontRightExtended = false;
	public static boolean isPistonRearExtended = false;
	public static boolean isPistonSideExtended = false;
	public static OI m_oi;
	
	//Swerve Drive
	public static WheelDrive backRight = new WheelDrive (0, 1, 0);
	public static WheelDrive backLeft = new WheelDrive (2, 3, 1);
	public static WheelDrive frontRight = new WheelDrive (4, 5, 2);
	public static WheelDrive frontLeft = new WheelDrive (6, 7, 3);
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

	//Pneumatics
	Compressor c = new Compressor(0);


	/**
	 * Subsystems
	 */
	
	//Regular
	public static final CylinderFrontLeft CYLINDERFRONTLEFT = new CylinderFrontLeft();
	public static final CylinderFrontRight CYLINDERFRONTRIGHT = new CylinderFrontRight();
	public static final CylinderRear CYLINDERREAR = new CylinderRear();
	public static final CylinderSide CYLINDERSIDE = new CylinderSide();
	public static final CylinderManipulator CYLINDERMANIPULATOR = new CylinderManipulator();
	public static final Vacuum VACUUM = new Vacuum();

	//LIDAR
	public static final LIDARSubsystem lidarSubsystem = new LIDARSubsystem();
	
	//PID Loops
	public static final PIDLIDARDistance auto_LIDAR_Distance_Swerve = new PIDLIDARDistance();
	public static final PIDRotationCorrection pidROTATIONCORRECTION = new PIDRotationCorrection();
	public static final PIDWheelDistance WHEELDISTANCEPID = new PIDWheelDistance();
	public static final GyroSubsystem GYROSUBSYSTEM = new GyroSubsystem();
	public static final PIDArm ARM = new PIDArm();
	public static final PIDManipulator MANIPULATOR = new PIDManipulator();

	//Ultrasonic 
	public static final SonicLeftSideFront LEFTSIDEFRONTSONIC = new SonicLeftSideFront();
	public static final SonicLeftSideRear LEFTSIDEREARSONIC = new SonicLeftSideRear();
	public static final SonicFrontLeft FRONTLEFTSONIC = new SonicFrontLeft();
	public static final SonicFrontRight FRONTRIGHTSONIC = new SonicFrontRight();
	/**
	 * Commands
	 */
	private static Command joystickMappingInit = new JoystickMappingInit();
	private static Command joystickMappingPeriodic = new JoystickMappingPeriodic();
	//public static Command Vacuum = new VacuumGamePiece();
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
		
		/**
		 * Cameras/Vision
		 */
		//Camera Stream
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture(0);
		camera.setResolution(640, 480);
		camera.setFPS(30);
		
		//Vision Coordinates
		SmartDashboard.putBoolean("lined up", false);

		
		/**
		 * SmartDashboard
		 */		
		
		//Choose between field and robot-oriented drive
		SmartDashboard.putBoolean("Field Oriented?", false);
		
		//Overrides cube distance check if enabled and runs instake on button command regardless of what the LIDAR distance is.
		SmartDashboard.putBoolean("Override Intake Sensor", true);
		
		//Initiate Gyro reset
		SmartDashboard.putBoolean("Reset Sensors", false);

		//Climbing status
		SmartDashboard.putString("Climb Next Step", "Raise Robot from Side");
		
		//Switch between flightsticks and Xbox joystick
		controlType.setDefaultOption("Dual Flightsticks", 0);
		controlType.addOption("Xbox Controller", 1);
		controlType.addOption("Piston Test (Right Flightstick)", 2);
        controlType.addOption("Manipulator Test (Left Flightstick)", 3);
		SmartDashboard.putData("Control Method", controlType);		
		
		//Shows current robot command running
		SmartDashboard.putString("Status", "Waiting for Match Start");
		
		//Troubleshooting Posts - visible in tab 2 of shuffleboard, made for fast and easy logic troubleshooting
		SmartDashboard.putString("Troubleshoot - String", "null");
		//SmartDashboard.putBoolean("Troubleshoot - Boolean", false); //Commented out to avoid confusion with an actual "false" troubleshooting step
		SmartDashboard.putNumber("Troubleshoot - Number", 0);
		
		/**
		 * Sensor Calibration/Setup
		 */
		RobotMap.gyro.calibrate();
		RobotMap.armEncoder.setDistancePerPulse(1);
		RobotMap.armEncoder.reset();
		RobotMap.winchEncoder.setDistancePerPulse(1);
		RobotMap.winchEncoder.reset();
		RobotMap.wheelEncoder1.setDistancePerPulse(0.0965989132622258);
		RobotMap.wheelEncoder1.reset();
		//Pneumatics
		c.setClosedLoopControl(true);

	}

	@Override
	public void disabledInit() {
		SmartDashboard.putString("Status", "Waiting for Match Start");
		isFieldOriented = false;
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Gyro Angle", GYROSUBSYSTEM.getGyroClampedNeg180To180());
		SmartDashboard.putNumber("Arm Encoder Distance", RobotMap.armEncoder.getDistance());
		SmartDashboard.putNumber("Winch Encoder Distance", RobotMap.winchEncoder.getDistance());
		
		//If Reset Sensors button is pressed in SmartDashboard, it will calibrate the gyro. The robot MUST NOT BE MOVING. It then resets the button back to false state.
		if (SmartDashboard.getBoolean("Reset Sensors", false)) {
			RobotMap.gyro.calibrate();
			RobotMap.armEncoder.reset();
			RobotMap.wheelEncoder1.reset();
			RobotMap.winchEncoder.reset();
			SmartDashboard.putBoolean("Reset Sensors", false);
			SmartDashboard.putNumber("Wheel Encoder", RobotMap.wheelEncoder1.getDistance());
		}
		
		SmartDashboard.putBoolean("Troubleshoot - Boolean", DriveStraight.isItFinished);
		
	}
	
	@Override
	public void autonomousInit() {}
	
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		}

	@Override
	public void teleopInit() {
		
		
		SmartDashboard.putString("Status", "Teleop Driving");
		
		//Takes ShuffleBoard button layout presets and maps buttons accordingly
		joystickMappingInit.start();

		//Pneumatics
		c.setClosedLoopControl(true);
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		//Processes axis values
		joystickMappingPeriodic.start();
		
		//SmartDashboard data publishing
		
		//Enables SmartDashboard driveType chooser
		isFieldOriented = SmartDashboard.getBoolean("Field Oriented?", false);
		
		/**
		 * Vision Processing
		 */

		pixy1x0 = pixy1x0Network.getDouble(0);
		pixy1y0 = pixy1y0Network.getDouble(0);
		pixy1x1 = pixy1x1Network.getDouble(0);
		pixy1y1 = pixy1y1Network.getDouble(0);

		// Ultrasonic sensor for hab approach

		SmartDashboard.putNumber("Sonic Front Left Distance (volts)", RobotMap.frontLeftSonic.getVoltage());
		SmartDashboard.putNumber("Sonic Front Left Distance (real)", SonicFrontLeft.getDistance());
		SmartDashboard.putNumber("Sonic Front Right Distance (volts)", RobotMap.frontLeftSonic.getVoltage());
		SmartDashboard.putNumber("Sonic Front Right Distance (real)", SonicFrontLeft.getDistance());
		SmartDashboard.putNumber("Sonic Side Front Distance (volts)", RobotMap.leftsideFrontSonic.getVoltage());
		SmartDashboard.putNumber("Sonic Side Front Distance (real)", SonicLeftSideFront.getDistance());
		SmartDashboard.putNumber("Sonic Side Rear Distance (volts)", RobotMap.leftsideRearSonic.getVoltage());
		SmartDashboard.putNumber("Sonic Side Rear Distance (real)", SonicLeftSideRear.getDistance());
		  

	}

	@Override
	public void testPeriodic() {
		SmartDashboard.putString("Status", "TEST MODE");
		swerveDrive.drive(0, pidOutputWheel, pidOutputAngle);
	}
}
