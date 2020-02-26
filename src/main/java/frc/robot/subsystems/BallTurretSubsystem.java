/*---------------------------------------------------------------------------
   FRC Team CrossThreaded #7411
   Valley Lutheran School, Cedar Falls, IA
   Open Source Software - may be modified and shared by all.
  ---------------------------------------------------------------------------*/
package frc.robot.subsystems;

import java.util.logging.Logger;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import frc.robot.Constants.CANID;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class BallTurretSubsystem extends SubsystemBase
{
   private TalonSRX turretMotor = new TalonSRX(CANID.ballShooterTurret);
   private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


   /**
    * Creates a new DriveSubsystem.
    */
   public BallTurretSubsystem()
   {
      turretMotor.configSelectedFeedbackSensor(FeedbackDevice.PulseWidthEncodedPosition, 1, 10);
      // Constructor
      stopMotor();
   }

   
   /**
    * Sets motor power for the ball launcher motor
    * 
    * @param targetPower Accepts a power level between -1 to 1
    */
   public void setMotorPower(double power)
   {
      turretMotor.set(ControlMode.PercentOutput, power);
   }


   /**
    * Stops the motor by setting power to 0.0
    */
   public void stopMotor()
   {
      turretMotor.set(ControlMode.PercentOutput, 0.0);
   }


   // Returns the absolute encoder position for the turret. We need absolute position since the turret
   // is not gauranteed to be at a known state on power up.
   public int getAbsPosition()
   {
      return turretMotor.getSensorCollection().getPulseWidthPosition();
   }

   public void displayTurretPosition()
   {
      System.out.println("Turret Counts: " + turretMotor.getSelectedSensorPosition() + ",  " + turretMotor.getSensorCollection().getPulseWidthPosition());
   }


   // Log data to file
   public void logTurretData(boolean enabled)
   {
      if (enabled)
      {
         logger.finer("Turrent_ABS_Encoder_Counts: " + turretMotor.getSensorCollection().getPulseWidthPosition());

         // Divide by 100 to convert percent to ratio to be consistant with non-CTRE motor libraries
         logger.finer("Turret_Power: " + turretMotor.getMotorOutputPercent() / 100.0);
      }
   }
}
