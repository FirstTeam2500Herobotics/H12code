package frc.robot.subSystems.ballLift;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ZeroBallLift extends CommandGroup {

    public ZeroBallLift() {
        //Quickly get to the bottom of the lift
        addSequential(new FastZeroBallLift(10));
        //Move a tiny bit up
        addSequential(new SetBallHeight(1,1));
        //Move slower to the bottom to get an exact 0
        addSequential(new SlowZeroBallLift(10));
    }

    public void initialize() {
        System.out.println("Zeroing Ball Lift");
    }
}
