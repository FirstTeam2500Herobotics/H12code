package frc.robot;

import edu.wpi.first.wpilibj.command.Command;

public class Wait extends Command {
	// This is the command for waiting a amount of time

	public Wait(double time) {
		setTimeout(time);
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}
}