/*---------------------------------------------------------------------------
   FRC Team CrossThreaded #7411
   Valley Lutheran School, Cedar Falls, IA
   Open Source Software - may be modified and shared by all.
  ---------------------------------------------------------------------------*/
package frc.robot.subsystems;

import java.util.logging.Logger;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class RecordPlayerSubsystem extends SubsystemBase
{
   private TalonSRX recordPlayerMotor = new TalonSRX(Constants.CANID.recordPlayer);
   private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

   //constructor
   public RecordPlayerSubsystem()
   {
      stopMotor();
   }


   // Sets motor power for the Record Player Spinner motor
   public void setRecordPlayerPower(double motorPower)
   {
      recordPlayerMotor.set(ControlMode.PercentOutput, motorPower);
   }


   // Stops the motor by setting power to 0.0
   public void stopMotor()
   {
      double stopMotorPower = 0.0;
      recordPlayerMotor.set(ControlMode.PercentOutput, stopMotorPower);
   }


   // Log data to file
   public void logRecordPlayerData(boolean enabled)
   {
      if (enabled)
      {
         // Divide by 100 to convert percent to ratio to be consistant with non-CTRE motor libraries
         logger.finer("Record_Player_Power: " + recordPlayerMotor.getMotorOutputPercent() / 100.0);
      }
   }
}