package frc.robot.commands;

import java.util.logging.Logger;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.LiftSubsystem;
//import java.math.abs;

public class RunLift extends CommandBase {
   private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
   private final LiftSubsystem lift;
   private double slide;
   private double raise;
   private double deadband;
   private double maxPower;

   public RunLift(LiftSubsystem subsystem)
   {
      lift = subsystem;
      addRequirements(subsystem);
      setDeadband(0.05);
      setMaxPower(1.0);
   }

   @Override
   public void execute()
   {
      raise = rangeLimitedPower(Robot.m_robotContainer.driver2Joystick.getRawAxis(Constants.GamePadAxis.rightStickY.value));
      slide = rangeLimitedPower(Robot.m_robotContainer.driver2Joystick.getRawAxis(Constants.GamePadAxis.leftStickX.value));

      lift.runLift(raise);
      lift.runSlide(-slide);
   }

   /**
    * Limits the max power and applies deadband to the motor controllor input
    * @param rawCommand The raw joystick axis value
    */
   private double rangeLimitedPower(double rawCommand)
   {
      double newCommand;

      // Apply simple deadband
      if (Math.abs(rawCommand) < deadband)
      {
         newCommand = 0.0;
      }
      else
      {
         // Apply max power limit
         newCommand = rawCommand * maxPower;
      }

      return (newCommand);
   }

   /**
    * Method sets deadband for joystick axis
    * 
    * @param deadbandValue
    */
   private void setDeadband(double deadbandValue)
   {
      if (Math.abs(deadbandValue) <= 1.0)
      {
         deadband = deadbandValue;
      }
      else
      {
         deadband = 1.0;
      }
   }

   /**
    * Method limits max power sent to motor controller
    * 
    * @param maxPowerValue
    */
   private void setMaxPower(double maxPowerValue)
   {
      if (Math.abs(maxPowerValue) <= 1.0) {
         maxPower = maxPowerValue;
      }
      else
      {
         maxPower = 1.0;
      }
   }

}
