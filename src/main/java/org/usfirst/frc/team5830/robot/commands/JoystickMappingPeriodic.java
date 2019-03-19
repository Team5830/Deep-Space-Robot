package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Hunter P.
 *
 */
public class JoystickMappingPeriodic extends InstantCommand {

    public JoystickMappingPeriodic() {}
    
    protected void execute() {
    	//Changes axes according to what was selected in SmartDashboard (controlType SendableChooser)
		switch(Robot.controlType.getSelected()) {
			case 0:  //DIDBoard and Flightsticks
			Robot.driveX = -JoystickMappingInit.leftJoy.getRawAxis(0);
			Robot.driveY = -JoystickMappingInit.leftJoy.getRawAxis(1);
			Robot.rotX = JoystickMappingInit.rightJoy.getRawAxis(0);
			Robot.povPosition = JoystickMappingInit.rightJoy.getPOV();

			Robot.isFieldOriented = SmartDashboard.getBoolean("Field Oriented?", false);
			break;

			case 1: //No DIDBoard (Xbox Backup)
			Robot.driveX = -JoystickMappingInit.leftJoy.getRawAxis(0);
			Robot.driveY = -JoystickMappingInit.leftJoy.getRawAxis(1);
			Robot.rotX = JoystickMappingInit.rightJoy.getRawAxis(0);
			Robot.povPosition = JoystickMappingInit.rightJoy.getPOV();

			Robot.isFieldOriented = SmartDashboard.getBoolean("Field Oriented?", false);
			break;
		}
	}
}
