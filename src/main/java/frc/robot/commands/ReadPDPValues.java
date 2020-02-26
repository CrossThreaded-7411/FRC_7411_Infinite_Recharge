/*---------------------------------------------------------------------------
   FRC Team CrossThreaded #7411
   Valley Lutheran School, Cedar Falls, IA
   Open Source Software - may be modified and shared by all.
  ---------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PDPSubsystem;


public class ReadPDPValues extends CommandBase
{
   private final PDPSubsystem pdp;

   // Constructor
   public ReadPDPValues(PDPSubsystem subsystem)
   {
      pdp = subsystem;
      addRequirements(subsystem);
   }


   // Get values from pdp
   @Override
   public void execute()
   {
      pdp.logPDPData();
   }


   // This command should remain active through the entire competition so isFinished is False
   @Override
   public boolean isFinished()
   {
      return false;
   }
}
