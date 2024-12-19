package frc.robot.Subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimelightSubsystem extends SubsystemBase {

    public LimelightSubsystem() {
        // Constructor (if needed for initialization)
    }

    public double getTargetOffsetX() {
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0.0);
    }

    public double getTargetOffsetY() {
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0.0);
    }

    public boolean isTargetVisible() {
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0.0) == 1.0;
    }

    public double getTargetArea() {
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0.0);
    }

    public void updateSmartDashboard() {
        SmartDashboard.putNumber("Limelight X", getTargetOffsetX());
        SmartDashboard.putNumber("Limelight Y", getTargetOffsetY());
        SmartDashboard.putBoolean("Target Visible", isTargetVisible());
        SmartDashboard.putNumber("Target Area", getTargetArea());
    }

    @Override
    public void periodic() {
        updateSmartDashboard();
    }
}