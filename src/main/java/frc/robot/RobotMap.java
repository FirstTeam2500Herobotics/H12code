package frc.robot;

public class RobotMap {
	// All of the ports eveything is pluged into

	// PWM
	public static final int DRIVE_LEFT_MOTOR1 = 0;
	public static final int DRIVE_LEFT_MOTOR2 = 1;
	public static final int DRIVE_RIGHT_MOTOR1 = 2;
	public static final int DRIVE_RIGHT_MOTOR2 = 3;

	public static final int BALL_INTAKE_WHEELS_MOTOR = 4;

	public static final int BALL_CARRIAGE_WHEELS = 5;

	public static final int BALL_LIFT_MOTOR = 6;
	public static final int PANEL_LIFT_MOTOR = 7;

	// DIO
	// public static final int BASE_GYROSCOPE = SPI.Port.kMXP;

	// To get this number put the robot on the ground with a test program that just
	// prints encoder distance to the console.
	// After that push the robot in a straight line for x inches (I do like 250)
	// then divide that by the number you got (ie. 250/13208.5)
	public static final double DRIVE_ENCODER_PULSE_RATE = 250 / 13208.5;
	public static final int DRIVE_LEFT_ENCODER1 = 0;
	public static final int DRIVE_LEFT_ENCODER2 = 1;

	public static final int DRIVE_RIGHT_ENCODER1 = 2;
	public static final int DRIVE_RIGHT_ENCODER2 = 3;

	public static final int DRIVE_DIRECTION_LED = 4;

	// Pulse Rate for Ball Lift encoder. Should make it be 1 value to one inch
	public static final double BALL_LIFT_ENCODER_PULSE_RATE = 1;
	public static final int BALL_LIFT_ENCODER1 = 5;
	public static final int BALL_LIFT_ENCODER2 = 6;

	// Pulse Rate for Ball Lift encoder. Should make it be 1 value to one inch
	public static final double PANEL_LIFT_ENCODER_PULSE_RATE = 1;
	public static final int PANEL_LIFT_ENCODER1 = 7;
	public static final int PANEL_LIFT_ENCODER2 = 8;

	// Pneumatics ports
	public static final int INTAKE_ACTUATOR_SOLENOID = 0;

	public static final int PANEL_EXTENDER_SOLENOID = 1;
	public static final int PANEL_ACTUATOR_SOLENOID = 2;
}
