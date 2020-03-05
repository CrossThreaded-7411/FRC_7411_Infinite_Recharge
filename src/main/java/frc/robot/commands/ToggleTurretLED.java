package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ToggleTurretLED extends CommandBase
{
   boolean LEDActive = false;

   public ToggleTurretLED(boolean ledOn)
   {
      this.LEDActive = ledOn;
   }


   @Override
   public void initialize()
   {
      RunTurret.setTurretLedOn(this.LEDActive);
   }

   
   @Override
   public boolean isFinished()
   {
      return true;
   }
}
