package frc.robot.subSystems.panelLift;

import frc.robot.driverStation.Controller;

import edu.wpi.first.wpilibj.command.Command;

public class TeleOp extends Command {

    public TeleOp() {
        requires(PanelLift.getInstance());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        PanelLift.getInstance().setSpeed(Controller.getInstance().getPanelLiftSpeed());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
}
