package frc.robot.commands;

import frc.robot.subsystems.BallShooterSubsystem;
import frc.robot.subsystems.BallFeederSubsystem;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;


public class StartShooterCommandGroup extends SequentialCommandGroup
{
    public StartShooterCommandGroup(BallShooterSubsystem ballShooter, BallFeederSubsystem ballFeeder)
    {
        // new SequentialCommandGroup(new RunBallFeeder(ballFeeder, 0.5), new WaitCommand(1), new RunBallShooter(ballShooter, 0.5, 0.7));
        addCommands(
            new RunBallShooter(ballShooter, 0.2, 0.6),
            new WaitCommand(1.0),
            new RunBallFeeder(ballFeeder, 0.2)
        );
    }
}