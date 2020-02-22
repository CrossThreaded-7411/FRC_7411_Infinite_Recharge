package frc.robot.commands;

import frc.robot.subsystems.BallShooterSubsystem;
import frc.robot.subsystems.BallFeederSubsystem;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.BallCollectorManual;
import frc.robot.subsystems.BallCollectorSubsystem;


public class StartShooterCommandGroup extends SequentialCommandGroup
{
    public StartShooterCommandGroup(BallShooterSubsystem ballShooter, BallFeederSubsystem ballFeeder, BallCollectorSubsystem ballCollector)
    {
        // new SequentialCommandGroup(new RunBallFeeder(ballFeeder, 0.5), new WaitCommand(1), new RunBallShooter(ballShooter, 0.5, 0.7));
        addCommands
        (
            //10ft: 0.4, 0.7
            new RunBallShooter(ballShooter, 0.5, 0.8),
            new WaitCommand(0.5),
            new RunBallFeeder(ballFeeder, 0.35),
            new WaitCommand(0.5),
            new BallCollectorManual(ballCollector, -0.35)
        );
    }
}