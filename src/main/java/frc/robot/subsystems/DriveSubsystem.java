// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.MaxDriveModule;
import frc.robot.Constants.Location;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new DriveSubsystem. */
  private final MecanumDrive m_MecanumDrive;
  private final MaxDriveModule m_FrontRight = new MaxDriveModule(Location.FR, 11);
  private final MaxDriveModule m_BackRight = new MaxDriveModule(Location.BR, 12);
  private final MaxDriveModule m_BackLeft = new MaxDriveModule(Location.BL, 13);
  private final MaxDriveModule m_FrontLeft = new MaxDriveModule(Location.FL, 14);
  private final double m_MaxSpeed = Constants.kMaxSpeed;

  public DriveSubsystem() {

        m_MecanumDrive = new MecanumDrive(m_FrontLeft.getMotor(), 
                                      m_BackLeft.getMotor(), 
                                      m_FrontRight.getMotor(), 
                                      m_BackRight.getMotor());

  }

  /**
   * Drives the robot using Cartesian coordinates (robot-oriented).
   * @param xSpeed    Forward/backward speed (-1.0 to 1.0)
   * @param ySpeed    Left/right strafe speed (-1.0 to 1.0)
   * @param zRotation Clockwise/counter-clockwise rotation (-1.0 to 1.0)
   */
  public void driveCartesian(double xSpeed, double ySpeed, double zRotation) {
    //Limit the speed. Max speed should be a percentage 0-1.
    xSpeed *= m_MaxSpeed;
    m_MecanumDrive.driveCartesian(xSpeed, ySpeed, zRotation);
  }

  public Command driveCartesianCommand(DoubleSupplier xSpeed, DoubleSupplier ySpeed, DoubleSupplier zRotation) {
    System.out.println("Command Drive created.");
    return run(() -> m_MecanumDrive.driveCartesian(xSpeed.getAsDouble(), ySpeed.getAsDouble(), zRotation.getAsDouble()));
  }
  /**
   * An Drive method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean driveCondition() {
    // Query some boolean state, such as a digital sensor.
    System.out.println("In driveCondition");
    return true;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void stop() {
    m_MecanumDrive.driveCartesian(0,0,0);
  }
}
