/*---------------------------------------------------------------------------
   FRC Team CrossThreaded #7411
   Valley Lutheran School, Cedar Falls, IA
   Open Source Software - may be modified and shared by all.
  ---------------------------------------------------------------------------*/
package frc.robot.commands;

import frc.robot.subsystems.BallShooterSubsystem;
import frc.robot.subsystems.BallFeederSubsystem;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
//import frc.robot.commands.BallCollectorManual;
import frc.robot.subsystems.BallCollectorSubsystem;
import frc.robot.subsystems.DriveTrainSubsystem;

public class AutonomousCommandGroup extends SequentialCommandGroup
{
   public AutonomousCommandGroup(BallShooterSubsystem ballShooter,
                                   BallFeederSubsystem ballFeeder,
                                   BallCollectorSubsystem ballCollector,
                                   DriveTrainSubsystem driveTrain)
   {
      // new SequentialCommandGroup(new RunBallFeeder(ballFeeder, 0.5), new WaitCommand(1), new RunBallShooter(ballShooter, 0.5, 0.7));
      addCommands
      (
         //10ft: 0.4, 0.7
         // 31 ft 0.75 0.85
         // new RunBallShooter(ballShooter, 0.80, 0.95),
         // new WaitCommand(0.5),
         // new RunBallFeeder(ballFeeder, 0.35),
         // new WaitCommand(0.5),
         // new BallCollectorManual(ballCollector, -0.35)
         new RunDriveTrain(driveTrain, 0.3, 0.3)
      );
   }
}