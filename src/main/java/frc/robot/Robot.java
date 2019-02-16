package frc.robot;

import frc.robot.autonomous.BaseLine;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	//Things for the dashboard for picking auto
	Command autonomousCommand;
	SendableChooser<String> autonomousChooser = new SendableChooser<>();
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//Adds a box in the dropdown for each auto mode (default auto if none picked is baseline)
		autonomousChooser.addDefault("Base Line", "Base Line");
		SmartDashboard.putData("Auto mode", autonomousChooser);
		
		CameraServer.getInstance().startAutomaticCapture();
	}
	
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		
	}

	@Override
	public void autonomousInit() {
		//Check what auto is picked
		switch(autonomousChooser.getSelected()){
		case "Base Line":
			autonomousCommand = new BaseLine();
		default:
			System.out.println("No auto picked");
			break;
		}

		//Start running whatever auto is picked
		if (autonomousCommand != null){
			autonomousCommand.start();
		}
		else{
			System.out.println("Something went wrong in starting auto");
		}
	}

	@Override
	public void autonomousPeriodic() {
		//Run auto
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when teleop starts running.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		
		Scheduler.getInstance().run();
	}
}
