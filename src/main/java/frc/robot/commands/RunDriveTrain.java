/*---------------------------------------------------------------------------
   FRC Team CrossThreaded #7411
   Valley Lutheran School, Cedar Falls, IA
   Open Source Software - may be modified and shared by all.
  ---------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

public class RunDriveTrain extends CommandBase
{
   private final DriveTrainSubsystem DTSubsystem;
   private final double rPower;
   private final double lPower;

   public RunDriveTrain(DriveTrainSubsystem driveTrainAutoSubsystem, double rightPower, double leftPower)
   {
      DTSubsystem = driveTrainAutoSubsystem;
      rPower = rightPower;
      lPower = leftPower;
      addRequirements(driveTrainAutoSubsystem);
   }


   @Override
   public void initialize()
   {
      DTSubsystem.driveByCommand(rPower, lPower);
   }


   @Override
   public boolean isFinished()
   {
      return true;
   }

}
