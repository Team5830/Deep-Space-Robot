package org.usfirst.frc.team5830.robot.subsystems;


import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Talon;

/**
 * @author Jacob Misirian
 * https://jacobmisirian.gitbooks.io/frc-swerve-drive-programming/content/
 *
 * Max P. implemented
 */

public class WheelDrive {
	private Talon angleMotor;
	private Talon speedMotor;
	private PIDController pidController;

public WheelDrive (int angleMotor, int speedMotor, int encoder) {
    this.angleMotor = new Talon (angleMotor);
    this.speedMotor = new Talon (speedMotor);

	pidController = new PIDController (2, 0, 0.5, new AnalogInput (encoder), this.angleMotor);

    pidController.setOutputRange (-1, 1);
    pidController.setInputRange(0, 4.95);
    pidController.setContinuous ();
    pidController.enable ();
}

private final double MAX_VOLTS = 4.95;

public void drive (double speed, double angle) {
    speedMotor.set (speed);

 double setpoint = angle * (MAX_VOLTS * 0.5) + (MAX_VOLTS * 0.5); // Optimization offset can be calculated here.
    if (setpoint < 0) {
        setpoint = MAX_VOLTS + setpoint;
    }
    if (setpoint > MAX_VOLTS) {
        setpoint = setpoint - MAX_VOLTS;
    }

    pidController.setSetpoint (setpoint);
}

}
