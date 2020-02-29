package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;



public class AutonomousDrive extends CommandBase 
{
  
 private final DriveTrainSubsystem driveTrain;
 private final double forwardPower;
 private final double rotatePower;



 public AutonomousDrive(DriveTrainSubsystem subsystem, double fwdPower, double rotPower) 
 {
   System.out.println(" entered autonomous drive");
    driveTrain = subsystem;
    forwardPower = fwdPower;
    rotatePower = rotPower;
    addRequirements(subsystem);
 }

 @Override
   public void initialize()
   {
     driveTrain.driveByArcade(forwardPower, rotatePower);
     System.out.println("motor power");
   }

   @Override
   public boolean isFinished()
   {
      return true;
   }


}