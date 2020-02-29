package frc.robot.commands;

import frc.robot.subsystems.DriveTrainSubsystem;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutonomousDriveCommandGroup extends SequentialCommandGroup
{
    public AutonomousDriveCommandGroup(DriveTrainSubsystem driveTrain) 
    {

        System.out.println("entered my autonomous mode");
        addCommands
        (
            
            new AutonomousDrive(driveTrain, 0.2, 0.0),  
            new WaitCommand(2),
            new AutonomousDrive(driveTrain, 0.0, 0.0)
            
        );
        System.out.println("exited autonomous mode");
    }  
}
