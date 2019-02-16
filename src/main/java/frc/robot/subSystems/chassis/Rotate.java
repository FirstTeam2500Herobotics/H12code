package frc.robot.subSystems.chassis;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class Rotate extends PIDCommand{
	private final static double P = 8.8;
	private final static double I = 24.0;
	private final static double D = 19.4;
	
	private double degrees;
	
    public Rotate(double degrees, double timeout) {
        super("Rotate",P, I, D);
        requires(Chassis.getInstance());

		getPIDController().setContinuous(false);
		getPIDController().setAbsoluteTolerance(0.1);
        setTimeout(timeout);
        
        this.degrees = degrees;
    }
	
	public void initialize(){
		Chassis.getInstance().resetGyro();
		System.out.println("Rotating to: " + degrees);
		getPIDController().setSetpoint(degrees);
	}

	@Override
	protected double returnPIDInput() {
//		System.out.println(Chassis.getInstance().getRotation());
		return Chassis.getInstance().getRotation();
	}

	@Override
	protected void usePIDOutput(double output) {
		Chassis.getInstance().arcadeDrive(0, output);
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
