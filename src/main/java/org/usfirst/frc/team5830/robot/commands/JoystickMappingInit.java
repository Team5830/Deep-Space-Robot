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
    	
    	//Initiates command to call buttons according to the option selected on SmartDashboard (Command name = ChooseButtonLayout)
		switch (Robot.controlType.getSelected()) {
			case 0: //General Flightsticks (Default)
				Robot.leftJoy = new Joystick(0);
				Robot.rightJoy = new Joystick(1);
				break;
			case 1: //General Xbox
				Robot.xbox = new Joystick(2);
				Robot.testPixyAlign = new JoystickButton(Robot.xbox, 1); //A
				Robot.testPixyAlign.whenPressed(new PixyAlign());
				break;
			case 2: //Daniel
				Robot.xbox = new Joystick(2);
				break;
			case 3: //Hannah
				Robot.leftJoy = new Joystick(0);
				Robot.rightJoy = new Joystick(1);
				break;
			case 4: //Hunter
				Robot.leftJoy = new Joystick(0);
				Robot.rightJoy = new Joystick(1);
				break;
			case 5:
				Robot.leftJoy = new Joystick(0);
				Robot.rightJoy = new Joystick(1);
				break;
			}
		
    }
}
