package frc.robot.commands;

import frc.robot.subsystems.DriveTrainSubsystem;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutonomousDriveCommandGroup extends SequentialCommandGroup
{
    public AutonomousDriveCommandGroup(DriveTrainSubsystem driveTrain) 
    {
        addCommands
        (
            new AutonomousDrive(driveTrain, 0.2, 0.2),  
            new WaitCommand(2),
            new AutonomousDrive(driveTrain, 0.0, 0.0)
        );
    }
}
