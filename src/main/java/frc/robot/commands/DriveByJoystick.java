package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.Constants.DriveDirection;
import java.util.logging.Logger;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.Constants;

/**
 * A command to turn on the launch motor to a known desired power
 */
public class DriveByJoystick extends CommandBase
{
   private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
   DriveDirection state = DriveDirection.normal;
   DriveDirection invertedstate = DriveDirection.inverted;
   private final DriveTrainSubsystem driveTrain;
   private double forward;
   private double rotate;
   
   


   public DriveByJoystick(DriveTrainSubsystem subsystem)//, double forwardValue, double rotateValue)
   {
      driveTrain = subsystem;
      // forward = forwardValue;
      // rotate = rotateValue;
      addRequirements(subsystem);

      // logger.fine("yaxis: " + forwardValue);
      // logger.fine("xaxis: " + rotateValue);
   }

   
   @Override
   public void execute()
   {
      forward = Robot.m_robotContainer.driver1Controller.getRawAxis(Constants.GamePadAxis.leftStickY.value);
      rotate = -Robot.m_robotContainer.driver1Controller.getRawAxis(Constants.GamePadAxis.leftStickX.value);
      boolean invertedbutton = Robot.m_robotContainer.driver1Controller.getRawButton(3);
      boolean normalbutton = Robot.m_robotContainer.driver1Controller.getRawButton(5);

      if(normalbutton)
      {
         state = DriveDirection.normal;
         System.out.println("normal button");
      }
      else if(invertedbutton)
      {
         state = DriveDirection.inverted;
         System.out.println("inverted button");
      }

      if(state == DriveDirection.normal)
      {
         driveTrain.driveByArcade(forward, rotate);
      }
      if(state == DriveDirection.inverted)
      {
         driveTrain.driveByArcade(-forward, rotate);
      }
      
     // driveTrain.driveByArcade(forward, rotate);
   }  
}