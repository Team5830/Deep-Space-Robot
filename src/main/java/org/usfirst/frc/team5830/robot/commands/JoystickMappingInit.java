package org.usfirst.frc.team5830.robot.commands;

import javax.print.attribute.standard.OrientationRequested;

import org.usfirst.frc.team5830.robot.Robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * 
 * @author Hunter P. and Arlene A.
 * DEPRECATED! To avoid nullPointerExceptions, this code was moved directly into teleopInit. Still have no idea why that worked.
 * Gets the status of the SmartDashboard chooser for Joystick Input, 
 * then creates and maps joystick buttons. Axes definition in Robot.teleopPeriodic
 */

public class JoystickMappingInit extends InstantCommand {
	

    public JoystickMappingInit() {}
    
    protected void execute() {
    	//There is no isFinished defined because this is an InstantCommand. 
    	//An InstantCommand is just shorthand for returning true in isFinished, meaning execute will only run once.
		/*Robot.arduino = new Joystick(3);
		Robot.HatchPanel = new JoystickButton(Robot.arduino, 1);
		Robot.Cargo = new JoystickButton(Robot.arduino, 2);
		Robot.ArmLow = new JoystickButton(Robot.arduino, 3);
		Robot.ArmMiddle = new JoystickButton(Robot.arduino, 4);
		Robot.ArmHigh = new JoystickButton(Robot.arduino, 5);
		Robot.Floor = new JoystickButton(Robot.arduino, 6);
		Robot.LoadingStation = new JoystickButton(Robot.arduino, 7);*/

		
    	//Initiates command to call buttons according to the option selected on SmartDashboard (Command name = ChooseButtonLayout)
		switch (Robot.controlType.getSelected()) {
			case 0: //General Flightsticks (Default)
				Robot.leftJoy = new Joystick(0);
				Robot.rightJoy = new Joystick(1);
				//Robot.arduino = new Joystick(2);
			
				break;
			case 1: //General Xbox
				Robot.xbox = new Joystick(2);
				Robot.testPixyAlign = new JoystickButton(Robot.xbox, 1); //A
				Robot.testPixyAlign.whenPressed(new PixyAlign());
				Robot.testPixyStrafe = new JoystickButton(Robot.xbox, 2); //B
				Robot.testPixyStrafe.whenPressed(new PixyLineStrafe());
				Robot.testPixyAngle = new JoystickButton(Robot.xbox, 3); //X
				Robot.testPixyAngle.whenPressed(new PixyLineRotation());
				Robot.habClimb = new JoystickButton(Robot.xbox, 4); //Y
				Robot.habClimb.whenPressed(new ClimbHab());
				break;
			case 2: //Arduino Button Test
				Robot.arduino = new Joystick(1);
				Robot.HatchPanel = new JoystickButton(Robot.arduino, 1);
				Robot.Cargo = new JoystickButton(Robot.arduino, 2);
				Robot.ArmLow = new JoystickButton(Robot.arduino, 3);
				Robot.ArmMiddle = new JoystickButton(Robot.arduino, 4);
				Robot.ArmHigh = new JoystickButton(Robot.arduino, 5);
				Robot.Floor = new JoystickButton(Robot.arduino, 6);
				//Robot.LoadingStation = new JoystickButton(Robot.arduino, 7);
				Robot.Vacuum = new JoystickButton(Robot.arduino, 8);
				Robot.AlignAngle = new JoystickButton(Robot.arduino, 9);
				Robot.AlignStrafe = new JoystickButton(Robot.arduino, 10);
				Robot.PistonHab12First = new JoystickButton(Robot.arduino, 11);
				Robot.PistonHab12Last = new JoystickButton(Robot.arduino, 12);
				Robot.PistonHab23First = new JoystickButton(Robot.arduino, 13);
				Robot.PistonHab23Last = new JoystickButton(Robot.arduino, 14);
				Robot.ArmDefault = new JoystickButton(Robot.arduino, 15);
				Robot.Orientation = new JoystickButton(Robot.arduino, 16);
				Robot.ControllerInput = new JoystickButton(Robot.arduino, 17);

				Robot.Floor.whenPressed(new ManipulatorFloorCargo());
				Robot.ArmDefault.whenPressed(new ArmDefault());
				Robot.AlignAngle.whenPressed(new PixyLineStrafe());
				Robot.AlignAngle.whenPressed(new PixyLineRotation());
				Robot.Vacuum.whenPressed(new VacuumGamePiece());
				Robot.PistonHab23First.whenPressed(new PistonFrontHab23());
				Robot.PistonHab23Last.whenPressed(new PistonRearHab23());
				Robot.PistonHab12First.whenPressed(new PistonSideHab12First());
				Robot.PistonHab12Last.whenPressed(new PistonSideHab12Last());



			//Pneumatics Test (Right Flightstick)
				//Robot.rightJoy = new Joystick(1);
				/*Robot.testPistonFront = new JoystickButton(Robot.rightJoy, 7);
				Robot.testPistonRear = new JoystickButton(Robot.rightJoy, 8);
				Robot.testPistonFrontLeft = new JoystickButton(Robot.rightJoy, 9);
				Robot.testPistonFrontRight = new JoystickButton(Robot.rightJoy, 10);
				Robot.testClimbHab = new JoystickButton(Robot.rightJoy, 11);
				Robot.testPistonSide = new JoystickButton(Robot.rightJoy, 12);

				Robot.testPistonFront.whenPressed(new PistonFront());
				Robot.testPistonFrontLeft.whenPressed(new PistonFrontLeft());
				Robot.testPistonFrontRight.whenPressed(new PistonFrontRight());
				Robot.testPistonRear.whenPressed(new PistonRear());
				Robot.testPistonSide.whenPressed(new PistonSide());
				Robot.testClimbHab.whenPressed(new ClimbHab());*/
				break;
				case 3: //Manipulator Arm Test (Left Flightstick)
				Robot.leftJoy = new Joystick(0);
				Robot.testArmHighCargo = new JoystickButton(Robot.leftJoy, 3);
				Robot.testArmLowHatchP = new JoystickButton(Robot.leftJoy, 5);
				Robot.testArmMiddleHatchP = new JoystickButton(Robot.leftJoy, 9);
				Robot.testVacuumGamePiece = new JoystickButton(Robot.leftJoy, 1);
				Robot.testArmMiddleCargo= new JoystickButton(Robot.leftJoy, 7);
				Robot.testManipulatorMiddleHatchP = new JoystickButton(Robot.leftJoy, 10);
				Robot.testDropGamePiece = new JoystickButton(Robot.leftJoy, 2);
				Robot.testManipulatorHighCargo = new JoystickButton(Robot.leftJoy, 4);
				Robot.testManipulatorMiddleCargo = new JoystickButton(Robot.leftJoy, 8);
				Robot.testManipulatorLowHatchP = new JoystickButton(Robot.leftJoy, 6);
				Robot.testSuckCargo = new JoystickButton(Robot.leftJoy, 11);
				Robot.testSpitCargo = new JoystickButton(Robot.leftJoy, 12);
				
				Robot.testSuckCargo.whenPressed(new SuckCargo());
				Robot.testSpitCargo.whenPressed(new SpitCargo());
				Robot.testManipulatorHighCargo.whenPressed(new ManipulatorHighCargo());
				Robot.testArmHighCargo.whenPressed(new ArmHighCargo());
				Robot.testManipulatorMiddleCargo.whenPressed(new ManipulatorMiddleCargo());
				Robot.testVacuumGamePiece.whenPressed(new VacuumGamePiece());
				Robot.testArmMiddleCargo.whenPressed(new ArmMiddleCargo());
				Robot.testManipulatorMiddleHatchP.whenPressed(new ManipulatorMiddleHatchP());
				Robot.testDropGamePiece.whenPressed(new DropGamePiece());
				Robot.testArmLowHatchP.whenPressed(new ArmLowHatchP());
				Robot.testArmMiddleHatchP.whenPressed(new ArmMiddleHatchP());
				Robot.testManipulatorLowHatchP.whenPressed(new ManipulatorLowHatchP());
				break;
				 case 4: //Manipulator Arm Test (Left Flightstick) hab climming test
				Robot.leftJoy = new Joystick(0);
				Robot.testPistonFrontHab23 = new JoystickButton(Robot.leftJoy, 3);
				Robot.testPistonRearHab23 = new JoystickButton(Robot.leftJoy, 5);
				Robot.testPistonManipulator = new JoystickButton(Robot.leftJoy, 9);
				Robot.testPistonSideHab12First= new JoystickButton(Robot.leftJoy, 1);
				Robot.testPistonSideHab12Last= new JoystickButton(Robot.leftJoy, 7);

				Robot.testPistonFrontHab23.whenPressed(new PistonFrontHab23());
				Robot.testPistonRearHab23.whenPressed(new PistonRearHab23());
				Robot.testPistonManipulator.whenPressed(new PistonManipulator());
				Robot.testPistonSideHab12First.whenPressed(new PistonSideHab12First());
				Robot.testPistonSideHab12Last.whenPressed(new PistonSideHab12Last());
				break;

			}

			}

		
    }

