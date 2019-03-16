package frc.robot.subSystems.ballLift;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class SetBallHeight extends PIDCommand {

    private final static double P = 1.0;
    private final static double I = 0.0;
    private final static double D = 0.1;

    private double target;

    public SetBallHeight(double target, double timeout) {
        super("Drive", P, I, D);
        requires(BallLift.getInstance());

        getPIDController().setContinuous(false);
        getPIDController().setAbsoluteTolerance(0.1);
        setTimeout(timeout);

        this.target = target;
    }

    public void initialize() {
        System.out.println("Setting Ball Lift to: " + target);
        getPIDController().setSetpoint(target);
    }

    @Override
    protected double returnPIDInput() {
        return BallLift.getInstance().getHeight();
    }

    @Override
    protected void usePIDOutput(double output) {
        BallLift.getInstance().setSpeed(output);
    }

    protected boolean isFinished() {
        return isTimedOut() || getPIDController().onTarget();
    }

    protected void end() {

    }

    protected void interrupted() {
        end();
    }
}
