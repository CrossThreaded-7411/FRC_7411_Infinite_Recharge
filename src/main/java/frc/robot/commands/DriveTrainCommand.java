package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.Constants.ProAxis;

/**
 * A command to turn on the launch motor to a known desired power
 */
public class DriveTrainCommand extends CommandBase
{
   private final DriveTrainSubsystem m_driveTrain;
   private final double m_drivePower;

   public DriveTrainCommand(DriveTrainSubsystem subsystem, double driveMotorPower)
   {
      m_driveTrain = subsystem;
      m_drivePower = driveMotorPower;
      addRequirements(subsystem);

      //driver 2 input from the joysticks; meant to control the drivetrain
      m_driveTrain.driveBase.arcadeDrive(ProAxis.YAxis.value, ProAxis.XAxis.value, true);
   }

   @Override
   public void initialize()
   {
      m_driveTrain.runDriveMotorsStraight(m_drivePower);
      m_driveTrain.runDriveMotorsTurn(m_drivePower);
   }

   @Override
   public boolean isFinished()
   {
      return true;
   }
}