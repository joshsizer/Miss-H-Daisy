package missdaisy;

import edu.wpi.first.wpilibj.IterativeRobot;
import missdaisy.subsystems.Drive;
import missdaisy.utilities.DaisyMath;
import missdaisy.utilities.XboxController;

/**
 * This is the main class that has functions that are called by the driver station.
 * 
 * @author joshs
 *
 */
public class Robot extends IterativeRobot {
	// Declares our drive object. We'll initalize it in this class's contructor. 
	private Drive drive;
	
	// Creates an Xbox controller object. This is used as human input.
	private XboxController controller;
	
	public Robot() {
		/*
		 * Creates a new drive object. In the actual robot code, 
		 * we use a singleton pattern, where the Drive class has a 
		 * .getInstance() function. This is not important for now, though. 
		 */
		drive = new Drive();
		controller = new XboxController(Inputs.XboxControllerPorts.Drive);
	}
	
	/**
	 * Called once while the robot is starting up
	 */
	@Override
	public void robotInit() {
		// just to make sure everythings the way it should be
		drive.reset();
	}
	
	/**
	 * This gets called once at the start of teleop.
	 */
	@Override
	public void teleopInit() {
		
	}
	
	/**
	 * This is the function that is called by the driver station every 20 ms during teleop. 
	 */
	@Override
	public void teleopPeriodic() {
		/*
		 * This gets the Xbox controllers current right stick's Y-axis and left stick's X-axis.
		 * A dead band is applied to this, meaning that if the values from the left and right stick
		 * are less than 0.1 and greater than -0.1, it returns a 0.0. This is because small
		 * movement in the Xbox controllers stick are inevitable, but undesirable when moving the
		 * robot. Ask more about this if you're interested.
		 */
		double driveMotorSpeed = -1.0
				* DaisyMath.applyDeadband(controller.getLeftYAxis(), Constants.XboxController.DEAD_BAND);
		double strafeMotorSpeed = -1.0
				* DaisyMath.applyDeadband(controller.getRightXAxis(), Constants.XboxController.DEAD_BAND);
		drive.setDrive(driveMotorSpeed, driveMotorSpeed);
		drive.setStrafe(strafeMotorSpeed, strafeMotorSpeed);
	}
}
