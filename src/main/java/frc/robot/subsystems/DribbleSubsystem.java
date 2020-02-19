/*---------------------------------------------------------------------------
   FRC Team CrossThreaded #7411
   Valley Lutheran School, Cedar Falls, IA
   Open Source Software - may be modified and shared by all.
  ---------------------------------------------------------------------------*/
  package frc.robot.subsystems;

  import edu.wpi.first.wpilibj2.command.SubsystemBase;
  import frc.robot.Constants;
  import edu.wpi.first.wpilibj.Servo;
  
  public class DribbleSubsystem extends SubsystemBase
  {
     public Servo dribbleServo = new Servo(Constants.MotorPorts.dribbleServoPort);
  
     /**
      * Creates a new DriveSubsystem.
      */
     public DribbleSubsystem()
     {
     }
  
     public void runDribbleServo(double dribblePosition)
     {
        dribbleServo.set(dribblePosition);
     }
  }
  