/*---------------------------------------------------------------------------
   FRC Team CrossThreaded #7411
   Valley Lutheran School, Cedar Falls, IA
   Open Source Software - may be modified and shared by all.
  ---------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallFeederSubsystem;


public class RunBallFeeder extends CommandBase
{
   private final BallFeederSubsystem ballFeeder;
   private final double m_feederPower;

   public RunBallFeeder(BallFeederSubsystem feederSubsystem, double feederPower)
   {
      ballFeeder = feederSubsystem;
      m_feederPower = feederPower;
      addRequirements(feederSubsystem);
   }


   @Override
   public void initialize()
   {
      ballFeeder.setMotorPower(m_feederPower);

   }


   @Override
   public boolean isFinished()
   {
      return true;
   }

}
