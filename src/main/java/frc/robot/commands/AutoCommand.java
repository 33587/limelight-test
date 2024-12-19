package frc.robot.commands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Subsystems.DriveTrainSubsystem;

public class AutoCommand extends SequentialCommandGroup {
    DriveTrainSubsystem _DriveTrain;

    public AutoCommand(DriveTrainSubsystem driveTrain)
    {
        _DriveTrain = driveTrain;
        addCommands(
            new InstantCommand(() -> _DriveTrain.driveForAuto(new Translation2d(-0.9, 0), 0), _DriveTrain),
            new WaitCommand(4),
            new RunCommand(() -> _DriveTrain.driveForAuto(new Translation2d(0, 0), 0), _DriveTrain)
        );
    }
}
