/*---------------------------------------------------------------------------
   FRC Team CrossThreaded #7411
   Valley Lutheran School, Cedar Falls, IA
   Open Source Software - may be modified and shared by all.
  ---------------------------------------------------------------------------*/
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANID;

public class BallFeederSubsystem extends SubsystemBase
{
   private TalonSRX feederMotor = new TalonSRX(CANID.ballFeeder);

   /**
    * Creates a new DriveSubsystem.
    */
   public BallFeederSubsystem()
   {
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
      feederMotor.set(ControlMode.PercentOutput, power);
   }

   /**
    * Stops the motor by setting power to 0.0
    */
   public void stopMotor()
   {
      feederMotor.set(ControlMode.PercentOutput, 0.0);
   }
}
