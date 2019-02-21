package frc.robot.subSystems.ballCarriage;

import frc.robot.driverStation.Controller;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleOp extends Command {

    public TeleOp() {
        requires(BallCarriage.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("starting lift teleop");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        BallCarriage.getInstance().setSpeed(Controller.getInstance().getCarriageWheels());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
}
