package org.usfirst.frc.team2500.subSystems.lift;

import org.usfirst.frc.team2500.robot.RobotMap;
import org.usfirst.frc.team2500.subSystems.lift.TeleOp;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lift extends Subsystem{

public static Lift instance;

	public static Lift getInstance(){
		if (instance == null){
			instance = new Lift();
		}

		return instance;
    }

	private Talon winch;
	private Encoder winchEncoder;

	public Lift(){
		winch = new Talon(RobotMap.LIFT_WINCH_MOTOR);
		winchEncoder = new Encoder(RobotMap.LIFT_WINCH_ENCODER1, RobotMap.LIFT_WINCH_ENCODER2);
	}

	public void setSpeed(double speed){
		winch.set(speed);
	}

	public double getHeight(){
		return winchEncoder.getDistance();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TeleOp());
	}
}
