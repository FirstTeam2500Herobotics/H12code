package frc.robot.subSystems.panel;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleExtender extends Command {

    public ToggleExtender() {

    }

    protected void initialize() {
        System.out.println("Toggling Panel Extender");
        Panel.getInstance().setExtention(!Panel.getInstance().getExtention());
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}