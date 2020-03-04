package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class StartTargeting extends CommandBase
{
   public StartTargeting()
   {
   }


   @Override
   public void initialize()
   {
      RunTurret.setTargeting(true);
   }

   
   @Override
   public boolean isFinished()
   {
      return true;
   }
}
