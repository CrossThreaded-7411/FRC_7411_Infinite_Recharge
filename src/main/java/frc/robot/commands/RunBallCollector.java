package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallCollectorSubsystem;

public class RunBallCollector extends CommandBase
{
   private final double m_CollectormotorPower;
   private final BallCollectorSubsystem m_ballCollector;


   public RunBallCollector(BallCollectorSubsystem subsystem, double CollectormotorPower)
   {
      m_ballCollector = subsystem;
      m_CollectormotorPower = CollectormotorPower;
      addRequirements(subsystem);
   }


   @Override
   public void initialize()
   {
      m_ballCollector.setMotorPower(m_CollectormotorPower);
      System.out.println("Toggled motor on");
      /*
       * Operated by a single toggle button for on and off, feedback on SmartDashboard, fixed power,
       * needs to reverse by a seperate toggle button
       */
   }


   @Override
   public boolean isFinished()
   {
      System.out.println("Toggled motor on is Finished");
      return true;
   }
}
