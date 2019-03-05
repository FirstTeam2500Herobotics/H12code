package frc.robot.subSystems.panelLift;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ZeroPanelLift extends CommandGroup {

    public ZeroPanelLift() {
        //Quickly get to the bottom of the lift
        addSequential(new FastZeroPanelLift(10));
        //Move a tiny bit up
        addSequential(new SetPanelHeight(1,1));
        //Move slower to the bottom to get an exact 0
        addSequential(new SlowZeroPanelLift(10));
    }

    public void initialize() {
        System.out.println("Zeroing Panel Lift");
    }
}
