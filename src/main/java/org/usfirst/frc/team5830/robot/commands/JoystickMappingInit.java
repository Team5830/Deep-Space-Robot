package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * 
 * @author Hunter P.
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
				//Robot.rightJoy = new Joystick(1);
				Robot.arduino = new Joystick(1);
			
				break;
			case 1: //General Xbox
				Robot.xbox = new Joystick(2);
				Robot.testPixyAlign = new JoystickButton(Robot.xbox, 1); //A
				Robot.testPixyAlign.whenPressed(new PixyAlign());
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
				Robot.LoadingStation = new JoystickButton(Robot.arduino, 7);
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
				Robot.leftJoy = new Joystick(4);
				Robot.testArmLowCargo = new JoystickButton(Robot.leftJoy, 3);
				Robot.testArmLowHatchP = new JoystickButton(Robot.leftJoy, 5);
				Robot.testArmMiddleHatchP = new JoystickButton(Robot.leftJoy, 9);
				Robot.testVacuumGamePiece = new JoystickButton(Robot.leftJoy, 1);
				Robot.testArmMiddleCargo= new JoystickButton(Robot.leftJoy, 7);
				Robot.testManipulatorMiddleHatchP = new JoystickButton(Robot.leftJoy, 10);
				Robot.testDropGamePiece = new JoystickButton(Robot.leftJoy, 2);
				Robot.testManipulatorFloorCargo = new JoystickButton(Robot.leftJoy, 4);
				Robot.testManipulatorMiddleCargo = new JoystickButton(Robot.leftJoy, 8);
				Robot.testManipulatorLowHatchP = new JoystickButton(Robot.leftJoy, 6);
				Robot.testSuckCargo = new JoystickButton(Robot.leftJoy, 11);
				Robot.testSpitCargo = new JoystickButton(Robot.leftJoy, 12);
				
				Robot.testSuckCargo.whenPressed(new SuckCargo());
				Robot.testSpitCargo.whenPressed(new SpitCargo());
				Robot.testManipulatorFloorCargo.whenPressed(new ManipulatorFloorCargo());
				Robot.testArmLowCargo.whenPressed(new ArmLowCargo());
				Robot.testManipulatorMiddleCargo.whenPressed(new ManipulatorMiddleCargo());
				Robot.testVacuumGamePiece.whenPressed(new VacuumGamePiece());
				Robot.testArmMiddleCargo.whenPressed(new ArmMiddleCargo());
				Robot.testManipulatorMiddleHatchP.whenPressed(new ManipulatorMiddleHatchP());
				Robot.testDropGamePiece.whenPressed(new DropGamePiece());
				Robot.testArmLowHatchP.whenPressed(new ArmLowHatchP());
				Robot.testArmMiddleHatchP.whenPressed(new ArmMiddleHatchP());
				Robot.testManipulatorLowHatchP.whenPressed(new ManipulatorLowHatchP());
				break;

			}

		
    }
}
