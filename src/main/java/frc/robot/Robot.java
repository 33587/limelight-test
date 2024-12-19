// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Subsystems.DriveTrainSubsystem;
import frc.robot.Subsystems.LimelightSubsystem;
import frc.robot.commands.AlignToTargetCommand;
import frc.robot.commands.AutoCommand;
import frc.robot.commands.TeleopSwerve;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autoSelected;
  private final SendableChooser<Command> m_chooser = new SendableChooser<>();
  private DriveTrainSubsystem drive;
  private LimelightSubsystem limelight;
  private XboxController drivestick = new XboxController(0);

  @Override
  public void robotInit() {
    // Initialize subsystems
    drive = new DriveTrainSubsystem();
    limelight = new LimelightSubsystem();

    // Configure default commands
    drive.setDefaultCommand(new TeleopSwerve(drive, drivestick));

    // Add auto options
    m_chooser.setDefaultOption("SimpleAuto", new AutoCommand(drive));
    SmartDashboard.putData("Auto Choices", m_chooser);

    // Camera setup
    CameraServer.startAutomaticCapture();
    UsbCamera cam2 = CameraServer.startAutomaticCapture();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    if (m_autoSelected != null) {
      m_autoSelected.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
    // Auto periodic updates
  }

  @Override
  public void teleopInit() {
    if (m_autoSelected != null) {
      m_autoSelected.cancel();
    }
  }

 @Override
public void teleopPeriodic() {
    // When the A button is pressed on the controller, start alignment
    if (drivestick.getAButtonPressed()) {
        // Create a new instance of AlignToTargetCommand
        AlignToTargetCommand alignCommand = new AlignToTargetCommand(drive, limelight);
        
        // Schedule the command (this will run the command)
        alignCommand.schedule();
    }
}


  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {
    drive.simulationInit();
  }

  @Override
  public void simulationPeriodic() {}
}