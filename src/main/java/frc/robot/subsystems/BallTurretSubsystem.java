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
   //10050 -3520
   private final int countLimitCW = 6500;
   private final int countLimitCCW = -6500;

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
      if (operatingState() == State.at_CCW_limit && power < 0)
      {
         stopMotor();
      }
      else if (operatingState() == State.at_CW_limit && power > 0)
      {
         stopMotor();
      }
      else
      {
         turretMotor.set(ControlMode.PercentOutput, power);
      }
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
   public int getRelativePosition()
   {
      return turretMotor.getSelectedSensorPosition();//getSensorCollection().getPulseWidthPosition();
   }

   // Rotational state of the turret
   private enum State
   {
      in_range,
      at_CCW_limit,
      at_CW_limit;
   }

   // Limit rotation of the turret based on the absolute encoder position of the subsystem.
   // The desired operation is to allow 180 degrees of rotation.
   // Encoder values need to be determined empirically.
   private State operatingState()
   {
      State state = State.in_range;
      int position = getRelativePosition();

      if (position <= countLimitCCW)
      {
         state = State.at_CCW_limit;
      }
      else if (position >= countLimitCW)
      {
         state = State.at_CW_limit;
      }
      else
      {
         state = State.in_range;
      }

      return state;
   }


   // Log data to file
   public void logTurretData(boolean enabled)
   {
      if (enabled)
      {
         logger.finer("Turrent_ABS_Enc: " + turretMotor.getSensorCollection().getPulseWidthPosition());

         // Divide by 100 to convert percent to ratio to be consistant with non-CTRE motor libraries
         logger.finer("Turret_Power: " + turretMotor.getMotorOutputPercent() / 100.0);
      }
   }
}
