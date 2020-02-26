/*---------------------------------------------------------------------------
   FRC Team CrossThreaded #7411
   Valley Lutheran School, Cedar Falls, IA
   Open Source Software - may be modified and shared by all.
  ---------------------------------------------------------------------------*/
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANID;

public class BallCollectorSubsystem extends SubsystemBase
{
   public VictorSPX ballCollectorMotor = new VictorSPX(CANID.ballCollector);

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
}
