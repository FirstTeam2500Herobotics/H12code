package frc.robot.driverStation;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subSystems.chassis.InvertDrive;
import frc.robot.subSystems.panel.ToggleActuator;
import frc.robot.subSystems.panel.ToggleExtender;

public class Controller {

	// USB ports that we are using for the differnt ports
	// Note: CoPilot is not being used right now
	public static final int PORT_PILOT_CONTROLLER = 0;
	public static final int PORT_COPILOT_CONTROLLER = 1;

	private Joystick pilot;
	private Joystick coPilot;

	public static Controller instance;

	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}

	// Supress the waring for resource leaks because we never save the
	// JoystickButtons in a var
	@SuppressWarnings("resource")
	public Controller() {
		pilot = new Joystick(PORT_PILOT_CONTROLLER);
		coPilot = new Joystick(PORT_COPILOT_CONTROLLER);

		// Bind all of the buttons to commands
		new JoystickButton(pilot, GamePad.Button.A).whenPressed(new ToggleActuator());
		new JoystickButton(pilot, GamePad.Button.B).whenPressed(new ToggleExtender());

		new JoystickButton(pilot, GamePad.Button.START).whenPressed(new InvertDrive());

		// new JoystickButton(pilot, GamePad.Button.BACK).whenPressed(new
		// ToggleCammera());
	}

	// Function to remove the controller not homing properly
	public double handleDeadband(double val, double deadband) {
		if (Math.abs(val) > deadband) {
			return val;
		}
		return 0;
	}

	// Function to remove the controller not homing properly
	public double handleDeadband(double val) {
		return handleDeadband(val, 0.1);
	}

	public double getChassisTurn() {
		double value = handleDeadband(pilot.getRawAxis(GamePad.Axis.LEFT_X));
		SmartDashboard.putNumber("Chassis Rotation", value);
		return value;
	}

	public double getChassisMove() {
		double value = handleDeadband(pilot.getRawAxis(GamePad.Axis.LEFT_Y));
		SmartDashboard.putNumber("Chassis Rotation", value);

		value *= -1;
		return value;
	}

	public double getBallLiftSpeed() {
		double value = handleDeadband(pilot.getRawAxis(GamePad.Axis.LEFT_X));
		SmartDashboard.putNumber("Chassis Rotation", value);
		return value;
	}

	public double getPanelLiftSpeed() {
		double value = coPilot.getRawAxis(2) - coPilot.getRawAxis(3);
		SmartDashboard.putNumber("Panel Lift Speed", value);
		return value;
	}

	public double getCarriageWheels() {
		double value = handleDeadband(coPilot.getRawAxis(GamePad.Axis.RIGHT_Y));
		SmartDashboard.putNumber("Carriage Wheels", value);
		return value;
	}
}