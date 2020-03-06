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
import frc.robot.commands.ToggleTurretLED;


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
         new DriveByTime(driveTrain, -0.3, 0.0, 3000.0),
         new WaitCommand(0.5),
         new DriveByTime(driveTrain, 0.0, 0.0, 500.0),
         new WaitCommand(0.5),
         new StartTargeting(),
         new WaitCommand(0.5),
         new RunBallShooter(ballShooter, 0.75, 0.90),
         new WaitCommand(0.5),
         new RunBallFeeder(ballFeeder, 0.35),
         new WaitCommand(0.5),
         new BallCollectorManual(ballCollector, -0.35),

         //  pause for shooting the balls
         new WaitCommand(3.0),

         // stop all motors
         new StopTargeting(),
         new RunBallShooter(ballShooter, 0.0, 0.0),
         new RunBallFeeder(ballFeeder, 0.0),
         new BallCollectorManual(ballCollector, 0.0),
         new WaitCommand(0.5),

         // return turret to zero
         new TurretToZero(true),
         new WaitCommand(2.0),
         new TurretToZero(false)
      );
   }
}