package frc.robot.commands;

import frc.robot.subsystems.BallShooterSubsystem;
import frc.robot.subsystems.BallFeederSubsystem;
//import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;


public class StopShooterCommandGroup extends SequentialCommandGroup
{
    public StopShooterCommandGroup(BallShooterSubsystem ballShooter, BallFeederSubsystem ballFeeder)
    {
        // new SequentialCommandGroup(new RunBallFeeder(ballFeeder, 0.5), new WaitCommand(1), new RunBallShooter(ballShooter, 0.5, 0.7));
        addCommands(
            new RunBallShooter(ballShooter, 0.0, 0.0),
            new RunBallFeeder(ballFeeder, 0.0)
        );
    }
}