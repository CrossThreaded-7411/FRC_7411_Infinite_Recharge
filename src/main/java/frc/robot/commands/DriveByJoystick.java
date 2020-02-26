package frc.robot.commands;

import frc.robot.Robot;
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
      System.out.println("output joystick value");
      forward = Robot.m_robotContainer.driver1Controller.getRawAxis(Constants.GamePadAxis.leftStickY.value);
      rotate = -Robot.m_robotContainer.driver1Controller.getRawAxis(Constants.GamePadAxis.leftStickX.value);
      
      logger.fine("forward  command: " + forward);
      logger.fine("rotate command:" + rotate);
      // driveTrain.arcadeDrive(forward.getAsDouble(), rotate.getAsDouble());
      
      driveTrain.driveByArcade(forward, rotate);
      // driveTrain.driveByArcade(forward, rotate);
   }  
}