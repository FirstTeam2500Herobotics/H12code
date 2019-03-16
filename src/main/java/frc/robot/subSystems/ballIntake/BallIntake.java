package frc.robot.subSystems.ballIntake;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Solenoid;

public class BallIntake extends Subsystem {

	public static BallIntake instance;

	public static BallIntake getInstance() {
		if (instance == null) {
			instance = new BallIntake();
		}

		return instance;
	}

	private Talon wheels;
	private Solenoid actuator;

	public BallIntake() {
		wheels = new Talon(RobotMap.BALL_INTAKE_WHEELS_MOTOR);
		actuator = new Solenoid(RobotMap.INTAKE_ACTUATOR_SOLENOID);
	}

	public void setSpeed(double speed) {
		wheels.set(speed);
	}

	public void setPosition(boolean position) {
		actuator.set(position);
	}

	public boolean getPosition() {
		return actuator.get();
	}

	@Override
	protected void initDefaultCommand() {
	}
}
