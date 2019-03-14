package frc.robot.subSystems.chassis;

import frc.robot.driverStation.Controller;

import edu.wpi.first.wpilibj.command.Command;

public class TeleOp extends Command {

    public TeleOp() {
    	//Make the code stop others from using the chassis when this is using it
        requires(Chassis.getInstance());
    }
    
    protected void execute(){
    	Chassis chassis = Chassis.getInstance();
    	//Get the values that the motors should be moving at and set them
    	double turnValue = Controller.getInstance().getChassisTurn();
    	double moveValue = Controller.getInstance().getChassisMove();
    	chassis.arcadeDrive(moveValue, turnValue, false);
    }
    
    //Never quit teleop
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }
}
