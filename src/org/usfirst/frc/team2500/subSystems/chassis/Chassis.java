package org.usfirst.frc.team2500.subSystems.chassis;
  
 import org.usfirst.frc.team2500.robot.RobotMap;
  
 import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SPI;
 import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Chassis extends Subsystem{

	//Making only one chassis exist in the code
	public static Chassis instance;
	
	public static Chassis getInstance()
    {
		if (instance == null)
		   instance = new Chassis();
	
		return instance;
    }

	final double encoderPulceRate = 250/13208.5;
	
	private Talon leftMotor;
	private Encoder leftEncoder;

	private Talon rightMotor;
	private Encoder rightEncoder;
	
	//This is the shifter for the gear boxes. Both sides are hooked up to the same solenoid so they cant fire independely
	//If build yells at you about only one side shifting tell them to go fuck themselfs -John
	private Solenoid shifter;
	
	public double driveScale = 1;

	private AHRS gyro;
	
	public Chassis(){
		leftMotor = new Talon(RobotMap.LEFT_DRIVE_PORT);
		leftEncoder = new Encoder(RobotMap.LEFT_ENCODER_PORT1,RobotMap.LEFT_ENCODER_PORT2);

		rightMotor = new Talon(RobotMap.RIGHT_DRIVE_PORT);
		rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER_PORT1,RobotMap.RIGHT_ENCODER_PORT2);

		leftEncoder.setDistancePerPulse(encoderPulceRate);
		leftEncoder.reset();
		//The right is negitive because it is a mirror of the left side
		rightEncoder.setDistancePerPulse(-encoderPulceRate);
		rightEncoder.reset();
		
		shifter = new Solenoid(RobotMap.SHIFTER_PORT);
		
		gyro = new AHRS(SPI.Port.kMXP);
		gyro.reset();
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
		gyro.reset();
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
	
	public double getRotation(){
    	SmartDashboard.putNumber("rotation",gyro.getAngle());
		return gyro.getAngle();
	}

	//Auto shifting
	private final double MAX_HIGH_GEAR_SPEED = 160;
	private final double MAX_LOW_GEAR_SPEED = 55;

	//Magic number used to make highgear autoshift smooth
	//It assumes that the acceleration is linear and makes the low gear max speed match up with that same value on the highgear equation
	private final double MIN_MAX_CONVERTER = 1/(MAX_LOW_GEAR_SPEED/MAX_HIGH_GEAR_SPEED);

	//What persenct of maxspeed do we shift at because we usualy dont get all the way up
	private final double LOW_GEAR_SHIFT_PERCENT_HIGH = 0.9;
	//Switch back a bit lower then the switch up to stop rapid toggle between the two
	private final double LOW_GEAR_SHIFT_PERCENT_LOW = 0.6;

	public void shiftingTankDrive(double left,double right){
		double averageRate = Math.abs(getAverageRate());

		if(averageRate > MAX_LOW_GEAR_SPEED * LOW_GEAR_SHIFT_PERCENT_HIGH){
			shifter.set(true);
			tankDrive(left,right);
			return;
		}
		if(averageRate < MAX_LOW_GEAR_SPEED * LOW_GEAR_SHIFT_PERCENT_LOW){
			shifter.set(false);
		}

		tankDrive(left*MIN_MAX_CONVERTER,right*MIN_MAX_CONVERTER);
	}
	


	public void tankDrive(double left,double right){
		leftMotor.setSpeed(left * driveScale);
		rightMotor.setSpeed(right * driveScale);
	}

	public void arcadeDrive(double throttle, double turn) {
	    arcadeDrive(throttle, turn, true, true);
	}
	
	public void arcadeDrive(double throttle,double turn, boolean square, boolean shifters){

	    double leftMotorOutput;
	    double rightMotorOutput;

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
	    } else {
	      // Third quadrant, else fourth quadrant
	      if (turn >= 0.0) {
	        leftMotorOutput = throttle + turn;
	        rightMotorOutput = maxInput;
	      } else {
	        leftMotorOutput = maxInput;
	        rightMotorOutput = throttle - turn;
	      }
	    }
	    
	    if(shifters){
	    	shiftingTankDrive(leftMotorOutput,rightMotorOutput * -1);
	    }
	    else{
	    	tankDrive(leftMotorOutput,rightMotorOutput * -1);
	    }
	}
	
	public boolean getGear(){
		return shifter.get();
	}
}
