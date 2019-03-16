package frc.robot.autonomous;

import frc.robot.subSystems.chassis.DriveDist;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BaseLine extends CommandGroup {

    public BaseLine() {

        addSequential(new DriveDist(AutoDistances.BaseLine.DISTANCE, AutoDistances.BaseLine.TIME_OUT));
    }
}