package frc.robot.subSystems.ballLift;

import frc.robot.driverStation.Controller;

import edu.wpi.first.wpilibj.command.Command;

public class TeleOp extends Command {

    public TeleOp() {
    	requires(BallLift.getInstance());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        BallLift.getInstance().setSpeed(Controller.getInstance().getBallLiftSpeed());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
}
