package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;



public class AutonomousDrive extends CommandBase 
{
  
 private final DriveTrainSubsystem driveTrain;
 private final double motorPowerRight;
 private final double motorPowerLeft;



 public AutonomousDrive(DriveTrainSubsystem subsystem, double rightDrivePower, double leftDrivePower) 
 {
   driveTrain = subsystem;
    motorPowerRight = rightDrivePower;
    motorPowerLeft = leftDrivePower;
    addRequirements(subsystem);
 }

 @Override
   public void initialize()
   {
     driveTrain.setMotorPower(motorPowerRight, motorPowerLeft);
   }

   @Override
   public boolean isFinished()
   {
      return true;
   }

  








}