package frc.robot.subSystems.lift;

import frc.robot.RobotMap;
import frc.robot.subSystems.lift.TeleOp;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lift extends Subsystem{

	public static Lift instance;

	public static Lift getInstance(){
		if (instance == null){
			instance = new Lift();
		}

		return instance;
  }

	private Talon ballWinch;
	private Talon panelWinch;
	private Encoder ballEncoder;
	private Encoder panelEncoder;

	public Lift(){
		ballWinch = new Talon(RobotMap.BALL_LIFT_MOTOR);
		panelWinch = new Talon(RobotMap.PANEL_LIFT_MOTOR);
		ballEncoder = new Encoder(RobotMap.BALL_LIFT_ENCODER1, RobotMap.BALL_LIFT_ENCODER2);
		panelEncoder = new Encoder(RobotMap.PANEL_LIFT_ENCODER1, RobotMap.PANEL_LIFT_ENCODER2);
	}

	public void setBallSpeed(double speed){
		ballWinch.set(speed);
	}

	public double getBallHeight() {
		return ballEncoder.getDistance();
	}

	public void resetBallEncoder() {
		// TODO:
	}

	public void setPanelSpeed(double speed){
		panelWinch.set(speed);
	}

	public double getPanelHeight() {
		return panelEncoder.getDistance();
	}

	public void resetPanelEncoder() {
		// TODO:
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TeleOp());
	}
}
