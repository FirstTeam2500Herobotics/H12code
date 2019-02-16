package frc.robot.subSystems.lift;

import frc.robot.driverStation.Controller;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleOp extends Command {

    public TeleOp() {
    	requires(Lift.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("starting lift teleop");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Lift.getInstance().setSpeed(Controller.getInstance().get_Triggers());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {

    }
}
