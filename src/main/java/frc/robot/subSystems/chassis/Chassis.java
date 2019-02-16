package frc.robot.subSystems.chassis;
  
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// import com.kauailabs.navx.frc.AHRS;

//TODO: Get a gyro

public class Chassis extends Subsystem{

	//Making only one chassis exist in the code
	public static Chassis instance;
	
	//This function return the chassis to be used in other places
	public static Chassis getInstance(){
		if (instance == null)
		   instance = new Chassis();
	
		return instance;
    }
	
    //To get this number put the robot on the ground with a test program that just prints encoder distance to the console.
	//After that push the robot in a straight line for x inches (I do like 250) then divide that by the number you got (ie. 250/13208.5)
	private static final double ENCODER_PULSE_RATE = 250/13208.5;
	
	//The drive wheels and the encoders that go with them
	private Talon leftMotor;
	private Encoder leftEncoder;

	private Talon rightMotor;
	private Encoder rightEncoder;

	//Scaler for if we want to switch the direction
	public double driveScale = 1;

	// private AHRS gyro;

	private DigitalOutput frontLED;

	public Chassis(){
		leftMotor = new Talon(RobotMap.DRIVE_LEFT_MOTOR);
		leftEncoder = new Encoder(RobotMap.DRIVE_LEFT_ENCODER1,RobotMap.DRIVE_LEFT_ENCODER2);

		rightMotor = new Talon(RobotMap.DRIVE_RIGHT_MOTOR);
		rightEncoder = new Encoder(RobotMap.DRIVE_RIGHT_ENCODER1,RobotMap.DRIVE_RIGHT_ENCODER2);

		leftEncoder.setDistancePerPulse(ENCODER_PULSE_RATE);
		//The right is negitive because it is a mirror of the left side
		rightEncoder.setDistancePerPulse(-ENCODER_PULSE_RATE);
		
		// gyro = new AHRS(SPI.Port.kMXP);

		frontLED = new DigitalOutput(RobotMap.DRIVE_DIRECTION_LED);

		reset();
	}
	
	//Will always be using arcade drive when nothing else is using it
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TeleOp());
	}

	public void resetEncoder(){
		leftEncoder.reset();
		rightEncoder.reset();
	}

	public void resetGyro() {
		// gyro.reset();
	}

	public void reset(){
		resetEncoder();
		resetGyro();
	}
	
	public double getAverageDistance(){
		return (getLeftDistance() + getRightDistance())/2;
	}
	
	public double getLeftDistance(){
		double distance = leftEncoder.getDistance();
    	SmartDashboard.putNumber("Chassis Left Distance",distance);
		return distance;
	}
	
	public double getRightDistance(){
		double distance = rightEncoder.getDistance();
    	SmartDashboard.putNumber("Chassis Right Distance",distance);
		return distance;
	}
	
	public double getAverageRate(){
		return (getLeftRate() + getRightRate())/2;
	}
	
	public double getLeftRate(){
		double rate = leftEncoder.getRate();
    	SmartDashboard.putNumber("Chassis Right rate",rate);
		return rate;
	}
	
	public double getRightRate(){
		double rate = rightEncoder.getRate();
    	SmartDashboard.putNumber("Chassis Right rate",rate);
		return rate;
	}
	
	public double getRotation() {
		// double angle = gyro.getAngle();
		double angle = 0;
		SmartDashboard.putNumber("rotation", angle);
		return angle;
	}

	public void setDirectionLED(boolean direction) {
		frontLED.set(direction);
	}

	public void toggleDirectionLED() {
		frontLED.set(!frontLED.get());
	}

	public void tankDrive(double left,double right){
		leftMotor.setSpeed(left * driveScale);
		rightMotor.setSpeed(right * driveScale);
	}
	
	public void arcadeDrive(double throttle, double turn, boolean square){

	    double leftMotorOutput;
	    double rightMotorOutput;

	    //Square inputs preserving sign
	    if(square){
			throttle = throttle * throttle * (throttle / Math.abs(throttle));
            turn = turn * turn * (turn / Math.abs(turn));
		}

		//Get the largest value for any input
	    double maxInput = Math.copySign(Math.max(Math.abs(throttle), Math.abs(turn)), throttle);

	    if (throttle >= 0.0) {
	      // First quadrant, else second quadrant
	      if (turn >= 0.0) {
	        leftMotorOutput = maxInput;
	        rightMotorOutput = throttle - turn;
	      } else {
	        leftMotorOutput = throttle + turn;
	        rightMotorOutput = maxInput;
	      }
	    }
	    else {
	      // Third quadrant, else fourth quadrant
	      if (turn >= 0.0) {
	        leftMotorOutput = throttle + turn;
	        rightMotorOutput = maxInput;
	      } else {
	        leftMotorOutput = maxInput;
	        rightMotorOutput = throttle - turn;
	      }
	    }
	    
	    tankDrive(leftMotorOutput,rightMotorOutput * -1);
	}

	public void arcadeDrive(double throttle, double turn) {
		arcadeDrive(throttle, turn, true);
	}
}
