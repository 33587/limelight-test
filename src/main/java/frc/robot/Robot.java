package frc.robot;

import org.photonvision.PhotonCamera;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Subsystems.DriveTrainSubsystem;
import frc.robot.Subsystems.PhotonVisionSubsystem;
import frc.robot.commands.AlignToTargetCommand;
import frc.robot.commands.TeleopSwerve;

public class Robot extends TimedRobot {
  private DriveTrainSubsystem drive;
  private PhotonVisionSubsystem vision;
  private XboxController drivestick = new XboxController(0);

  @Override
  public void robotInit() {
    drive = new DriveTrainSubsystem();
    vision = new PhotonVisionSubsystem("CameraName"); // Replace "CameraName" with the PhotonVision camera's name
    SmartDashboard.putString("Camera Stream", "http://10.57.24.2:1181/stream.mjpg");
    // Set default drivetrain command
    drive.setDefaultCommand(new TeleopSwerve(drive, drivestick));
  }
  

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }
  @Override
  public void teleopPeriodic() {
      if (drivestick.getAButtonPressed()) {
          new AlignToTargetCommand(drive, vision).schedule();
      }
  }
}  
