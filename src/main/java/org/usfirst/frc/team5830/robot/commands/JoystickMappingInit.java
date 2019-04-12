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
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Hunter P. and Arlene A.
 * Gets the status of the SmartDashboard chooser for Joystick Input, 
 * then creates and maps joystick buttons. Axes definition in Robot.teleopPeriodic
 */

public class JoystickMappingInit extends InstantCommand {
	
	public static Joystick leftJoy;
	public static Joystick rightJoy;
	public static XboxController xbox;
	public static Joystick arduino;

	Button raiseFront;
	Button raiseRear;
	Button habClimb;
	Button HatchPanel;
	Button Cargo;
	Button ArmLow;
	Button ArmMiddle;
	Button ArmHigh;
	Button Floor;
	Button LoadingStation;
	Button Vacuum;
	Button AlignAngle;
	Button AlignStrafe;
	Button PistonHab12First;
	Button PistonHab12Last;
	Button PistonHab23First;
	Button PistonHab23Last;
	Button ArmDefault;
	Button Orientation;
	Button ControllerInput;
	Button MoveToHatch;
	Button isField;
	Button selectCargoOrHatch;
	Button ship;
	Button pickupCargoFloor;
	Button pistonManipulator;
	Button armPounce;
	Button hatchLow;
	Button hatchMiddle;
	Button hatchHigh;
	Button hatchLoad;
	Button hatchShip;
	Button hatchRelease;

    public JoystickMappingInit() {}
    
    protected void execute() {		
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

