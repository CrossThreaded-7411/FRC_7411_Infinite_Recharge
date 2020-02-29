/*---------------------------------------------------------------------------
   FRC Team CrossThreaded #7411
   Valley Lutheran School, Cedar Falls, IA
   Open Source Software - may be modified and shared by all.
  ---------------------------------------------------------------------------*/
package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.Constants;


public class DriveByJoystick extends CommandBase
{
   private final DriveTrainSubsystem driveTrain;
   private double forward;
   private double rotate;

   public DriveByJoystick(DriveTrainSubsystem subsystem)
   {
      driveTrain = subsystem;
      addRequirements(subsystem);
   }


   @Override
   public void execute()
   {
      forward = Robot.m_robotContainer.driver1Controller.getRawAxis(Constants.GamePadAxis.leftStickY.value);
      rotate = -Robot.m_robotContainer.driver1Controller.getRawAxis(Constants.GamePadAxis.leftStickX.value);

      driveTrain.driveByArcade(forward, rotate);
   }
}
