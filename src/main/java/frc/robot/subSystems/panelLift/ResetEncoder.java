
package frc.robot.subSystems.panelLift;

import edu.wpi.first.wpilibj.command.Command;

public class ResetEncoder extends Command {
    public void initialize() {
        PanelLift.getInstance().resetEncoder();
    }

    protected boolean isFinished() {
        return true;
    }
}
