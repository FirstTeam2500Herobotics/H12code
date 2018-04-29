package org.usfirst.frc.team2500.subSystems.chassis;

import edu.wpi.first.wpilibj.command.Command;

public class InvertDrive extends Command {

	public InvertDrive(){
		
	}
	
    protected void initialize() {
		System.out.println("Inverting the drive");
    	Chassis.getInstance().driveScale = Chassis.getInstance().driveScale * -1;
    }
	
	@Override
	protected boolean isFinished() {
		return true;
	}
}