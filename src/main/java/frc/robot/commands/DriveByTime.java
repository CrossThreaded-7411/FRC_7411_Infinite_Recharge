/*---------------------------------------------------------------------------
   FRC Team CrossThreaded #7411
   Valley Lutheran School, Cedar Falls, IA
   Open Source Software - may be modified and shared by all.
  ---------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

public class DriveByTime extends CommandBase
{
   private final DriveTrainSubsystem DTAutoSubsystem;
   private double fwdPower;
   private double rotPower;
   private double timeInMs;
   private double endTimeInMs;

   /**
    * Runs drivetrain using the "Drive By Arcade" mode for a specified amount of time
    * @param driveTrainAutoSubsystem Drivetrain subsystem
    * @param fwdPower Forward motor power (Range: -1.0 to 1.0)
    * @param rotPower Rotation motor power (Range: -1.0 to 1.0)
    * @param timeInMs Duration of time to run command in milliseconds
    */
   public DriveByTime(DriveTrainSubsystem driveTrainAutoSubsystem, double fwdPower, double rotPower, double timeInMs)
   {
      DTAutoSubsystem = driveTrainAutoSubsystem;
      this.fwdPower = fwdPower;
      this.rotPower = rotPower;
      this.timeInMs = timeInMs;
      addRequirements(DTAutoSubsystem);
   }


   @Override
   public void initialize()
   {
      // Differential drive safety disabled so it would continue to run in auto. without constant input
      DTAutoSubsystem.driveBase.setSafetyEnabled(false);

      // Sets the time for how long this autonomous command will run
      this.endTimeInMs = System.currentTimeMillis() + this.timeInMs;
   }


   @Override
   public void execute()
   {
      DTAutoSubsystem.driveByArcade(this.fwdPower, this.rotPower);
   }


   @Override
   public boolean isFinished()
   {
      return System.currentTimeMillis() >= this.endTimeInMs;
   }


   @Override
   public void end(boolean interrupted)
   {
      // Differential drive safety disabled so it would continue to run in auto. without constant input
      DTAutoSubsystem.driveBase.setSafetyEnabled(true);
      // DTAutoSubsystem.stopDriveMotors();
   }
}
