package frc.robot.subSystems.ballIntake;

import edu.wpi.first.wpilibj.command.Command;

public class SetBallIntakeWheels extends Command {

    private double speed;

    public SetBallIntakeWheels(double speed){
        this.speed = speed;
    }

    protected void initialize() {
        System.out.println("Setting ball intake to speed: " + speed);
        BallIntake.getInstance().setSpeed(speed);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}