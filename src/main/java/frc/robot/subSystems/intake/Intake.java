package frc.robot.subSystems.intake;

import frc.robot.RobotMap;
import frc.robot.subSystems.intake.TeleOp;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Solenoid;

public class Intake extends Subsystem{

	public static Intake instance;

	public static Intake getInstance(){
		if (instance == null){
			instance = new Intake();
		}

		return instance;
    }

	private Talon wheels;
	private Solenoid actuator;

	public Intake(){
		wheels = new Talon(RobotMap.INTAKE_WHEELS_MOTOR);
		actuator = new Solenoid(RobotMap.INTAKE_ACTUATOR_SOLENOID);
	}

	public void setSpeed(double speed){
		wheels.set(speed);
	}

	public void setPosition(boolean position){
		actuator.set(position);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TeleOp());
	}
}
