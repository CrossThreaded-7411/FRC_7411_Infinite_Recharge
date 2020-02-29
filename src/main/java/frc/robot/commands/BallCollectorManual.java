/*---------------------------------------------------------------------------
   FRC Team CrossThreaded #7411
   Valley Lutheran School, Cedar Falls, IA
   Open Source Software - may be modified and shared by all.
  ---------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.subsystems.BallShooterSubsystem;
import frc.robot.subsystems.BallCollectorSubsystem;
//mport frc.robot.subsystems.BallFeederSubsystem;
import frc.robot.subsystems.BallCollectorSubsystem;


public class BallCollectorManual extends CommandBase
{
   private final BallCollectorSubsystem ballCollector;
   private final double m_collectorPower;

   public BallCollectorManual(BallCollectorSubsystem subsystem, double collectorPower)
   {
      ballCollector = subsystem;
      m_collectorPower = collectorPower;
      addRequirements(ballCollector);
   }

   @Override
   public void initialize()
   {
      ballCollector.setMotorPower(m_collectorPower);
   }

   @Override
   public boolean isFinished()
   {
      return true;
   }
}
