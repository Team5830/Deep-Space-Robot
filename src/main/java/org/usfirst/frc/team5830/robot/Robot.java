/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot;

import org.usfirst.frc.team5830.robot.commands.ActivateArmAutomatic;
import org.usfirst.frc.team5830.robot.commands.ActivateArmManual;
import org.usfirst.frc.team5830.robot.commands.ArmManual;
import org.usfirst.frc.team5830.robot.commands.GamePieceVacuumSlow;
import org.usfirst.frc.team5830.robot.commands.JoystickMappingInit;
import org.usfirst.frc.team5830.robot.commands.JoystickMappingPeriodic;
import org.usfirst.frc.team5830.robot.commands.PistonFrontHab23;
import org.usfirst.frc.team5830.robot.commands.PixyLineRotation;
import org.usfirst.frc.team5830.robot.commands.PixyLineStrafe;
import org.usfirst.frc.team5830.robot.subsystems.Cylinder12SideFirst;
import org.usfirst.frc.team5830.robot.subsystems.Cylinder12SideLast;
import org.usfirst.frc.team5830.robot.subsystems.Cylinder23Rear;
import org.usfirst.frc.team5830.robot.subsystems.CylinderManipulator;
import org.usfirst.frc.team5830.robot.subsystems.Cylinders23FrontLeft;
import org.usfirst.frc.team5830.robot.subsystems.Cylinders23FrontRight;
import org.usfirst.frc.team5830.robot.subsystems.GyroSubsystem;
import org.usfirst.frc.team5830.robot.subsystems.LIDARSubsystem;
import org.usfirst.frc.team5830.robot.subsystems.PIDArm;
import org.usfirst.frc.team5830.robot.subsystems.PIDManipulator;
import org.usfirst.frc.team5830.robot.subsystems.SwerveDrive;
import org.usfirst.frc.team5830.robot.subsystems.Vacuum;
import org.usfirst.frc.team5830.robot.subsystems.WheelDrive;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
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
	public static final double maxArmSpeedDown = -.5; //Between -1 and 0. POSITIVE NUMBERS WILL NOT WORK!
	//Maximum manipulator speed up
	public static final double maxManipulatorSpeedUp = .4; //Between 0 and 1. NEGATIVE NUMBERS WILL NOT WORK!
	//Maximum manipulator speed up
	public static final double maxManipulatorSpeedDown = -0.7; //Between -1 and 0. POSITIVE NUMBERS WILL NOT WORK!
	//Pixy 2 line margin of error
	public static final double pixy2LineRotationError = 1; //Error in pixels allowed when aligning
	//Pixy 2 line margin of error
	public static final double pixy2LineStrafeError = 1; //Error in pixels allowed when aligning
	//Margin of error allowed when using LIDAR for finding distance
	public static final int lidarError = 3; //Inches
	//Distance robot needs to be from HAB to climb. 
	public static final double habDistance = 4; //inches
	//Margin of error allowed when using Ultrasonic for finding distance
	public static final int ultrasonicError = 1; //Inches
	//Highest arm position
	public static final int armMaxHeight = 900;
	//Maximum Manipulator Rotation
	public static final int manipulatorMaxRotation = 1200;
	//Arm Error
	public static final int armError = 100;
	//Manipulator Error
	public static final int manipulatorError = 100;

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
	public static XboxController xbox;
	public static Joystick arduino;
	public static Button testPixyStrafe;
	public static Button testPixyAlign;
	public static Button testPixyAngle;
	public static Button raiseFront;
	public static Button raiseRear;
	public static Button habClimb;
	public static Button HatchPanel;
	public static Button Cargo;
	public static Button ArmLow;
	public static Button ArmMiddle;
	public static Button ArmHigh;
	public static Button Floor;
	public static Button LoadingStation;
	public static Button Vacuum;
	public static Button AlignAngle;
	public static Button AlignStrafe;
	public static Button PistonHab12First;
	public static Button PistonHab12Last;
	public static Button PistonHab23First;
	public static Button PistonHab23Last;
	public static Button ArmDefault;
	public static Button Orientation;
	public static Button ControllerInput;
	public static Button MoveToHatch;
	public static Button isField;
	public static Button selectCargoOrHatch;
	public static Button ship;

	//Testing
	public static Button testPistonFrontLeft;
	public static Button testPistonFrontRight;
	public static Button testPistonFront;
	public static Button testPistonSide;
	public static Button testPistonRear;
	public static Button testClimbHab;
	public static Button testArmMiddleCargo;
	public static Button testManipulatorMiddleHatchP;
	public static Button testGamePieceVacuum;
	public static Button testGamePieceDrop;
	public static Button testArmHighCargo; 
	public static Button testManipulatorMiddleCargo;
	public static Button testManipulatorHighCargo;
	public static Button testArmLowHatchP;
	public static Button testArmMiddleHatchP;
	public static Button testManipulatorLowHatchP;
	public static Button testSuckCargo;
	public static Button testSpitCargo; 
	public static Button testPistonFrontHab23;
	public static Button testPistonRearHab23;
	public static Button testPistonManipulator;
	public static Button testPistonSideHab12First;
	public static Button testPistonSideHab12Last;
	public static Button testManipulatorLow;
	public static Button pickupCargoFloor;
	public static Button pistonManipulator;


	//Misc
	
	public static SendableChooser<Boolean> driveType = new SendableChooser<>();
	public static SendableChooser<Integer> controlType = new SendableChooser<>();
	public static boolean isFieldOriented = false;
	public static int climbHabStepCount = 1;
	public static boolean isPistonFrontLeftExtended = false;
	public static boolean isPistonFrontRightExtended = false;
	public static boolean isPistonRearExtended = false;
	public static boolean isPiston12FirstExtended = false;
	public static boolean isPiston12LastExtended = false;
	public static boolean isPistonManipulatorExtended= false;
	public static boolean isCargo = false;
	public static boolean isArmLow = false;
	public static boolean isArmMiddle = false;
	public static boolean isArmHigh = false;
	public static boolean isArmDefault = false;
	public static boolean isFloor = false;
	public static boolean isArmAutomatic = true;
	public static boolean armCommandRunning = false;
	public static boolean isVacuumRunning = false;

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

	//Pneumatics
	//Compressor c = new Compressor(0);


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

	/**
	 * Commands
	 */
	private static Command joystickMappingInit = new JoystickMappingInit();
	private static Command joystickMappingPeriodic = new JoystickMappingPeriodic();
	private static Command armManual = new ArmManual();
	//public static Command Vacuum = new GamePieceVacuum();
	
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
		UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(0);
		camera1.setResolution(640, 480);
		camera1.setFPS(30);

		/*UsbCamera camera2 = CameraServer.getInstance().startAutomaticCapture(1);
		camera2.setResolution(640, 480);
		camera2.setFPS(30);*/
		
		//Vision Coordinates
		SmartDashboard.putBoolean("lined up", false);

		
		
		/**
		 * SmartDashboard
		 */		

		//Choose between Cargo and Hatch Panel
		SmartDashboard.putBoolean("Hatch Panel?", false);
		SmartDashboard.putBoolean("Cargo?", false); 
		SmartDashboard.putBoolean("Floor?", false);
		//SmartDashboard.putBoolean("Loading Station", false);
		SmartDashboard.putBoolean("Arm Low?", false);
		SmartDashboard.putBoolean("Arm Middle?", false);
		SmartDashboard.putBoolean("Arm High?", false);
		SmartDashboard.putBoolean("Arm Default?", false);
		SmartDashboard.putBoolean("DIDVacOn", false);
		
		//Overrides cube distance check if enabled and runs instake on button command regardless of what the LIDAR distance is.
		//SmartDashboard.putBoolean("Override Intake Sensor", true);
		
		//Initiate Gyro reset
		SmartDashboard.putBoolean("Reset Sensors", false);

		//Climbing status
		SmartDashboard.putString("Climb Next Step", "Raise Robot from Side");
		
		//Switch between flightsticks and Xbox joystick
		controlType.addOption("DIDBoard Flightsticks", 0);
		controlType.setDefaultOption("NO DIDBoard, Flightsticks & Xbox", 1);
		/*controlType.addOption("Piston Test (Right Flightstick)", 2);
		controlType.addOption("Manipulator Test (Left Flightstick)", 3);
		controlType.addOption("Pneumatics Test (Right Flightstick)", 4);	
		controlType.addOption("Arduino Test (Dual Flightsticks)", 6);	*/
		SmartDashboard.putData("Control Method", controlType);
	
		//Shows current robot command running
		SmartDashboard.putString("Status", "Waiting for Match Start");
		
		//Troubleshooting Posts - visible in tab 2 of shuffleboard, made for fast and easy logic troubleshooting
		SmartDashboard.putString("Troubleshoot - String", "null");
		//SmartDashboard.putBoolean("Troubleshoot - Boolean", false); //Commented out to avoid confusion with an actual "false" troubleshooting step
		SmartDashboard.putNumber("Troubleshoot - Number", 0);

		SmartDashboard.putData("Activate Arm Automatic", new ActivateArmAutomatic());
		SmartDashboard.putData("Activate Arm Manual", new ActivateArmManual());
		SmartDashboard.putData("Pixy Rotation", new PixyLineRotation());
		SmartDashboard.putData("Pixy Strafe", new PixyLineStrafe());
		SmartDashboard.putData("Backup Piston", new PistonFrontHab23());
		SmartDashboard.putData("Slow Vacuum", new GamePieceVacuumSlow());
		/**
		 * Sensor Calibration/Setup
		 */
		RobotMap.gyro.calibrate();
		RobotMap.armEncoder.setDistancePerPulse(1);
		RobotMap.armEncoder.reset();
		RobotMap.manipulatorEncoder.setDistancePerPulse(1);
		RobotMap.manipulatorEncoder.reset();
		RobotMap.wheelEncoder1.setDistancePerPulse(0.0965989132622258);
		RobotMap.wheelEncoder1.reset();
		//Pneumatics
		//c.setClosedLoopControl(true);

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
		SmartDashboard.putNumber("Manipulator Encoder Distance", RobotMap.manipulatorEncoder.getDistance());
		
		//If Reset Sensors button is pressed in SmartDashboard, it will calibrate the gyro. The robot MUST NOT BE MOVING. It then resets the button back to false state.
		if (SmartDashboard.getBoolean("Reset Sensors", false)) {
			RobotMap.gyro.calibrate();
			RobotMap.armEncoder.reset();
			RobotMap.wheelEncoder1.reset();
			RobotMap.manipulatorEncoder.reset();
			SmartDashboard.putBoolean("Reset Sensors", false);
			SmartDashboard.putNumber("Wheel Encoder", RobotMap.wheelEncoder1.getDistance());
		}
		
	}
	
	@Override
	public void autonomousInit() {
		joystickMappingInit.start();
		
	}
	
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();

		//Processes axis values
		joystickMappingPeriodic.start();

		if(Robot.armSetpointRaw < Robot.ARM.getSetpoint()){
			Robot.ARM.setSetpoint(Robot.ARM.getSetpoint() - 10);
		} else if(Robot.armSetpointRaw > Robot.ARM.getSetpoint()){
			Robot.ARM.setSetpoint(Robot.ARM.getSetpoint() + 10);
		}

		if(Robot.manipulatorSetpointRaw < Robot.MANIPULATOR.getSetpoint()){
			Robot.MANIPULATOR.setSetpoint(Robot.MANIPULATOR.getSetpoint() - 10);
		} else if(Robot.manipulatorSetpointRaw > Robot.MANIPULATOR.getSetpoint()){
			Robot.MANIPULATOR.setSetpoint(Robot.MANIPULATOR.getSetpoint() + 10);
		}
	
		
		//SmartDashboard data publishing

		/*isCargo = SmartDashboard.getBoolean("Cargo?", false);
		isArmLow = SmartDashboard.getBoolean("Arm Low?", false);
		isArmMiddle = SmartDashboard.getBoolean("Arm Middle?", false);
		isArmHigh = SmartDashboard.getBoolean("Arm High?", false);
		isArmDefault = SmartDashboard.getBoolean("Arm Default?", false);*/

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
		joystickMappingInit.start();

		//Pneumatics
		// c.setClosedLoopControl(true);
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		//Processes axis values
		joystickMappingPeriodic.start();
	
		//Arm and Manipulator Ramp
		if(Robot.armSetpointRaw < Robot.ARM.getSetpoint()){
			Robot.ARM.setSetpoint(Robot.ARM.getSetpoint() - 10);
		} else if(Robot.armSetpointRaw > Robot.ARM.getSetpoint()){
			Robot.ARM.setSetpoint(Robot.ARM.getSetpoint() + 10);
		}

		if(Robot.manipulatorSetpointRaw < Robot.MANIPULATOR.getSetpoint()){
			Robot.MANIPULATOR.setSetpoint(Robot.MANIPULATOR.getSetpoint() - 10);
		} else if(Robot.manipulatorSetpointRaw > Robot.MANIPULATOR.getSetpoint()){
			Robot.MANIPULATOR.setSetpoint(Robot.MANIPULATOR.getSetpoint() + 10);
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
		swerveDrive.drive(0, pidOutputWheel, pidOutputAngle);
	}
	
}
