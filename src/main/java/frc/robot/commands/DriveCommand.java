// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;

/** An Drive command that uses an Drive subsystem. */
public class DriveCommand extends Command {
  @SuppressWarnings("PMD.UnusedPrivateField")
  private final DriveSubsystem m_DriveSubsystem;
  private double m_xSpeed;
  private double m_ySpeed;
  private double m_rotation;

  /**
   * Creates a new DriveCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveCommand(DriveSubsystem subsystem, DoubleSupplier x, DoubleSupplier y, DoubleSupplier r) {
    m_DriveSubsystem = subsystem;
    m_xSpeed = x.getAsDouble();
    m_ySpeed = y.getAsDouble();
    m_rotation = r.getAsDouble();

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_DriveSubsystem);
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("in execute x: "+ m_xSpeed + " y:"+ m_ySpeed +" z:"+ m_rotation);
    m_DriveSubsystem.driveCartesian(m_xSpeed, m_ySpeed, m_rotation);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_DriveSubsystem.driveCartesian(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
