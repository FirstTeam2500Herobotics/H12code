package frc.robot.driverStation;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ToggleCammera extends Command {

	public ToggleCammera(){
		
	}
	
	protected void initialize() {
        // System.out.println("Toggleing the cammera");
        // if(CameraServer.getInstance().getServer().getSource() == Robot.cam0){
        //     CameraServer.getInstance().getServer().setSource(Robot.cam1);
        // }
        // else{
        //     CameraServer.getInstance().getServer().setSource(Robot.cam0); 
        // }
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
}