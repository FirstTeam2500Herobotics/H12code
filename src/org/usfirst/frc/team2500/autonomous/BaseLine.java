package org.usfirst.frc.team2500.autonomous;

import org.usfirst.frc.team2500.subSystems.chassis.DriveDist;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BaseLine extends CommandGroup {

    public BaseLine() {
    	
    	addSequential(new DriveDist(AutoDistances.BaseLine.DISTANCE, AutoDistances.BaseLine.TIME_OUT));
    }
}