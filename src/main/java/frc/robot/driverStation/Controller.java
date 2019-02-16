package frc.robot.driverStation;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Controller {

	public static final int PORT_DRIVER_CONTROLLER = 0;
	
	private Joystick pilot;
	
	public static Controller instance;
	
	public static Controller getInstance()
    {
		if (instance == null)
		   instance = new Controller();
	
		return instance;
    }
	
	public Controller(){
    	pilot = new Joystick(PORT_DRIVER_CONTROLLER);
    	
    	createDriverstaion();

    	//new JoystickButton(pilot,GamePad.A).whenPressed(new ToggleClaw());
	}
	
	//make the spots on the dashboard for everything
	private void createDriverstaion(){
    	SmartDashboard.putNumber("Pilot Throttle",0);
    	SmartDashboard.putNumber("Pilot Steering",0);
    	SmartDashboard.putNumber("Left Speed",0);
    	SmartDashboard.putNumber("Right Speed",0);
    	SmartDashboard.putNumber("Left Dist",0);
    	SmartDashboard.putNumber("Right Dist",0);
    	SmartDashboard.putNumber("Rotation",0);
	}
	
    //Function to remove the controller not homing properly
	public double handleDeadband(double val, double deadband) {
		if(Math.abs(val) > deadband){
			return val;
		}
		return 0;
	}

	//Return the proper x and update the dashboard with it
	public double getTurn() {
		double turn = pilot.getRawAxis(GamePad.Axis.LEFT_X);
		return handleDeadband(turn,0.05);
	}

	//Return the proper y and update the dashboard with it
	public double getMove() {
		double move = pilot.getRawAxis(GamePad.Axis.LEFT_Y) * -1;
		return handleDeadband(move,0.05);
	}
    
    //Return the value you get from subtracting the triggers
    public double get_Triggers(){
    	double value = pilot.getRawAxis(2) - pilot.getRawAxis(3);
    	SmartDashboard.putNumber("Lift Speed",value);
    	return value;
    }
}