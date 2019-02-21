package frc.robot.subSystems.panel;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleActuator extends Command {

    public ToggleActuator() {

    }

    protected void initialize() {
        System.out.println("Toggling Panel Actuator");
        Panel.getInstance().setActuator(!Panel.getInstance().getActuator());
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}