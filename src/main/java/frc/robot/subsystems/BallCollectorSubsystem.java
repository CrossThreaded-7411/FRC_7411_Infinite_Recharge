/*---------------------------------------------------------------------------
   FRC Team CrossThreaded #7411
   Valley Lutheran School, Cedar Falls, IA
   Open Source Software - may be modified and shared by all.
  ---------------------------------------------------------------------------*/
package frc.robot.subsystems;

import java.util.logging.Logger;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANID;

public class BallCollectorSubsystem extends SubsystemBase
{
   public VictorSPX ballCollectorMotor = new VictorSPX(CANID.ballCollector);
   private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

   public BallCollectorSubsystem()
   {
      stopBallCollectorMotor();
   }


   public void setMotorPower(double targetPowerCollector)
   {
      ballCollectorMotor.set(ControlMode.PercentOutput, targetPowerCollector);
   }


   public void stopBallCollectorMotor()
   {
      ballCollectorMotor.set(ControlMode.PercentOutput, 0.0);
   }


   public void runBallCollectorMotor(double collectorSpeed)
   {
      ballCollectorMotor.set(ControlMode.PercentOutput, collectorSpeed);
   }


   // Log data to file
   public void logBallCollectorData(boolean enabled)
   {
      if (enabled)
      {
         // Divide by 100 to convert percent to ratio to be consistant with non-CTRE motor libraries
         logger.finer("Ball_Collector_Power: " + ballCollectorMotor.getMotorOutputPercent() / 100.0);
      }
   }
}
