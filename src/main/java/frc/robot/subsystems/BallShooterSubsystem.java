/*---------------------------------------------------------------------------
   FRC Team CrossThreaded #7411
   Valley Lutheran School, Cedar Falls, IA
   Open Source Software - may be modified and shared by all.
  ---------------------------------------------------------------------------*/
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANID;

public class BallShooterSubsystem extends SubsystemBase
{
   // private Spark launchMotorBottom = new Spark(CANID.ballShooterBottom);
   // private Spark launchMotorTop = new Spark(CANID.ballShooterTop);
   private CANSparkMax launchMotorBottom = new CANSparkMax(CANID.ballShooterBottom, MotorType.kBrushless);
   private CANSparkMax launchMotorTop = new CANSparkMax(CANID.ballShooterTop, MotorType.kBrushless);

   // Constructor
   public BallShooterSubsystem()
   {
      stopMotors();
      launchMotorTop.setInverted(true);
   }


   // Sets motor power for the ball launcher motor
   public void setMotorPower(double targetPowerLower, double targetPowerUpper)
   {
      launchMotorBottom.set(targetPowerLower);
      launchMotorTop.set(targetPowerUpper);
   }


   // Stops the motor by setting power to 0.0
   public void stopMotors()
   {
      double stopPower = 0.0;
      launchMotorBottom.set(stopPower);
      launchMotorTop.set(stopPower);
   }
}
