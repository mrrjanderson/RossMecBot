package frc.robot;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.ResetMode;
import com.revrobotics.PersistMode;


public class MaxDriveModule {
    private int m_id;
    private Constants.Location m_location;
    private SparkMax m_motor;
    private SparkMaxConfig m_motorConfig;
  
    public MaxDriveModule (Constants.Location location, int id) {
        m_id = id;
        m_location = location;
        m_motor = new SparkMax(m_id, MotorType.kBrushless);
        m_motorConfig = new SparkMaxConfig();

        m_motorConfig.smartCurrentLimit(Constants.kDriveMotorLimit);
        m_motorConfig.idleMode(Constants.kDriveMotorIdleMode);
        
        //invert the motors on the right side
        if(m_location == Constants.Location.FR || m_location == Constants.Location.BR) {
          m_motorConfig.inverted(true);
        }

        m_motor.configure(m_motorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        System.out.println("Built motor " + m_location);
    }

  //Returns the motor
  public SparkMax getMotor() { return m_motor; }

  //returns the module's location (FR, BR, BL, FL)
  public Constants.Location getLocation() { return m_location; }
  
}
