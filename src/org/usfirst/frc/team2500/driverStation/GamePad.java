package org.usfirst.frc.team2500.driverStation;

public class GamePad {
	
	public class Button {
		public static final int DPAD = 0;//GetPOV () direction for directional gamepad
		public static final int A = 1;//Bottom Button
		public static final int B = 2;//Right Button
		public static final int X = 3;//Left button
		public static final int Y = 4;//Top Button
		public static final int LB = 5;//Intake Block
		public static final int RB = 6;//Output Block
		public static final int BACK = 7;//	
		public static final int START = 8;//	
		public static final int LEFT_PRESS = 9;//	
		public static final int RIGHT_PRESS = 10;//
	}
	
	public class Axis {
		public static final int LEFT_X = 0;//	
		public static final int LEFT_Y = 1;//Chassis Move
		public static final int LT = 2;//Lift Down
		public static final int RT = 3;//Lift Up
		public static final int RIGHT_X = 4;//Chassis Rotate
		public static final int RIGHT_Y = 5;//	
	}
}
