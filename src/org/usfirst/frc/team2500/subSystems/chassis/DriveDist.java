package org.usfirst.frc.team2500.subSystems.chassis;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class DriveDist extends PIDCommand {

	private final static double P = 1.0;
	private final static double I = 0.0;
	private final static double D = 0.1;
	
	private final static double sP = -0.1;
	
	private double distance;
	
	public DriveDist(double distance, double timeout){
        super("Drive",P, I, D);
		requires(Chassis.getInstance());
		
		getPIDController().setContinuous(false);
		getPIDController().setAbsoluteTolerance(0.1);
        setTimeout(timeout);

		this.distance = distance;
	}
	
	public void initialize(){
		//Make sure everything is 0 ed properly
		Chassis.getInstance().resetEncoder();
		Chassis.getInstance().resetGyro();

		System.out.println("Driving to: " + distance);
		getPIDController().setSetpoint(distance);
		
	}

	@Override
	protected double returnPIDInput() {
		return Chassis.getInstance().getAverageDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		Chassis.getInstance().arcadeDrive(output, Chassis.getInstance().getRotation() * sP,false,false);
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
