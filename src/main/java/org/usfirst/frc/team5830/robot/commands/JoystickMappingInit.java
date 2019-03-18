package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
			case 0: //DIDBoard and Flightsticks //TODO update for new DIDBoard layout and cargo only commands
			Robot.leftJoy = new Joystick(0);
			Robot.rightJoy = new Joystick(1);
			Robot.xbox = new XboxController(2);
			Robot.arduino = new Joystick(3);

			Robot.selectCargoOrHatch = new JoystickButton(Robot.arduino, 3); //Back THIS IS ARM CARGO SHIP!!!
			
			Robot.ArmDefault = new JoystickButton(Robot.arduino, 1);         //Start
			Robot.Floor = new JoystickButton(Robot.arduino, 8);			  //A
			Robot.ArmLow = new JoystickButton(Robot.xbox, 2);			  //B
			Robot.ArmMiddle = new JoystickButton(Robot.xbox, 3);		  //X
			Robot.ArmHigh = new JoystickButton(Robot.xbox, 4);	          //Y
			Robot.ship = new JoystickButton(Robot.arduino, 2);
			
			Robot.testPixyAlign = new JoystickButton(Robot.arduino, 9); //THIS IS ARM POUNCE!!!

			//Robot.AlignAngle = new JoystickButton(Robot.xbox, 15);
			//Robot.AlignStrafe = new JoystickButton(Robot.xbox, 16);

			Robot.pistonManipulator = new JoystickButton(Robot.arduino, 4);  //L Stick (Push Down)
			Robot.PistonHab12First = new JoystickButton(Robot.arduino, 6);   //RB
			Robot.PistonHab12Last = new JoystickButton(Robot.arduino, 7);	  //LB

			Robot.Vacuum = new JoystickButton(Robot.arduino, 5);			  //R Stick (Push Down)


			Robot.selectCargoOrHatch.whenPressed(new ArmLoadingStation());

			Robot.ArmDefault.whenPressed(new ArmDefault());
			Robot.Floor.whenPressed(new ArmFloor());
			Robot.ArmLow.whenPressed(new ArmLow());
			Robot.ArmMiddle.whenPressed(new ArmMiddle());
			Robot.ArmHigh.whenPressed(new ArmHigh());
			Robot.ship.whenPressed(new ArmShip());

			//Robot.AlignStrafe.whenPressed(new PixyLineStrafe());
			//Robot.AlignAngle.whenPressed(new PixyLineRotation());

			Robot.pistonManipulator.whenPressed(new PistonManipulator());
			Robot.PistonHab12First.whenPressed(new PistonSideHab12First());
			Robot.PistonHab12Last.whenPressed(new PistonSideHab12Last());

			Robot.Vacuum.whenPressed(new VacuumToggle());

			SmartDashboard.putBoolean("Field Oriented?", false);
			SmartDashboard.putData("Toggle Arm Automatic", new ActivateArmAutomatic());

			Robot.testPixyAlign.whenPressed(new ArmPounce());

			// Robot.leftJoy = new Joystick(0);
			// Robot.rightJoy = new Joystick(1);
			// Robot.arduino = new Joystick(3);

			// Robot.selectCargoOrHatch = new JoystickButton(Robot.arduino, 1);
			
			// Robot.ArmDefault = new JoystickButton(Robot.arduino, 10);
			// Robot.Floor = new JoystickButton(Robot.arduino, 11);
			// Robot.ArmLow = new JoystickButton(Robot.arduino, 12);
			// Robot.ArmMiddle = new JoystickButton(Robot.arduino, 13);
			// Robot.ArmHigh = new JoystickButton(Robot.arduino, 14);

			// Robot.AlignAngle = new JoystickButton(Robot.arduino, 15);
			// Robot.AlignStrafe = new JoystickButton(Robot.arduino, 16);

			// Robot.pistonManipulator = new JoystickButton(Robot.arduino, 2);
			// Robot.PistonHab12First = new JoystickButton(Robot.arduino, 5);
			// Robot.PistonHab12Last = new JoystickButton(Robot.arduino, 6);

			// Robot.Vacuum = new JoystickButton(Robot.arduino, 3);

			// Robot.isField = new JoystickButton(Robot.arduino, 4); //This is a WhileHeld button. This is handled in JoystickMappingPeriodic.


			// Robot.selectCargoOrHatch.whenPressed(new ChooseHatchCargo());

			// Robot.ArmDefault.whenPressed(new ArmDefault());
			// Robot.ArmLow.whenPressed(new ArmLow());
			// Robot.ArmMiddle.whenPressed(new ArmMiddle());
			// Robot.ArmHigh.whenPressed(new ArmHigh());

			// Robot.AlignStrafe.whenPressed(new PixyLineStrafe());
			// Robot.AlignAngle.whenPressed(new PixyLineRotation());

			// Robot.pistonManipulator.whenPressed(new PistonManipulator());
			// Robot.PistonHab12First.whenPressed(new PistonSideHab12First());
			// Robot.PistonHab12Last.whenPressed(new PistonSideHab12Last());

			// Robot.Vacuum.whenPressed(new GamePieceVacuum());
			break;

			case 1: //No DIDBoard (Xbox Backup)
			Robot.leftJoy = new Joystick(0);
			Robot.rightJoy = new Joystick(1);
			Robot.xbox = new XboxController(2);

			Robot.selectCargoOrHatch = new JoystickButton(Robot.xbox, 7); //Back THIS IS ARM CARGO SHIP!!!
			
			Robot.ArmDefault = new JoystickButton(Robot.xbox, 8);         //Start
			Robot.Floor = new JoystickButton(Robot.xbox, 1);			  //A
			Robot.ArmLow = new JoystickButton(Robot.xbox, 2);			  //B
			Robot.ArmMiddle = new JoystickButton(Robot.xbox, 3);		  //X
			Robot.ArmHigh = new JoystickButton(Robot.xbox, 4);	          //Y
			Robot.ship = new JoystickButton(Robot.rightJoy, 11);
			
			Robot.testPixyAlign = new JoystickButton(Robot.rightJoy, 7); //THIS IS ARM POUNCE!!! //TODO Rename button

			//Robot.AlignAngle = new JoystickButton(Robot.xbox, 15);
			//Robot.AlignStrafe = new JoystickButton(Robot.xbox, 16);

			Robot.pistonManipulator = new JoystickButton(Robot.xbox, 9);  //L Stick (Push Down)
			Robot.PistonHab12First = new JoystickButton(Robot.xbox, 6);   //RB
			Robot.PistonHab12Last = new JoystickButton(Robot.xbox, 5);	  //LB
			Robot.PistonHab23First = new JoystickButton(Robot.leftJoy, 11);
			Robot.PistonHab23Last = new JoystickButton(Robot.leftJoy, 12);

			Robot.Vacuum = new JoystickButton(Robot.xbox, 10);			  //R Stick (Push Down)


			Robot.selectCargoOrHatch.whenPressed(new ArmLoadingStation());

			Robot.ArmDefault.whenPressed(new ArmDefault());
			Robot.Floor.whenPressed(new ArmFloor());
			Robot.ArmLow.whenPressed(new ArmLow());
			Robot.ArmMiddle.whenPressed(new ArmMiddle());
			Robot.ArmHigh.whenPressed(new ArmHigh());
			Robot.ship.whenPressed(new ArmShip());

			//Robot.AlignStrafe.whenPressed(new PixyLineStrafe());
			//Robot.AlignAngle.whenPressed(new PixyLineRotation());

			Robot.pistonManipulator.whenPressed(new PistonManipulator());
			Robot.PistonHab12First.whenPressed(new PistonSideHab12First());
			Robot.PistonHab12Last.whenPressed(new PistonSideHab12Last());
			Robot.PistonHab23First.whenPressed(new PistonFrontHab23());
			Robot.PistonHab23Last.whenPressed(new PistonRearHab23());

			Robot.Vacuum.whenPressed(new VacuumToggle());

			SmartDashboard.putBoolean("Field Oriented?", false);
			SmartDashboard.putData("Toggle Arm Automatic", new ActivateArmAutomatic());

			Robot.testPixyAlign.whenPressed(new ArmPounce());
			break;

			case 2: //Manipulator Arm Test (Left Flightstick) hab climming test
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

