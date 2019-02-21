package frc.robot.subSystems.panel;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Panel extends Subsystem{

public static Panel instance;

	public static Panel getInstance(){
		if (instance == null){
			instance = new Panel();
		}

		return instance;
	}

	private Solenoid extender;
	private Solenoid actuator;

	public Panel(){
		extender = new Solenoid(RobotMap.PANEL_EXTENDER_SOLENOID);
		actuator = new Solenoid(RobotMap.PANEL_ACTUATOR_SOLENOID);
	}

	public void setExtention(boolean position) {
		extender.set(position);
	}

	public boolean getExtention() {
		return extender.get();
	}

	public void setActuator(boolean position) {
		actuator.set(position);
	}

	public boolean getActuator() {
		return actuator.get();
	}

	@Override
	protected void initDefaultCommand() {
		
	}
}
