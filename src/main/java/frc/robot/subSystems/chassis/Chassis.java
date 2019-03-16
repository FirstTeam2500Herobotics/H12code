package frc.robot.subSystems.chassis;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// import com.kauailabs.navx.frc.AHRS;

//TODO: Get a gyro

public class Chassis extends Subsystem {

	// Making only one chassis exist in the code
	public static Chassis instance;

	// This function return the chassis to be used in other places
	public static Chassis getInstance() {
		if (instance == null)
			instance = new Chassis();

		return instance;
	}

	// The drive wheels and the encoders that go with them
	private Talon leftMotor1;
	private Talon leftMotor2;
	private Encoder leftEncoder;

	private Talon rightMotor1;
	private Talon rightMotor2;
	private Encoder rightEncoder;

	// Scaler for if we want to switch the direction
	public double driveScale = 1;

	// private AHRS gyro;

	private DigitalOutput frontLED;

	public Chassis() {
		// Setting up everything for the right side of the left
		leftMotor1 = new Talon(RobotMap.DRIVE_LEFT_MOTOR1);
		leftMotor2 = new Talon(RobotMap.DRIVE_LEFT_MOTOR2);
		leftEncoder = new Encoder(RobotMap.DRIVE_LEFT_ENCODER1, RobotMap.DRIVE_LEFT_ENCODER2);

		// Setting up everything for the left side of the robot
		rightMotor1 = new Talon(RobotMap.DRIVE_RIGHT_MOTOR1);
		rightMotor2 = new Talon(RobotMap.DRIVE_RIGHT_MOTOR2);
		rightEncoder = new Encoder(RobotMap.DRIVE_RIGHT_ENCODER1, RobotMap.DRIVE_RIGHT_ENCODER2);

		// Setting the pulse rate of then encoders to give us convenient units (inches)
		leftEncoder.setDistancePerPulse(RobotMap.DRIVE_ENCODER_PULSE_RATE);
		// The right is negitive because it is a mirror of the left side
		rightEncoder.setDistancePerPulse(-RobotMap.DRIVE_ENCODER_PULSE_RATE);

		// Setting up the gyroscope
		// gyro = new AHRS(SPI.Port.kMXP);

		frontLED = new DigitalOutput(RobotMap.DRIVE_DIRECTION_LED);

		this.reset();
	}

	// Will always be using arcade drive when nothing else is using it
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TeleOp());
	}

	public void resetEncoder() {
		leftEncoder.reset();
		rightEncoder.reset();
	}

	public void resetGyro() {
		// gyro.reset();
	}

	public void reset() {
		resetEncoder();
		resetGyro();
	}

	public double getAverageDistance() {
		return (getLeftDistance() + getRightDistance()) / 2;
	}

	public double getLeftDistance() {
		double distance = leftEncoder.getDistance();
		SmartDashboard.putNumber("Chassis Left Distance", distance);
		return distance;
	}

	public double getRightDistance() {
		double distance = rightEncoder.getDistance();
		SmartDashboard.putNumber("Chassis Right Distance", distance);
		return distance;
	}

	public double getAverageRate() {
		return (getLeftRate() + getRightRate()) / 2;
	}

	public double getLeftRate() {
		double rate = leftEncoder.getRate();
		SmartDashboard.putNumber("Chassis Right rate", rate);
		return rate;
	}

	public double getRightRate() {
		double rate = rightEncoder.getRate();
		SmartDashboard.putNumber("Chassis Right rate", rate);
		return rate;
	}

	public double getRotation() {
		// double angle = gyro.getAngle();
		double angle = 0;
		SmartDashboard.putNumber("rotation", angle);
		return angle;
	}

	public void setDirectionLED(boolean direction) {
		frontLED.set(direction);
	}

	public void toggleDirectionLED() {
		frontLED.set(!frontLED.get());
	}

	/*
	 * A mode of drive where you control the wheels of the robots with a left and a
	 * right speed Set left to a double between -1 and 1 to set the left speed from
	 * -100% to 100% speed Set right to a double between -1 and 1 to set the right
	 * speed from -100% to 100% speed
	 */
	public void tankDrive(double left, double right) {
		leftMotor1.setSpeed(left * driveScale);
		leftMotor2.setSpeed(left * driveScale);
		rightMotor1.setSpeed(right * driveScale);
		rightMotor2.setSpeed(right * driveScale);
	}

	/*
	 * A mode of drive where you control the wheels with a forword and a rotation
	 * value Set the throttle to a double between -1 and 1 to set the forword speed
	 * from -100% to 100% Set the turn to a double between -1 and 1 to set the
	 * rotation speed from -100% to 100% Set the square to a boolean to true to
	 * square the inputs of the throttel and turn values
	 */
	public void arcadeDrive(double throttle, double turn, boolean square) {
		// If we are using drive scale it invert the drive then invert the steering
		if (driveScale < 0) {
			turn *= -1;
		}

		// Square inputs preserving sign
		if (square) {
			throttle = throttle * throttle * (throttle / Math.abs(throttle));
			turn = turn * turn * (turn / Math.abs(turn));
		}

		// Code from:
		// http://firstfrc.blob.core.windows.net/frc2019/Manual/TeamUpdates/TeamUpdate14.pdf
		// Get the largest value for any input
		double maxInput = Math.max(Math.abs(throttle), Math.abs(turn));
		double difference = throttle - turn;
		double total = throttle + turn;

		double leftMotorOutput;
		double rightMotorOutput;
		if (throttle >= 0.0) {
			// First quadrant
			if (turn >= 0.0) {
				leftMotorOutput = maxInput;
				rightMotorOutput = difference;
			}
			// Second quadrant
			else {
				leftMotorOutput = total;
				rightMotorOutput = maxInput;
			}
		} else {
			// Fourth quadrant
			if (turn >= 0.0) {
				leftMotorOutput = total;
				rightMotorOutput = -maxInput;
			}
			// Third quadrant
			else {
				leftMotorOutput = -maxInput;
				rightMotorOutput = difference;
			}
		}

		tankDrive(leftMotorOutput, rightMotorOutput * -1);
	}

	public void arcadeDrive(double throttle, double turn) {
		arcadeDrive(throttle, turn, true);
	}
}
