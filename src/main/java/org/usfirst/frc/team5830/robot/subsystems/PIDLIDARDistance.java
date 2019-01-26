package org.usfirst.frc.team5830.robot.subsystems;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * @author Hunter P.
 * 
 * This code is
 *   _   _    ___      _        ___    _   _    ____   _____   ____      _   _   ____    _____   ____  
 *	| \ | |  / _ \    | |      / _ \  | \ | |  / ___| | ____| |  _ \    | | | | / ___|  | ____| |  _ \ 
 *	|  \| | | | | |   | |     | | | | |  \| | | |  _  |  _|   | |_) |   | | | | \___ \  |  _|   | | | |
 *	| |\  | | |_| |   | |___  | |_| | | |\  | | |_| | | |___  |  _ <    | |_| |  ___) | | |___  | |_| |
 *	|_| \_|  \___/    |_____|  \___/  |_| \_|  \____| |_____| |_| \_\    \___/  |____/  |_____| |____/ 
 *                               
 * but is kept here just in case we use it again. LIDAR is replaced with a drive wheel encoder used in PIDWheelDistance subsystem.
 */
public class PIDLIDARDistance extends PIDSubsystem {

    public PIDLIDARDistance() {
    	super("AUTO_LIDAR_Distance_Swerve", 0.05, 0.0, 0.0);
    	setAbsoluteTolerance(5);
    	setOutputRange(-0.5, 0.5);
    }

    public void initDefaultCommand() {}

    protected double returnPIDInput() {
        return Robot.lidarSubsystem.getDistanceIn(true);	
    }

    protected void usePIDOutput(double output) {
    	Robot.pidOutputRobot = output;
    }
}
