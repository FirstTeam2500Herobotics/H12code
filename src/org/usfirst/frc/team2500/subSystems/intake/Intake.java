package org.usfirst.frc.team2500.subSystems.intake;

import org.usfirst.frc.team2500.robot.RobotMap;
import org.usfirst.frc.team2500.subSystems.intake.TeleOp;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem{
	
public static Intake instance;
	
	public static Intake getInstance()
    {
		if (instance == null)
		   instance = new Intake();
	
		return instance;
    }
	
	private Talon wheels;
	
	public Intake(){
		wheels = new Talon(RobotMap.INTAKE_WHEELS);
	}
	
	public void setSpeed(double speed){
		wheels.set(speed);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TeleOp());
	}

}
