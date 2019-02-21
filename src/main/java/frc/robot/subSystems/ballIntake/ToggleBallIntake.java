package frc.robot.subSystems.ballIntake;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleBallIntake extends Command {

    protected void initialize() {
        System.out.println("Toggling Ball Intake Actuator");
        BallIntake.getInstance().setPosition(!BallIntake.getInstance().getPosition());
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}