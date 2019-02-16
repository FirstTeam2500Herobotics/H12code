package frc.robot;

import edu.wpi.first.wpilibj.SPI;

public class RobotMap {
	//All of the ports eveything is pluged into
	
	// PWM
	public static final int DRIVE_LEFT_MOTOR = 0;
	public static final int DRIVE_RIGHT_MOTOR = 1;

	public static final int LIFT_WINCH_MOTOR = 2;
	
	public static final int INTAKE_WHEELS_MOTOR = 3;

	// DIO
	// public static final int BASE_GYROSCOPE = SPI.Port.kMXP;

	public static final int DRIVE_LEFT_ENCODER1 = 0;
	public static final int DRIVE_LEFT_ENCODER2 = 1;

	public static final int DRIVE_RIGHT_ENCODER1 = 2;
	public static final int DRIVE_RIGHT_ENCODER2 = 3;

	public static final int LIFT_WINCH_ENCODER1 = 4;
	public static final int LIFT_WINCH_ENCODER2 = 5;

	public static final int DRIVE_DIRECTION_LED = 6;
	
	// Pneumatics ports
	public static final int INTAKE_ACTUATOR_SOLENOID = 0;
}
