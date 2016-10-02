package missdaisy;

/**
 * This is a class which holds the information of which port devices are plugged into.
 * 
 * @author Joshua Sizer
 *
 */
public class Inputs {
	public static class PWMs{
		public static int LeftDriveMotor = 0;
		public static int RightDriveMotor = 1;
		public static int StrafeLeftMotor = 2;
		public static int StrafeRightMotor = 3;
	}
	
	public static class XboxControllerPorts {
		public static int Drive = 0;
	}
}
