package frc.robot.subSystems.panelLift;

import frc.robot.RobotMap;
import frc.robot.subSystems.panelLift.TeleOp;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PanelLift extends Subsystem {

	public static PanelLift instance;

	public static PanelLift getInstance() {
		if (instance == null) {
			instance = new PanelLift();
		}

		return instance;
	}

	private Talon winch;
	private Encoder encoder;

	public PanelLift() {
		winch = new Talon(RobotMap.PANEL_LIFT_MOTOR);
		encoder = new Encoder(RobotMap.PANEL_LIFT_ENCODER1, RobotMap.PANEL_LIFT_ENCODER2);
		encoder.setDistancePerPulse(RobotMap.PANEL_LIFT_ENCODER_PULSE_RATE);
	}

	public void setSpeed(double speed) {
		winch.set(speed);
	}

	public double getHeight() {
		return encoder.getDistance();
	}

	public void resetEncoder() {
		encoder.reset();
	}

	// TODO:
	public boolean getZeroSwitch() {
		return false;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TeleOp());
	}
}
