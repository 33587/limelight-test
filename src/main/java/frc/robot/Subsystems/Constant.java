package frc.robot.Subsystems;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import frc.robot.Util.Conversions;

public class Constant {
    public static final class DriveConstants {
        public static final double LFOff = 0.245 * Conversions.twoPi;//CANCoder offset in radians
        public static final int LFTurnMotor = 11;
        public static final int LFDriveMotor = 20;
        public static final int LFCanID = 61;
        public static final double RFOff = 0.271 * Conversions.twoPi;
        public static final int RFTurnMotor = 15;
        public static final int RFDriveMotor = 12;
        public static final int RFCanID = 62;
        public static final double LBOff = -0.189 * Conversions.twoPi;
        public static final int LBTurnMotor = 19;
        public static final int LBDriveMotor = 17;
        public static final int LBCanID = 60;
        public static final double RBOff = 0.454 * Conversions.twoPi;
        public static final int RBTurnMotor = 9;
        public static final int RBDriveMotor = 13;
        public static final int RBCanID = 59;
        public static final double trackWidth = Units.inchesToMeters(24.0);//wheel center to center width
        public static final double wheelBase = Units.inchesToMeters(25.0);//wheel center to center front / back distance
        public static final double wheelDiameter = Units.inchesToMeters(4.125);//guessing there is about 1/8" added for the tread. The wheel diameter is 4"
        public static final double wheelCircumfrence = wheelDiameter * Math.PI;//meters
        public static final double driveGearRatio = 6.75;
        public static final double maxMotorRpm = 6380;
        public static final double maxWheelRpm = maxMotorRpm / driveGearRatio;
        public static final double maxRobotSpeedmps = maxWheelRpm / 60 * wheelCircumfrence;//should be 5.1853 mps
        //Swerve locations relative to the center of the robot. Positive x values represent moving toward the front of the robot whereas positive y values
        // represent moving toward the left of the robot. Distances are in meters.
        public static Translation2d LFLocation = new Translation2d(wheelBase/2, trackWidth/2);
        public static Translation2d RFLocation = new Translation2d(wheelBase/2, -trackWidth/2);
        public static Translation2d LBLocation = new Translation2d(-wheelBase/2, trackWidth/2);
        public static Translation2d RBLocation = new Translation2d(-wheelBase/2, -trackWidth/2);
        public static double turnGearRatio = 150.0 / 7.0;
        /**Maximum angular velocity in degrees per second */
        public static double maxAngularVelocityRps = 10.0;

        public static final int PigeonID = 49;

        public static final int kNominalVoltage = 12;
        public static final int kDriveCurrentLimit = 60;
        public static final int kSteerCurrentLimit = 25;

        public static final double speakerDistance = 6;
    }
    
    public static final class ControllerConstants{
        public static double joystickDeadband = 0.1;//a deadband that you must overcome for the joystick input, otherwise we send 0
    }
    public static final class DebugSetting{
        public static final DebugLevel TraceLevel = DebugLevel.Arm;//set this to get more values to smart dashboard.
    }
    public static enum DebugLevel{
        Off,
        Swerve,
        Arm,
        All
    }
    public static final class AutoConstants {
        public static final double kPTranslationController = 2;
        public static final double kPThetaController = 3;
    }
    public static final class visionConstants {
        public static final int cameraName = 12;
    }
}
