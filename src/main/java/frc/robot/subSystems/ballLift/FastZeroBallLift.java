package frc.robot.subSystems.ballLift;

import edu.wpi.first.wpilibj.command.Command;

public class FastZeroBallLift extends Command {

    public FastZeroBallLift(double timeout) {
        requires(BallLift.getInstance());

        setTimeout(timeout);
    }

    public void execute() {
        BallLift.getInstance().setSpeed(-1);
    }

    protected boolean isFinished() {
        return isTimedOut() || BallLift.getInstance().getZeroSwitch();
    }

    protected void end() {
        BallLift.getInstance().setSpeed(0);
    }

    protected void interrupted() {
        end();
    }
}
