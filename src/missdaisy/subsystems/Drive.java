package missdaisy.subsystems;

import edu.wpi.first.wpilibj.Talon;
import missdaisy.Inputs;

/**
 * <p>A class to represent the H drive that we are experimenting with.
 * This has basic functionality of setting the foward/backward motors and 
 * the left/right motors independantly.</p> 
 * 
 * <p>The drive base is split into two types of motors: the forward/backwards motors,
 * which here we call the drive left/right drive motors (because they can be thought of 
 * the left and right sides of a tank drive), and the strafe left/right motors, which
 * here we call the strafe left/right motors (because they control the robot's left/
 * right strafe movements). </p>
 * 
 * <p>There are functions to set each motor's speed, as well as functions that combine
 * two inputs to move the robot. For example, there is a setDriveSpeedTurn() function which
 * will take a speed input and a turn input, and process them to create a motor output
 * to the different motors to accomplish the goal of turning a certain speed (a useful
 * functionality to this function is the ability to turn in place by setting the speed
 * parameter to 0.0).</p>
 * 
 * <p>You'll notice that the two pairs of motors switch roles if you look at the H-Drive
 * rotated 90 degrees. Keep this in mind when predicting the movement of this robot with 
 * certain inputs</p>
 * 
 * @author Joshua Sizer
 *
 */

public class Drive {
	/* 
	 * These are our motor objects. Motors are controlled by speed controllers, which send a 
	 * variable voltage, between -12 and 12 volts, to the motor based on the input PWM 
	 * (pulse width modulation) signal. Talons and Victors are types of speed controllers.
	 * 
	 * These are private for consistency sake, as well as for good code-practice. In java,
	 * intance data is usually kept private, and the object that the data is held within 
	 * will have methods (functions) for mutating them, usually called getters and setters.
	 * 
	 * Later, we will have to set two of these motors to be inverted. This means that if you 
	 * set the speed of the motor to be 1.0, the motor object will actually send a -1.0 to the
	 * motor. This is useful since when two motors are 180 degrees apart, they will spin in 
	 * opposite directions with the same input voltage. 
	 * 
	 */
	
	private Talon leftDriveMotor;
	private Talon rightDriveMotor;
	private Talon leftStrafeMotor;
	private Talon rightStrafeMotor;
	
	/**
	 * Constructs an H-Drive object. This has low level calls to set the drive motors
	 * in specific and useful ways.
	 */
	public Drive() {
		/*
		 *  Creates the motor objects by passing in the port number that the PWM wires
		 *  are plugged into on the Roborio. The Inputs.PWMs class is a container class
		 *  for storing all the port number that the different devices are plugged into.
		 */
		leftDriveMotor = new Talon(Inputs.PWMs.LeftDriveMotor);
		rightDriveMotor = new Talon(Inputs.PWMs.RightDriveMotor);
		leftStrafeMotor = new Talon(Inputs.PWMs.StrafeLeftMotor);
		rightStrafeMotor = new Talon(Inputs.PWMs.StrafeRightMotor);
	}
	
	/* 
	 * ************************************************************************
	 * "Tank drive" control for the left and right drive and strafe motors. 
	 * ************************************************************************
	 */
	
	/**
	 * This function allows you to set the left and right drive motors independantly
	 * in the same call. 
	 * @param leftInput A double between -1.0 and 1.0
	 * @param rightInput A double between -1.0 and 1.0
	 */
	public void setDrive(double leftInput, double rightInput) {
		setLeftDrive(leftInput);
		setRightDrive(rightInput);
	}
	
	/**
	 * This funtion allow you set the left and right strafe motors independantly
	 * in the same call.
	 * @param leftInput A double between -1.0 and 1.0
	 * @param rightInput A double between -1.0 and 1.0
	 */
	public void setStrafe(double leftInput, double rightInput) {
		setLeftStrafe(leftInput);
		setRightStrafe(rightInput);
	}
	
	/*
	 * *****************************************************************************************
	 * Speed/Turn control for the left and right drive and strafe motors, and both combined. 
	 * These will accept a speed and a turn parameter. If the turn is kept 0.0 and the speed 
	 * non-zero, the robot will move forwards/backwards. If the speed is kept 0.0 and the turn 
	 * non-zero, the robot will turn in place
	 * ****************************************************************************************
	 */
	
	/**
	 * Sets the drive motor's speed and turn seperately.
	 * @param speed A double between -1.0 and 1.0
	 * @param turn A double between -1.0 and 1.0
	 */
	public void setDriveSpeedTurn(double speed, double turn) {
		double leftMotorSpeed = speed + turn;
		double rightMotorSpeed = speed - turn;
		setDrive(leftMotorSpeed, rightMotorSpeed);
	}
	
	/**
	 * Sets the strafe motor's speed and turn seperately.
	 * @param speed A double between -1.0 and 1.0
	 * @param turn A double between -1.0 and 1.0
	 */
	public void setStrafeSpeedTurn(double speed, double turn) {
		double leftMotorSpeed = speed + turn;
		double rightMotorSpeed = speed - turn;
		setStrafe(leftMotorSpeed, rightMotorSpeed);
	}
	
	/**
	 * Sets the turn of the whole robot. This is for spinning in place. 
	 * If all motors are trying to push forwards, then the robot should just spin. 
	 * @param turn
	 */
	public void setTurn(double turn) {
		setDrive(turn, turn);
		setStrafe(turn, turn);
	}
	
	/* 
	 * ********************************************************************************
	 * These four are wrapper functions that allow you to access methods in the class's
	 * private motor objects.
	 * ********************************************************************************
	 */
	
	/**
	 * Set the left drive motors speed.
	 * @param input A double between -1.0 and 1.0
	 */
	public void setLeftDrive(double input) {
		leftDriveMotor.set(input);
	}
	
	/**
	 * Set the right drive motor speed.
	 * @param input A double between -1.0 and 1.0
	 */
	public void setRightDrive(double input) {
		rightDriveMotor.set(input);
	}
	
	/**
	 * Set the left strafe motor speed
	 * @param input A double between -1.0 and 1.0
	 */
	public void setLeftStrafe(double input) {
		leftStrafeMotor.set(input);
	}
	
	/**
	 * Set the right strafe motor speed
	 * @param input A double between -1.0 and 1.0
	 */
	public void setRightStrafe(double input) {
		rightStrafeMotor.set(input);
	}
	
	/**
	 * Puts the drive back into a known state. In this case, not moving. 
	 */
	public void reset() {
		setDrive(0.0, 0.0);
		setStrafe(0.0, 0.0);
	}
}
