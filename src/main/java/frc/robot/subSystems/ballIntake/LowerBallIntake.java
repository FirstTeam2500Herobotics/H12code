package frc.robot.subSystems.ballIntake;

import edu.wpi.first.wpilibj.command.Command;

public class LowerBallIntake extends Command {

    protected void initialize() {
        System.out.println("Lowering Ball Intake Actuator");
        BallIntake.getInstance().setPosition(false);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}