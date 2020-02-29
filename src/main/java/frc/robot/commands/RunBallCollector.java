/*---------------------------------------------------------------------------
   FRC Team CrossThreaded #7411
   Valley Lutheran School, Cedar Falls, IA
   Open Source Software - may be modified and shared by all.
  ---------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallCollectorSubsystem;
import frc.robot.Robot;


public class RunBallCollector extends CommandBase
{
   private final BallCollectorSubsystem m_ballCollector;

   public RunBallCollector(BallCollectorSubsystem subsystem)
   {
      m_ballCollector = subsystem;
      addRequirements(subsystem);
   }


   @Override
   public void execute()
   {
      int direction = Robot.m_robotContainer.driver2Controller.getPOV(0);
      Robot.m_robotContainer.driver1Controller.getPOV();
      this.setBallCollectorMotorPower(direction);
   }


   private void setBallCollectorMotorPower(int direction)
   {
      if (direction == 0)
      {
         m_ballCollector.setMotorPower(-0.35);
      }
      else if (direction == 180)
      {
         m_ballCollector.setMotorPower(0.35);
      }
      else if (direction == 270)
      {
         // when POV is not pressed will not spin
         m_ballCollector.setMotorPower(0.0);
      }
   }
}
