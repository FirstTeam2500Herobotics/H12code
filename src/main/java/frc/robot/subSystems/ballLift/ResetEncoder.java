
package frc.robot.subSystems.ballLift;

import edu.wpi.first.wpilibj.command.Command;

public class ResetEncoder extends Command {
    public void initialize(){
        BallLift.getInstance().resetEncoder();
    }

    protected boolean isFinished() {
        return true;
    }
}
