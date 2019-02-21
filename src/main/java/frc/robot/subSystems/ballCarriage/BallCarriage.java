package frc.robot.subSystems.ballCarriage;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Solenoid;

public class BallCarriage extends Subsystem {

    public static BallCarriage instance;

    public static BallCarriage getInstance() {
        if (instance == null) {
            instance = new BallCarriage();
        }

        return instance;
    }

    private Talon wheels;

    public BallCarriage() {
        wheels = new Talon(RobotMap.BALL_CARRIAGE_WHEELS);
    }

    public void setSpeed(double speed) {
        wheels.set(speed);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new TeleOp());
    }
}
