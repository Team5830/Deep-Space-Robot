package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

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
    				case 0: //General Flightsticks (Default)
    					Robot.driveX = Robot.leftJoy.getRawAxis(0);
    					Robot.driveY = Robot.leftJoy.getRawAxis(1);
    					Robot.rotX = Robot.rightJoy.getRawAxis(0);
    					Robot.povPosition = Robot.rightJoy.getPOV();
    					//Links triggers to cube functions
    					//This weird format fixes the bug where the wheels would only spin for one tick, or not spin at all. Basically whileHeld in a raw format
    					
    					break;
    				case 1: //General Xbox
    					if (Math.abs(Robot.xbox.getRawAxis(0)) > Robot.xboxStickDeadzone) Robot.driveX = Robot.xbox.getRawAxis(0)/2; else Robot.driveX = 0;
    					if (Math.abs(Robot.xbox.getRawAxis(1)) > Robot.xboxStickDeadzone) Robot.driveY = Robot.xbox.getRawAxis(1)/2; else Robot.driveY = 0;
    					if (Math.abs(Robot.xbox.getRawAxis(4)) > Robot.xboxStickDeadzone) Robot.rotX = Robot.xbox.getRawAxis(4)/3; else Robot.rotX = 0;
    					Robot.povPosition = Robot.xbox.getPOV();
    					//if (Robot.xbox.getRawAxis(3) > Robot.xboxTriggerDeadzone) Robot.commandSuckCube.start(); else Robot.commandSuckCube.cancel();
    					//if (Robot.xbox.getRawAxis(2) > Robot.xboxTriggerDeadzone) Robot.commandSpitCube.start(); else Robot.commandSpitCube.cancel();
    					break;
    				case 2: //Daniel
    					if (Math.abs(Robot.xbox.getRawAxis(0)) > Robot.xboxStickDeadzone) Robot.driveX = Robot.xbox.getRawAxis(0)/2; else Robot.driveX = 0;
    					if (Math.abs(Robot.xbox.getRawAxis(1)) > Robot.xboxStickDeadzone) Robot.driveY = Robot.xbox.getRawAxis(1)/2; else Robot.driveY = 0;
    					if (Math.abs(Robot.xbox.getRawAxis(4)) > Robot.xboxStickDeadzone) Robot.rotX = Robot.xbox.getRawAxis(4)/3; else Robot.rotX = 0;
    					Robot.povPosition = Robot.xbox.getPOV();
    				//	if (Robot.xbox.getRawAxis(3) > Robot.xboxTriggerDeadzone) Robot.commandSuckCube.start(); else Robot.commandSuckCube.cancel();
    				//	if (Robot.xbox.getRawAxis(2) > Robot.xboxTriggerDeadzone) Robot.commandSpitCube.start(); else Robot.commandSpitCube.cancel();
    					break;
    				case 3: //Hannah
    					Robot.driveX = Robot.leftJoy.getRawAxis(0);
    					Robot.driveY = Robot.leftJoy.getRawAxis(1);
    					Robot.rotX = Robot.rightJoy.getRawAxis(0);
    					Robot.povPosition = Robot.rightJoy.getPOV();
    					//Links triggers to cube functions
    					//This weird format fixes the bug where the wheels would only spin for one tick, or not spin at all. Basically whileHeld in a raw format
    				//	if(Robot.button1.get()) Robot.commandSuckCube.start(); else Robot.commandSuckCube.cancel();
    				//	if(Robot.button2.get()) Robot.commandSpitCube.start(); else Robot.commandSpitCube.cancel();
    					break;
    				case 4: //Hunter
    					Robot.driveX = Robot.leftJoy.getRawAxis(0);
    					Robot.driveY = Robot.leftJoy.getRawAxis(1);
    					Robot.rotX = Robot.rightJoy.getRawAxis(0);
    					Robot.povPosition = Robot.rightJoy.getPOV();
    					//Links triggers to cube functions
    					//This weird format fixes the bug where the wheels would only spin for one tick, or not spin at all. Basically whileHeld in a raw format
    				//	if(Robot.button1.get()) Robot.commandSuckCube.start(); else Robot.commandSuckCube.cancel();
    				//	if(Robot.button2.get()) Robot.commandSpitCube.start(); else Robot.commandSpitCube.cancel();
    					break;
    				case 5: //Arcade RightJoy
    					Robot.driveX = Robot.rightJoy.getRawAxis(0);
    					Robot.driveY = Robot.rightJoy.getRawAxis(1);
    					Robot.rotX = Robot.rightJoy.getRawAxis(2);
    					Robot.povPosition = Robot.rightJoy.getPOV();
    					//Links triggers to cube functions
    					//This weird format fixes the bug where the wheels would only spin for one tick, or not spin at all. Basically whileHeld in a raw format
    				//	if(Robot.button1.get()) Robot.commandSuckCube.start(); else Robot.commandSuckCube.cancel();
    				//	if(Robot.button2.get()) Robot.commandSpitCube.start(); else Robot.commandSpitCube.cancel();
    					break;
    			}
    }
}
