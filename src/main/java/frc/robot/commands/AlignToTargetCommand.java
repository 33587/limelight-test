package frc.robot.commands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.DriveTrainSubsystem;
import frc.robot.Subsystems.LimelightSubsystem;

public class AlignToTargetCommand extends Command {
    private final DriveTrainSubsystem driveTrain;
    private final LimelightSubsystem limelight;

    private final double alignmentThreshold = 1.0; // Degrees of acceptable alignment
    private final double maxTurnSpeed = 0.5;       // Maximum turn speed
    private final double kP = 0.02;                // Proportional control constant for rotation

    public AlignToTargetCommand(DriveTrainSubsystem driveTrain, LimelightSubsystem limelight) {
        this.driveTrain = driveTrain;
        this.limelight = limelight;
        addRequirements(driveTrain, limelight);
    }

    @Override
    public void initialize() {
        System.out.println("AlignToTargetCommand initialized");
    }

    @Override
    public void execute() {
        if (limelight.isTargetVisible()) {
            // Get horizontal target offset from Limelight (tx)
            double errorX = limelight.getTargetOffsetX();

            // Calculate rotational speed using proportional control
            double turnSpeed = kP * errorX;
            turnSpeed = Math.max(-maxTurnSpeed, Math.min(maxTurnSpeed, turnSpeed)); // Clamp turn speed

            // Command swerve drive to rotate (no translation movement)
            driveTrain.drive(new Translation2d(0.0, 0.0), turnSpeed);
        } else {
            // If no target is visible, stop the robot
            driveTrain.drive(new Translation2d(0.0, 0.0), 0.0);
        }
    }

    @Override
    public boolean isFinished() {
        // Command finishes when the alignment error is within the acceptable threshold
        return Math.abs(limelight.getTargetOffsetX()) < alignmentThreshold;
    }

    @Override
    public void end(boolean interrupted) {
        // Stop the drivetrain when the command ends
        driveTrain.drive(new Translation2d(0.0, 0.0), 0.0);
        if (interrupted) {
            System.out.println("AlignToTargetCommand interrupted");
        } else {
            System.out.println("AlignToTargetCommand completed");
        }
    }
}