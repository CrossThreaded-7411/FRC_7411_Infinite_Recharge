package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class TurretToZero extends CommandBase
{
   boolean turretReturnZero = false;

   public TurretToZero(boolean turretReturnZero)
   {
      this.turretReturnZero = turretReturnZero;
   }


   @Override
   public void initialize()
   {
      RunTurret.setReturnToZero(this.turretReturnZero);
   }

   
   @Override
   public boolean isFinished()
   {
      return true;
   }
}
