package frc.robot;

import frc.robot.subSystems.chassis.Chassis;
import frc.robot.subSystems.panel.Panel;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
	// Things for the dashboard for picking auto
	Command autonomousCommand;
	SendableChooser<String> autonomousChooser = new SendableChooser<>();

	// public static UsbCamera cam0 = new UsbCamera("cam0", 0);
	// public static UsbCamera cam1 = new UsbCamera("cam1", 1);

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		// Adds a box in the dropdown for each auto mode (default auto if none picked is baseline)
		// autonomousChooser.addDefault("Base Line", "Base Line");
		SmartDashboard.putData("Auto mode", autonomousChooser);
		// Set up the cammeras
		CameraServer.getInstance().startAutomaticCapture("cam0", 0);
		// CameraServer.getInstance().startAutomaticCapture("cam1", 1);

		// Subsytems are created the first time we get a instance so we are just
		// creating them now
		Chassis.getInstance();
		Panel.getInstance();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	/**
	 * This function is called once each time time the robot enters Autonoums.
	 */
	@Override
	public void autonomousInit() {
		// We want to make sure that we dont have problems if we forget to create a
		// autonomouseChooser in the driver station
		if (autonomousChooser.getSelected() != null) {
			// Check what auto is picked
			switch (autonomousChooser.getSelected()) {
			// case "Base Line":
			// autonomousCommand = new BaseLine();
			case "TeliOp":
				// If we are doing teliop just let the default command in chassis and everything
				// else do its thing
				break;
			default:
				System.out.println("No auto picked");
				break;
			}
		}

		// Start running whatever auto is picked if one is picked
		if (autonomousCommand != null) {
			autonomousCommand.start();
		} else {
			System.out.println("Something went wrong in starting auto");
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when teleop starts running.
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
}
