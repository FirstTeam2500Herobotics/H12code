package frc.robot.subSystems.panelLift;

import edu.wpi.first.wpilibj.command.Command;

public class FastZeroPanelLift extends Command {

    public FastZeroPanelLift(double timeout) {
        requires(PanelLift.getInstance());

        setTimeout(timeout);
    }

    public void execute() {
        PanelLift.getInstance().setSpeed(-1);
    }

    protected boolean isFinished() {
        return isTimedOut() || PanelLift.getInstance().getZeroSwitch();
    }

    protected void end() {
        PanelLift.getInstance().setSpeed(0);
    }

    protected void interrupted() {
        end();
    }
}
