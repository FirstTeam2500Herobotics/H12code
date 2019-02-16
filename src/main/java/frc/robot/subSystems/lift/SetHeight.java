package frc.robot.subSystems.lift;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class SetHeight extends PIDCommand {

    private final static double P = 1.0;
    private final static double I = 0.0;
    private final static double D = 0.1;

    private final static double sP = -0.1;

    private double target;

    public SetHeight(double target, double timeout){
        super("Drive",P, I, D);
        requires(Lift.getInstance());

        getPIDController().setContinuous(false);
        getPIDController().setAbsoluteTolerance(0.1);
        setTimeout(timeout);

        this.target = target;
    }

    public void initialize(){
        //Make sure everything is 0 ed properly
        Lift.getInstance().resetEncoder();

        System.out.println("Setting Lift to: " + target);
        getPIDController().setSetpoint(target);

    }

    @Override
    protected double returnPIDInput() {
        return Lift.getInstance().getHeight();
    }

    @Override
    protected void usePIDOutput(double output) {
        Lift.getInstance().setSpeed(output);
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
