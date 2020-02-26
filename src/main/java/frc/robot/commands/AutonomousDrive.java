package frc.robot.commands;

import java.util.logging.Logger;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.Robot;


public class AutonomousDrive extends CommandBase 
{
 private final DriveTrainSubsystem driveTrain;


 public AutonomousDrive(DriveTrainSubsystem subsystem, double rightDrivePower, double leftDrivePower) 
 {
   driveTrain = subsystem;
   addRequirements(subsystem);
                  rig= rightDrivePower;
   leftDrivePower = leftDrivePower;
 }

 @Override
   public void initialize()
   {
      
       driveTrain.setMotorPower();
   }

  








}