package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;
import org.usfirst.frc.team5830.robot.commands.arm.*;
import org.usfirst.frc.team5830.robot.commands.pistons.PistonFrontHab23;
import org.usfirst.frc.team5830.robot.commands.pistons.PistonManipulator;
import org.usfirst.frc.team5830.robot.commands.pistons.PistonRearHab23;
import org.usfirst.frc.team5830.robot.commands.pistons.PistonSideHab12First;
import org.usfirst.frc.team5830.robot.commands.pistons.PistonSideHab12Last;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Hunter P. and Arlene A.
 * Gets the status of the SmartDashboard chooser for Joystick Input, 
 * then creates and maps joystick buttons. Axes definition in Robot.teleopPeriodic
 */

public class JoystickMappingInit {
	
	public static Joystick leftJoy;
	public static Joystick rightJoy;
	public static XboxController xbox;
	public static Joystick arduino;

	static Button raiseFront;
	static Button raiseRear;
	static Button habClimb;
	static Button HatchPanel;
	static Button Cargo;
	static Button ArmLow;
	static Button ArmMiddle;
	static Button ArmHigh;
	static Button Floor;
	static Button LoadingStation;
	static Button Vacuum;
	static Button AlignAngle;
	static Button AlignStrafe;
	static Button PistonHab12First;
	static Button PistonHab12Last;
	static Button PistonHab23First;
	static Button PistonHab23Last;
	static Button ArmDefault;
	static Button Orientation;
	static Button ControllerInput;
	static Button MoveToHatch;
	static Button isField;
	static Button selectCargoOrHatch;
	static Button ship;
	static Button pickupCargoFloor;
	static Button pistonManipulator;
	static Button armPounce;
	static Button hatchLow;
	static Button hatchMiddle;
	static Button hatchHigh;
	static Button hatchLoad;
	static Button hatchShip;
	static Button hatchRelease;

    public JoystickMappingInit() {}
    
    public static void run() {		
    	//Initiates command to call buttons according to the option selected on SmartDashboard (Command name = ChooseButtonLayout)
		switch (Robot.controlType.getSelected()) {
			case 0: //DIDBoard and Flightsticks
			leftJoy = new Joystick(0);
			rightJoy = new Joystick(1);
			xbox = new XboxController(2);
			arduino = new Joystick(3);

			selectCargoOrHatch = new JoystickButton(arduino, 3); //Back THIS IS ARM CARGO SHIP!!!
			
			ArmDefault = new JoystickButton(arduino, 1);     //Start
			Floor = new JoystickButton(arduino, 8);		  //A
			ArmLow = new JoystickButton(xbox, 2);			  //B
			ArmMiddle = new JoystickButton(xbox, 3);		  //X
			ArmHigh = new JoystickButton(xbox, 4);	          //Y
			ship = new JoystickButton(arduino, 2);

			hatchLow = new JoystickButton(rightJoy, 3);
			hatchMiddle = new JoystickButton(rightJoy, 4);
			hatchHigh = new JoystickButton(rightJoy, 5);
			hatchLoad = new JoystickButton(leftJoy, 3);
			hatchShip = new JoystickButton(leftJoy, 4);
			hatchRelease = new JoystickButton(rightJoy, 1);

			
			armPounce = new JoystickButton(arduino, 9);

			pistonManipulator = new JoystickButton(arduino, 4);  //L Stick (Push Down)
			PistonHab12First = new JoystickButton(arduino, 6);   //RB
			PistonHab12Last = new JoystickButton(arduino, 7);	  //LB

			Vacuum = new JoystickButton(arduino, 5);			  //R Stick (Push Down)


			selectCargoOrHatch.whenPressed(new ArmLoadingStation());

			ArmDefault.whenPressed(new ArmDefault());
			Floor.whenPressed(new ArmFloor());
			ArmLow.whenPressed(new ArmLow());
			ArmMiddle.whenPressed(new ArmMiddle());
			ArmHigh.whenPressed(new ArmHigh());
			ship.whenPressed(new ArmShip());

			hatchLow.whenPressed(new ArmHatchLow());
			hatchMiddle.whenPressed(new ArmHatchMiddle());
			hatchHigh.whenPressed(new ArmHatchHigh());
			hatchLoad.whenPressed(new ArmHatchLoadingStation());
			hatchShip.whenPressed(new ArmHatchCargoShip());
			hatchRelease.whenPressed(new ArmHatchRelease());

			pistonManipulator.whenPressed(new PistonManipulator());
			PistonHab12First.whenPressed(new PistonSideHab12First());
			PistonHab12Last.whenPressed(new PistonSideHab12Last());

			Vacuum.whenPressed(new VacuumToggle());

			SmartDashboard.putBoolean("Field Oriented?", false);
			SmartDashboard.putData("Toggle Arm Automatic", new ActivateArmAutomatic());

			armPounce.whenPressed(new ArmPounce());
			break;

			case 1: //No DIDBoard (Xbox Backup)
			leftJoy = new Joystick(0);
			rightJoy = new Joystick(1);
			xbox = new XboxController(2);

			selectCargoOrHatch = new JoystickButton(xbox, 7); //Back THIS IS ARM CARGO SHIP!!!
			
			ArmDefault = new JoystickButton(xbox, 8);         //Start
			Floor = new JoystickButton(xbox, 1);			  //A
			ArmLow = new JoystickButton(xbox, 2);			  //B
			ArmMiddle = new JoystickButton(xbox, 3);		  //X
			ArmHigh = new JoystickButton(xbox, 4);	          //Y
			ship = new JoystickButton(rightJoy, 11);
			
			armPounce = new JoystickButton(rightJoy, 7);

			//AlignAngle = new JoystickButton(xbox, 15);
			//AlignStrafe = new JoystickButton(xbox, 16);

			pistonManipulator = new JoystickButton(xbox, 9);  //L Stick (Push Down)
			PistonHab12First = new JoystickButton(xbox, 6);   //RB
			PistonHab12Last = new JoystickButton(xbox, 5);	  //LB
			PistonHab23First = new JoystickButton(leftJoy, 11);
			PistonHab23Last = new JoystickButton(leftJoy, 12);

			Vacuum = new JoystickButton(xbox, 10);			  //R Stick (Push Down)


			selectCargoOrHatch.whenPressed(new ArmLoadingStation());

			ArmDefault.whenPressed(new ArmDefault());
			Floor.whenPressed(new ArmFloor());
			ArmLow.whenPressed(new ArmLow());
			ArmMiddle.whenPressed(new ArmMiddle());
			ArmHigh.whenPressed(new ArmHigh());
			ship.whenPressed(new ArmShip());

			//AlignStrafe.whenPressed(new PixyLineStrafe());
			//AlignAngle.whenPressed(new PixyLineRotation());

			pistonManipulator.whenPressed(new PistonManipulator());
			PistonHab12First.whenPressed(new PistonSideHab12First());
			PistonHab12Last.whenPressed(new PistonSideHab12Last());
			PistonHab23First.whenPressed(new PistonFrontHab23());
			PistonHab23Last.whenPressed(new PistonRearHab23());

			Vacuum.whenPressed(new VacuumToggle());

			SmartDashboard.putBoolean("Field Oriented?", false);
			SmartDashboard.putData("Toggle Arm Automatic", new ActivateArmAutomatic());

			armPounce.whenPressed(new ArmPounce());
			break;
			}

		}

		
    }

