package frc.robot.subSystems.lift;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class SetPanelHeight extends PIDCommand {

    private final static double P = 1.0;
    private final static double I = 0.0;
    private final static double D = 0.1;

    private final static double sP = -0.1;

    private double target;

    public SetPanelHeight(double target, double timeout){
        super("Drive",P, I, D);
        requires(Lift.getInstance());

        getPIDController().setContinuous(false);
        getPIDController().setAbsoluteTolerance(0.1);
        setTimeout(timeout);

        this.target = target;
    }

    public void initialize(){
        System.out.println("Setting Panel Lift to: " + target);
        getPIDController().setSetpoint(target);
    }

    @Override
    protected double returnPIDInput() {
        return Lift.getInstance().getPanelHeight();
    }

    @Override
    protected void usePIDOutput(double output) {
        Lift.getInstance().setPanelSpeed(output);
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
