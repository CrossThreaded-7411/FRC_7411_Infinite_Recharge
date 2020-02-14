package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import frc.robot.subsystems.BallShooterSubsystem;
import frc.robot.subsystems.BallFeederSubsystem;

public class StartShooterCommandGroup extends CommandGroupBase {

    public StartShooterCommandGroup(BallShooterSubsystem subsystem, BallFeederSubsystem subsystem)
    {
        addSequential(new RunBallFeeder());
        addSequential(new RunBallShooter());

    }

   
    

















}


